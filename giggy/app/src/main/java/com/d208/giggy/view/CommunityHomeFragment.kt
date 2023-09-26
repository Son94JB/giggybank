package com.d208.giggy.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d208.domain.model.DomainPost
import com.d208.domain.model.DomainTransaction
import com.d208.giggy.R
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentCommunityHomeBinding
import com.d208.giggy.databinding.ItemPostBinding
import com.d208.giggy.viewmodel.CommunityHomeFragmentViewModel
import com.d208.giggy.viewmodel.MainActivityViewModel

import com.d208.presentation.adapter.PostAdapter
import com.d208.presentation.adapter.TransactionAdapater


import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommunityHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CommunityHomeFragment : BaseFragment<FragmentCommunityHomeBinding>(FragmentCommunityHomeBinding::bind, R.layout.fragment_community_home) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter : PostAdapter
    private val communityHomeFragmentViewModel : CommunityHomeFragmentViewModel by viewModels()
    private val mainActivityViewModel : MainActivityViewModel by activityViewModels()
    private var list = mutableListOf<DomainPost>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostAdapter(requireContext())
        init()
    }


    fun init() {
        spinnerInit()
        recyclerViewInit()
        tabInit()

        with(binding){
            fragmentCommunityHomeFoodChip.setOnClickListener {
                it.setBackgroundColor(Color.YELLOW)
            }
            fragmentCommunityHomeFAB.setOnClickListener {
                findNavController().navigate(R.id.action_CommunityHomeFragment_to_CommunityPostRegisterFragment)
            }
        }
        communityHomeFragmentViewModel.getPosts()
        communityHomeFragmentViewModel.postList.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.submitList(it)
            }
            else{
                adapter.submitList(it)
                showSnackbar("불러올 게시글이 없습니다.")
            }
        }

    }

    fun recyclerViewInit() = with(binding) {

        fragmentCommunityHomeRecyclerView.adapter = adapter
        fragmentCommunityHomeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.apply {
            itemClickListener = object : PostAdapter.ItemClickListener{

                override fun onClick(binding: ItemPostBinding, position: Int, data: DomainPost) {
                    mainActivityViewModel.seletedPostId = data.id
                    findNavController().navigate(R.id.action_CommunityHomeFragment_to_CommunityPostDetailFragment)
                }

            }
            itemHeartListener = object : PostAdapter.ItemClickListener{
                override fun onClick(binding: ItemPostBinding, position: Int, data: DomainPost) {
                   communityHomeFragmentViewModel.pushLike(data.id)
                }

            }
        }
    }

    fun tabInit() = with(binding){
        fragmentCommunityHomeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                if (tab != null) {
                    adapter.submitList(newPostTypeList(list,  tab.text.toString()))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

    }
    fun spinnerInit() {
        with(binding){
            fragmentCommunityHomeSpinner.setItems("좋아요 순", "조회 순", "오래된 순", "최신 순")
            fragmentCommunityHomeSpinner.setOnItemSelectedListener { view, position, id, item ->
                view.text = item.toString()
                Log.d("아이템 정보", "spinnerInit: $item")
                Log.d("리스트 정보", "spinnerInit: $list")
                adapter.submitList(orderList(list, item.toString()))

            }
            fragmentCommunityHomeSpinner.setOnNothingSelectedListener {

            }


        }

    }


    fun newPostTypeList(oldlist : MutableList<DomainPost>, postType : String) : MutableList<DomainPost>{

        if(postType == "전체"){
            return list
        }
        else{
            return oldlist.filter {
                it.postType == postType
            } as MutableList<DomainPost>
        }


    }

    fun orderList(oldlist : MutableList<DomainPost>, orderType : String) : List<DomainPost>{

        when(orderType) {
            "좋아요 순" -> {
                return oldlist.sortedByDescending { it.likeCount }
            }
            "조회 순" -> {

                return oldlist.sortedByDescending { it.viewCount }
            }
            "오래된 순"-> {

                return oldlist.sortedBy { it.createdAt }
            }
            "최신 순" -> {

                return oldlist.sortedByDescending { it.createdAt }
            }
        }
        return oldlist
    }


}
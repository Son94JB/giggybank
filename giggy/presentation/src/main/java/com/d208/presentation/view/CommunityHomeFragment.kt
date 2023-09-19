package com.d208.presentation.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d208.presentation.R
import com.d208.presentation.base.BaseFragment
import com.d208.presentation.databinding.FragmentCommunityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Arrays
import java.util.LinkedList

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    fun init() {
//        spinnerInit()
        with(binding){
            fragmentCommunityHomeFoodChip.setOnClickListener {
                it.setBackgroundColor(Color.YELLOW)
            }
        }
    }


//    fun spinnerInit() {
//        val niceSpinner = binding.fragmentCommunityHomeNiceSpinner
//        val dataset: List<String> = LinkedList(Arrays.asList("추천순", "오래된 순", "최신 순"))
//        niceSpinner.attachDataSource(dataset)
//        niceSpinner.selectedIndex = 0
//    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommunityHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommunityHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
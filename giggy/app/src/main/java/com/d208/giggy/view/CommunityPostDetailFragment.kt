package com.d208.giggy.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.d208.domain.utils.StringFormatUtil
import com.d208.giggy.R
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentCommunityPostDetailBinding
import com.d208.giggy.viewmodel.CommunityPostDetailFragmentViewModel
import com.d208.giggy.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [CommunityPostDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "CommunityPostDetailFrag giggy"
@AndroidEntryPoint
class CommunityPostDetailFragment : BaseFragment<FragmentCommunityPostDetailBinding>(FragmentCommunityPostDetailBinding::bind, R.layout.fragment_community_post_detail) {

    private val communityPostDetailFragmentViewModel : CommunityPostDetailFragmentViewModel by viewModels()
    private val mainActivityViewModel : MainActivityViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() = with(binding){
        communityPostDetailFragmentViewModel.getOnePost(mainActivityViewModel.seletedPostId)
        communityPostDetailFragmentViewModel.post.observe(viewLifecycleOwner){
            if(it != null){
                try{
                    Glide.with(requireContext())
                        .load(it.postPicture)
                        .into(fragmentCommunityPostDetailImageView)
                }catch (e : Exception){
                    Log.d(TAG, "init: ${e.message}")
                }
                fragmentCommunityPostDetailTextView.text =  it.title
                fragmentCommunityPostDetailWriterNameTextView.text = it.nickName
                fragmentCommunityPostDetailWriteTimeTextView.text = StringFormatUtil.dateTimeToString(it.createAt)
                fragmentCommunityPostDetailContentTextView.text = it.content

            }
        }
    }



}
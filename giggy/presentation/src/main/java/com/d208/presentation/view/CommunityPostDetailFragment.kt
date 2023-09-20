package com.d208.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d208.presentation.R
import com.d208.presentation.base.BaseFragment
import com.d208.presentation.databinding.FragmentCommunityPostDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [CommunityPostDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommunityPostDetailFragment : BaseFragment<FragmentCommunityPostDetailBinding>(FragmentCommunityPostDetailBinding::bind, R.layout.fragment_community_post_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){

    }



}
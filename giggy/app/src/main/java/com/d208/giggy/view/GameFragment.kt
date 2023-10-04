package com.d208.giggy.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.d208.giggy.R
import com.d208.giggy.adapter.GameRankAdapter
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentGameBinding
import com.unity3d.player.UnityPlayer
import com.unity3d.player.UnityPlayerActivity
import com.unity3d.player.UnityPlayerActivity.RESULT_OK
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val TAG = "GameFragment giggy"

class GameFragment : BaseFragment<FragmentGameBinding>(FragmentGameBinding::bind, R.layout.fragment_game) {

    private lateinit var adapter : GameRankAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: 뷰 시작")
        adapter = GameRankAdapter(requireContext())
        init()
    }

    fun init() = with(binding){

        fragmentGameRankTop10RecyclerView.adapter = adapter
        fragmentGameRankTop10RecyclerView.layoutManager = LinearLayoutManager(requireContext())

        gameStart.setOnClickListener {
            (requireContext() as MainActivity).startGame()
        }
    }



}
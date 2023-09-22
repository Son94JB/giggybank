package com.d208.giggybank.ui.home

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d103.asaf.common.config.BaseFragment
import com.d208.giggybank.R
import com.d208.giggybank.databinding.FragmentHomeBinding
import com.d208.giggybank.ui.MainActivityVIewModel
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()
    private val mainActivityViewModel : MainActivityVIewModel by activityViewModels()
    private var amountPercent = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test()
        animate()
        init()
    }
    fun test()
    {
        amountPercent = 30
    }
    fun animate() {

        with(binding){
            YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .repeat(0)
                .playOn(fragmentHomeConsumptionAnalysisCardView)

            YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .repeat(0)
                .playOn(fragmentHomeCommunityCardView)

            YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .repeat(0)
                .playOn(fragmentHomeMiniGameCardView)

            YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .repeat(0)
                .playOn(fragmentHomeSettingCardView)

            YoYo.with(Techniques.BounceIn)
                .duration(1500)
                .repeat(0)
                .playOn(cardView)


        }



    }

    fun init(){
        with(binding){


            //애니메이션



            //닉네임
            fragmentHomeNickNameTextView.text = "${mainActivityViewModel.user.nickname} 님"


            //은행 정보


            // 소비 패턴 분석 화면
            fragmentHomeConsumptionAnalysisCardView.setOnClickListener {
                findNavController().navigate(R.id.action_HomeFragment_to_ConsumeAnalysisFragment)
            }
            // 미니 게임 화면
            fragmentHomeMiniGameCardView.setOnClickListener {

            }
            // 커뮤니티 화면
            fragmentHomeCommunityCardView.setOnClickListener {
                findNavController().navigate(R.id.action_HomeFragment_to_CommunityHomeFragment)
            }
            // 설정 화면
            fragmentHomeSettingCardView.setOnClickListener {

            }

            ObjectAnimator.ofInt(fragmenthomeProgressBar, "progress", amountPercent.toInt())
                .setDuration(500)
                .start()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
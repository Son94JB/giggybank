package com.d208.presentation.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.d208.presentation.R
import com.d208.presentation.base.BaseFragment
import com.d208.presentation.databinding.FragmentSignUpNextBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpNextFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpNextFragment : BaseFragment<FragmentSignUpNextBinding>(FragmentSignUpNextBinding::bind, R.layout.fragment_sign_up_next) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var countDownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun init(){
        with(binding){
            fragmentSignUpNextCheckButton.setOnClickListener {
                if(!fragmentSignUpNextAccountEditText.text.toString().isNullOrEmpty()){
                    startTimer()
                    fragmentSignUpNextTimerTextView.visibility  = View.VISIBLE
                }
            }
            fragmentSignUpNextCompleteButton.setOnClickListener {

                if(fragmentSignUpNextTimerTextView.text == "0:00"){
                    showSnackbar("시간이 초과되었습니다. 다시 인증 요청을 해주세요")
                }
                else if(fragmentSignUpNextAccountCheckEditText.text.isNullOrEmpty()){
                    showSnackbar("인증 문자를 입력해주세요")
                }
                else{
                    /**만약 인증이 맞다면 => 추가 구현해야함*/
                    findNavController().navigate(R.id.action_SignUpNextFragment_to_SignUpCompleteFragment)
                }


            }
        }

    }

    private fun startTimer() {
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(180000, 1000) { // 3분을 밀리초로 표현
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                binding.fragmentSignUpNextTimerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.fragmentSignUpNextTimerTextView.text = "00:00"
            }
        }
        countDownTimer?.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        binding.fragmentSignUpNextTimerTextView.text = "3:00"
    }

    override fun onStop() {
        super.onStop()
        stopTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpNextFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpNextFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
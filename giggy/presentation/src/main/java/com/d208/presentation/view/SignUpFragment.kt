package com.d208.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d208.presentation.R
import com.d208.presentation.base.BaseFragment
import com.d208.presentation.databinding.FragmentSignUpBinding
import com.d208.presentation.viewmodel.SignUpFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "SignUpFragment giggy"
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::bind, R.layout.fragment_sign_up) {
    // TODO: Rename and change types of parameters

    private val signUpFragmentViewModel : SignUpFragmentViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    fun init() {
        with(binding) {
            // 등록 후 홈 화면 이동
            fragmentSignUpButton.setOnClickListener {
                findNavController().navigate(R.id.action_SignUpFragment_to_SignUpNextFragment)
            }
            fragmentSignUpTargetAmountSlider.addOnChangeListener { slider, value, fromUser ->
                // Slider의 값이 변경되면 EditText에 업데이트합니다.
                Log.d("Slider", "$value 만원")
                fragmentSignUpTargetAmountEditText.text = "${value.toInt()} 만원"
            }
            // 금액 설정

        }
    }


}
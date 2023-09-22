package com.d208.giggy.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d208.giggy.R
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentLoginBinding
import com.d208.giggy.di.App
import com.d208.giggy.viewmodel.LoginFragmentViewModel
import com.d208.giggy.viewmodel.MainActivityViewModel
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "LoginFragment giggy"
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {
    // TODO: Rename and change types of parameters
    private val loginFragmentViewModel : LoginFragmentViewModel by viewModels()
    private val mainActivityViewModel : MainActivityViewModel by activityViewModels()
    private lateinit var mActivity : MainActivity
    // 카카오 로그인
    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            Log.i(TAG, "카카오톡으로 로그인 성공 : Refresh ${token.refreshToken}")
            binding.accessTokenTextView.text  =  "Access : \n ${token.accessToken}"
            binding.refreshTokenTextView.text =  "Refresh : \n ${token.refreshToken}"
            mainActivityViewModel.accessToken = token.accessToken
            mainActivityViewModel.refreshToken = token.refreshToken
            loginFragmentViewModel.login(token.accessToken,token.refreshToken, mainActivityViewModel.fcmToken!!)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = requireActivity() as MainActivity
        init()

    }
    fun init(){
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        //로그인 필요
                    }
                    else {
                        //기타 에러
                    }
                }
                else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                        if (error != null) {
                            Log.e(TAG, "토큰 정보 보기 실패", error)
                        }
                        else if (tokenInfo != null) {
                            Log.i(TAG, "토큰 정보 보기 성공" +
                                    "\n회원번호: ${tokenInfo.id}" +
                                    "\n만료시간: ${tokenInfo.expiresIn} 초")
                            if(tokenInfo.expiresIn > 0){
                                Log.d(TAG, "init: ${App.sharedPreferences.getString("id")}")
                                findNavController().navigate(R.id.action_LoginFragment_to_HomeFragment)
                            }
                        }
                    }
                }
            }
        }
        else {
            //로그인 필요
        }


        with(binding) {
            fragmentLoginKakaoButton.setOnClickListener {

                // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                    UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                        if (error != null) {
                            Log.e(TAG, "카카오톡으로 로그인 실패", error)

                            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                            // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }

                            // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                        } else if (token != null) {
                            Log.i(TAG, "카카오톡으로 로그인 성공 : Access ${token.accessToken}")
                            Log.i(TAG, "카카오톡으로 로그인 성공 : Refresh ${token.refreshToken}")
                            accessTokenTextView.text  =  "Access : \n ${token.accessToken}"
                            refreshTokenTextView.text =  "Refresh : \n ${token.refreshToken}"
                            mainActivityViewModel.accessToken = token.accessToken
                            mainActivityViewModel.refreshToken = token.refreshToken
                            loginFragmentViewModel.login(token.accessToken,token.refreshToken, mainActivityViewModel.fcmToken)
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                }


            }
        }




        loginFragmentViewModel.loginSuccess.observe(viewLifecycleOwner){

            if(it.email.isNullOrEmpty()){
                showSnackbar("통신 실패")
            }
            else{
                mainActivityViewModel.user = it
                if(!it.nickname.isNullOrEmpty()){
                    Log.d(TAG, "init: $it")
                    App.sharedPreferences.addId(it.id.toString())
                    Log.d(TAG, "initID: ${App.sharedPreferences.getString("id")}")
                    findNavController().navigate(R.id.action_LoginFragment_to_HomeFragment)
                }
                else{
                    findNavController().navigate(R.id.action_LoginFragment_to_SignUpFragment)
                }
            }


        }
    }


}
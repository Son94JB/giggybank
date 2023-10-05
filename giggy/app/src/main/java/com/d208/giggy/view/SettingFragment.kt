package com.d208.giggy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.d208.giggy.R
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentSettingBinding
import com.d208.giggy.di.App

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }
    fun init(){

        with(binding) {
            fragmentSettingLogout.setOnClickListener {
                App.sharedPreferences.removeUser()
                findNavController().navigate(R.id.action_SettingFragment_to_LoginFragment)
            }
        }
    }

}
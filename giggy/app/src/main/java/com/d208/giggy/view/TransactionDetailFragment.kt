package com.d208.giggy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d208.giggy.R
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentTransactionDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionDetailFragment : BaseFragment<FragmentTransactionDetailBinding>(FragmentTransactionDetailBinding::bind, R.layout.fragment_transaction_detail) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    fun init(){

    }
}
package com.d208.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.d208.domain.model.DomainTransaction
import com.d208.presentation.R
import com.d208.presentation.adapter.TransactionAdapater
import com.d208.presentation.base.BaseFragment
import com.d208.presentation.databinding.FragmentTransactionHistoryBinding
import com.d208.presentation.databinding.ItemTransactionBinding

import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionHistoryFragment : BaseFragment<FragmentTransactionHistoryBinding>(FragmentTransactionHistoryBinding::bind, R.layout.fragment_transaction_history) {

    private lateinit var adapter : TransactionAdapater
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TransactionAdapater(requireContext())
        init()
        test()
    }


    fun spinnerInit(){
        with(binding){
            fragmentTransactionHistorySpinner.setItems("전체 내역", "식비 내역", "교통 내역", "여가 내역", "쇼핑 내역", "기타 내역")
            fragmentTransactionHistorySpinner.setOnItemSelectedListener { view, position, id, item ->
                view.text = item.toString()
            }
            fragmentTransactionHistorySpinner.setOnNothingSelectedListener {

            }

        }
    }

    fun recyclerViewInit() = with(binding) {
        adapter.apply {
            itemClickListener = object : TransactionAdapater.ItemClickListener{
                override fun onClick(
                    binding: ItemTransactionBinding,
                    position: Int,
                    data: DomainTransaction
                ) {
                    showSnackbar("${data.content}") /**임시*/
                }

            }
        }
        fragmentTransactionHistoryRecyclerView.adapter = adapter
        fragmentTransactionHistoryRecyclerView.layoutManager =LinearLayoutManager(requireContext())
    }

    fun init(){
        recyclerViewInit()
        spinnerInit()
    }
    fun test() {
        var list = mutableListOf<DomainTransaction>()
        list.add(DomainTransaction(UUID.randomUUID(), "싸피 저녁", 1695186959, "출금", "식비", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "김싸피", 1695186959, "입금", "기타", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "9월 월세", 1695186959, "출금", "고정 지출", 300000))
        list.add(DomainTransaction(UUID.randomUUID(), "티머니 시외 버스", 1695186959, "출금", "교통", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "게임", 1695186959, "출금", "여가", 27800))
        list.add(DomainTransaction(UUID.randomUUID(), "싸피 저녁", 1695186959, "출금", "식비", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "김싸피", 1695186959, "입금", "기타", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "9월 월세", 1695186959, "출금", "고정 지출", 300000))
        list.add(DomainTransaction(UUID.randomUUID(), "티머니 시외 버스", 1695186959, "출금", "교통", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "게임", 1695186959, "출금", "여가", 27800))
        list.add(DomainTransaction(UUID.randomUUID(), "싸피 저녁", 1695186959, "출금", "식비", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "김싸피", 1695186959, "입금", "기타", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "9월 월세", 1695186959, "출금", "고정 지출", 300000))
        list.add(DomainTransaction(UUID.randomUUID(), "티머니 시외 버스", 1695186959, "출금", "교통", 7800))
        list.add(DomainTransaction(UUID.randomUUID(), "게임", 1695186959, "출금", "여가", 27800))
        adapter.submitList(list)
    }

}
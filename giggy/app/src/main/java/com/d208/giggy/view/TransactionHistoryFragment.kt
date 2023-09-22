package com.d208.giggy.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.d208.domain.model.DomainTransaction
import com.d208.domain.utils.StringFormatUtil
import com.d208.giggy.R
import com.d208.giggy.base.BaseFragment
import com.d208.giggy.databinding.FragmentTransactionHistoryBinding
import com.d208.giggy.databinding.ItemTransactionBinding
import com.d208.giggy.di.App
import com.d208.giggy.viewmodel.MainActivityViewModel
import com.d208.giggy.viewmodel.TransactionHistoryFragmentViewModel
import com.d208.presentation.adapter.TransactionAdapater
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.security.SecureRandom.getInstance
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import androidx.core.util.Pair
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import dagger.hilt.android.AndroidEntryPoint

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
private const val TAG = "TransactionHistoryFragm giggy"
@AndroidEntryPoint
class TransactionHistoryFragment : BaseFragment<FragmentTransactionHistoryBinding>(
    FragmentTransactionHistoryBinding::bind, R.layout.fragment_transaction_history) {

    private lateinit var adapter : TransactionAdapater
    private val transactionHistoryFragmentViewModel : TransactionHistoryFragmentViewModel by viewModels()
    private val mainActivityViewModel : MainActivityViewModel by activityViewModels()
    private var startDate = StringFormatUtil.dateToString(mainActivityViewModel.user.registerDate!!)
    private var endDate = StringFormatUtil.dateToString(System.currentTimeMillis())
    private val testList = mutableListOf<DomainTransaction>()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TransactionAdapater(requireContext())
        init()
//        test()
    }


    fun spinnerInit(){
        with(binding){
            fragmentTransactionHistorySpinner.setItems("전체 내역", "식품 내역", "교통 내역", "여가 내역", "쇼핑 내역", "고정지출 내역",  "기타 내역")
            fragmentTransactionHistorySpinner.setOnItemSelectedListener { view, position, id, item ->
                view.text = item.toString()
                when(item){
                    "전체 내역"->{
                        adapter.submitList(transactionHistoryFragmentViewModel.transactionList.value)
                    }
                    "식품 내역" ->{
                        adapter.submitList(transactionHistoryFragmentViewModel.transactionList.value?.filter { it.category == "식품" })
                    }
                    "교통 내역" ->{
                        adapter.submitList(transactionHistoryFragmentViewModel.transactionList.value?.filter { it.category == "교통" })
                    }
                    "여가 내역" ->{
                        adapter.submitList(transactionHistoryFragmentViewModel.transactionList.value?.filter { it.category == "여가" })
                    }
                    "쇼핑 내역" ->{
                        adapter.submitList(transactionHistoryFragmentViewModel.transactionList.value?.filter { it.category == "쇼핑" })
                    }
                    "고정지출 내역"-> {
                        adapter.submitList(transactionHistoryFragmentViewModel.transactionList.value?.filter { it.category == "고정지출" })
                    }
                    "기타 내역" ->{
                        adapter.submitList(transactionHistoryFragmentViewModel.transactionList.value?.filter { it.category == "기타" })
                    }
                }
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
                    mainActivityViewModel.selectedTransaction = data
                    findNavController().navigate(R.id.action_TransactionHistoryFragment_to_TransactionDetailFragment)
                }

            }
        }
        fragmentTransactionHistoryRecyclerView.adapter = adapter
        fragmentTransactionHistoryRecyclerView.layoutManager =LinearLayoutManager(requireContext())
    }

    fun init(){
        recyclerViewInit()
        spinnerInit()
        binding.fragmentTransactionHistoryBack.setOnClickListener {
            findNavController().navigateUp()
        }
        transactionHistoryFragmentViewModel.getTransactionData(App.sharedPreferences.getString("account"), startDate, endDate)
        transactionHistoryFragmentViewModel.transactionList.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                showSnackbar("불러올 거래내역이 없습니다.")
            }
            else{
                App.sharedPreferences.updateMoney(it[0].amount)
                adapter.submitList(it)
            }
        }

        // 날짜 변경
        binding.fragmentTransactionHistoryChangeDateButton.setOnClickListener {
           val dateRangePicker =  MaterialDatePicker.Builder.dateRangePicker()
               .setTitleText("거래 내역 구간 설정")
               .setCalendarConstraints(CalendarConstraints.Builder().setValidator(
                   DateValidatorPointBackward.now()
               ).build())
               .setCalendarConstraints(CalendarConstraints.Builder().setValidator(
                   DateValidatorPointForward.from(mainActivityViewModel.user.registerDate!!)
               ).build())
               .build()
            dateRangePicker.show(childFragmentManager, "date_picker")
            dateRangePicker.addOnPositiveButtonClickListener(object :
                MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>> {
                override fun onPositiveButtonClick(selection: Pair<Long, Long>?) {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = selection?.first ?: 0
                    startDate = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()
                    Log.d("start", startDate)

                    calendar.timeInMillis = selection?.second ?: 0
                    endDate = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()
                    Log.d("end", endDate)
                    transactionHistoryFragmentViewModel.getTransactionData(App.sharedPreferences.getString("account"), startDate, endDate)

                }
            })

        }
        transactionHistoryFragmentViewModel.exceptionHandler.observe(viewLifecycleOwner){
            when(it){
                0 -> {
                    showSnackbar("네트워크 오류")
                    findNavController().navigate(R.id.action_TransactionHistoryFragment_to_ErrorFragment)
                }
                401 ->
                {
                    showSnackbar("토큰 값이 만료되었습니다.")
                    findNavController().navigate(R.id.action_TransactionHistoryFragment_to_ErrorFragment)
                }
            }
        }
    }
    fun test() {
        testList.clear()
        testList.add(DomainTransaction(UUID.randomUUID(), "싸피 저녁", 1695186959, "출금", "식비", 7800, 5000, 0))
        testList.add(DomainTransaction(UUID.randomUUID(), "김싸피", 1695186959, "입금", "기타", 7800, 0, 5000))
        testList.add(DomainTransaction(UUID.randomUUID(), "9월 월세", 1695186959, "출금", "고정 지출", 300000, 0, 8000))
        testList.add(DomainTransaction(UUID.randomUUID(), "티머니 시외 버스", 1695186959, "출금", "교통", 7800, 0 , 7000))
        testList.add(DomainTransaction(UUID.randomUUID(), "게임", 1695186959, "출금", "여가", 27800, 0, 4000))
        testList.add(DomainTransaction(UUID.randomUUID(), "싸피 저녁", 1695186959, "출금", "식비", 7800, 0, 5000))
        testList.add(DomainTransaction(UUID.randomUUID(), "김싸피", 1695186959, "입금", "기타", 7800, 4000, 0))
        testList.add(DomainTransaction(UUID.randomUUID(), "9월 월세", 1695186959, "출금", "고정 지출", 300000, 0, 300000))
        testList.add(DomainTransaction(UUID.randomUUID(), "티머니 시외 버스", 1695186959, "출금", "교통", 7800, 0, 7800))
        testList.add(DomainTransaction(UUID.randomUUID(), "게임", 1695186959, "출금", "여가", 27800, 0, 27800))
        testList.add(DomainTransaction(UUID.randomUUID(), "싸피 저녁", 1695186959, "출금", "식비", 7800, 0, 7800 ))
        testList.add(DomainTransaction(UUID.randomUUID(), "김싸피", 1695186959, "입금", "기타", 7800, 7800, 0))
        testList.add(DomainTransaction(UUID.randomUUID(), "9월 월세", 1695186959, "출금", "고정 지출", 300000, 0, 30000))
        testList.add(DomainTransaction(UUID.randomUUID(), "티머니 시외 버스", 1695186959, "출금", "교통", 7800, 0, 7800))
        testList.add(DomainTransaction(UUID.randomUUID(), "게임", 1695186959, "출금", "여가", 27800, 0, 27800))
        adapter.submitList(testList)
    }

}
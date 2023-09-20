package com.d208.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d208.domain.model.DomainTransaction
import com.d208.domain.utils.StringFormatUtil
import com.d208.presentation.R
import com.d208.presentation.databinding.ItemTransactionBinding

class TransactionAdapater(var context : Context) : ListAdapter<DomainTransaction, TransactionAdapater.ItemViewHolder>(AdapterUtil.diffUtilTransaction) {
    inner class ItemViewHolder (var binding : ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : DomainTransaction) = with(binding){
            itemTransactionConsumeTitle.text = data.content
            when(data.category){
                "식비" -> {
                    itemTransactionCategoryImage.setImageResource(R.drawable.ic_food)
                }
                "교통" -> {
                    itemTransactionCategoryImage.setImageResource(R.drawable.ic_traffic)
                }
                "여가" -> {
                    itemTransactionCategoryImage.setImageResource(R.drawable.ic_leisure)
                }
                "쇼핑" -> {
                    itemTransactionCategoryImage.setImageResource(R.drawable.ic_shopping)
                }
                "고정지출" -> {
                    itemTransactionCategoryImage.setImageResource(R.drawable.ic_fixed_expenses)
                }
                "기타" -> {
                    itemTransactionCategoryImage.setImageResource(R.drawable.ic_etc)
                }
            }
            if(data.transactionType == "출금"){
                itemTransactionConsumeAmount.text = " - ${StringFormatUtil.moneyToWon(data.amount)}"
            }
            else{
                itemTransactionConsumeAmount.text = " + ${StringFormatUtil.moneyToWon(data.amount)}"
            }

            itemTransactionConsumeDate.text = StringFormatUtil.dateTimeToString(data.transactionDate)
            root.setOnClickListener {
                itemClickListener.onClick(binding, layoutPosition, data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    lateinit var itemClickListener: ItemClickListener
    interface ItemClickListener {
        fun onClick(binding: ItemTransactionBinding, position: Int, data: DomainTransaction)
    }

}
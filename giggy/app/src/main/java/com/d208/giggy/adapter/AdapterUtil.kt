package com.d208.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.d208.domain.model.DomainComment
import com.d208.domain.model.DomainPost
import com.d208.domain.model.DomainTransaction

object AdapterUtil {

    val diffUtilTransaction = object : DiffUtil.ItemCallback<DomainTransaction>() {
        override fun areItemsTheSame(oldItem: DomainTransaction, newItem: DomainTransaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DomainTransaction, newItem: DomainTransaction): Boolean {
            return oldItem == newItem
        }
    }

    val diffUtilPost = object : DiffUtil.ItemCallback<DomainPost>() {
        override fun areItemsTheSame(oldItem: DomainPost, newItem: DomainPost): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DomainPost, newItem: DomainPost): Boolean {
            return oldItem == newItem
        }
    }

    val diffUtilComment = object : DiffUtil.ItemCallback<DomainComment>() {
        override fun areItemsTheSame(oldItem: DomainComment, newItem: DomainComment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DomainComment, newItem: DomainComment): Boolean {
            return oldItem == newItem
        }

    }
}
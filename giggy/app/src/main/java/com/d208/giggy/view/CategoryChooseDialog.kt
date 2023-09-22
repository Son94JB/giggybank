package com.d208.giggy.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.d208.giggy.R

class CategoryChooseDialog(context : Context, var currentCategory : String, var id : Long, var fragment : Fragment) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_category_choose)

        val food = findViewById<LinearLayout>(R.id.dialog_category_choose_food_layout)
        val traffic = findViewById<LinearLayout>(R.id.dialog_category_choose_traffic_layout)
        val leisure= findViewById<LinearLayout>(R.id.dialog_category_choose_leisure_layout)
        val shopping = findViewById<LinearLayout>(R.id.dialog_category_choose_shopping_layout)
        val fixed = findViewById<LinearLayout>(R.id.dialog_category_choose_fixed_layout)
        val etc = findViewById<LinearLayout>(R.id.dialog_category_choose_etc_layout)

        when(currentCategory){
            "식품" -> food.setBackgroundColor(Color.GREEN)
            "교통" -> traffic.setBackgroundColor(Color.GREEN)
            "여가" -> leisure.setBackgroundColor(Color.GREEN)
            "고정지출" -> fixed.setBackgroundColor(Color.GREEN)
            "쇼핑" -> shopping.setBackgroundColor(Color.GREEN)
            "기타" -> etc.setBackgroundColor(Color.GREEN)
        }
        food.setOnClickListener {
            (fragment as TransactionDetailFragment).updateCategory(id, "식품")
            dismiss()
        }
        traffic.setOnClickListener {
            dismiss()
        }
        leisure.setOnClickListener {
            dismiss()
        }
        shopping.setOnClickListener {
            dismiss()
        }
        fixed.setOnClickListener {
            dismiss()
        }
        etc.setOnClickListener {
            dismiss()
        }



    }
}
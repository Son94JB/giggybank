package com.d208.presentation.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.d208.presentation.R
import com.d208.presentation.base.BaseFragment
import com.d208.presentation.databinding.FragmentConsumeAnalysisBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import java.util.Arrays
import java.util.LinkedList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsumeAnalysisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsumeAnalysisFragment : BaseFragment<FragmentConsumeAnalysisBinding>(FragmentConsumeAnalysisBinding::bind, R.layout.fragment_consume_analysis) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun chartInit(){
        with(binding){

            fragmentConsumeAnalysisChart.setUsePercentValues(true)

            // data Set
            val entries = ArrayList<PieEntry>()
            entries.add(PieEntry(508f, "쇼핑"))
            entries.add(PieEntry(600f, "여가"))
            entries.add(PieEntry(750f, "교통"))
            entries.add(PieEntry(508f, "식비"))
            entries.add(PieEntry(670f, "고정지출"))
            entries.add(PieEntry(200f, "기타"))

            // add a lot of colors
            val colorsItems = ArrayList<Int>()
            for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
            for (c in COLORFUL_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.MATERIAL_COLORS) colorsItems.add(c)
            colorsItems.add(ColorTemplate.getHoloBlue())

            val pieDataSet = PieDataSet(entries, "")


            pieDataSet.apply {
                colors = colorsItems
                valueTextColor = Color.BLACK
                valueTextSize = 32f
            }

            val pieData = PieData(pieDataSet)
            fragmentConsumeAnalysisChart.apply {
                data = pieData
                description.isEnabled = false
                legend.isEnabled = false
                centerText = "소비 패턴"
                setCenterTextSize(32f)
                setEntryLabelColor(Color.BLACK)
                animateY(1400, Easing.EaseInOutQuad)
                animate()
            }
        }
    }
    fun spinnerInit() {
//        val niceSpinner = binding.niceSpinner
//        val dataset: List<String> = LinkedList(Arrays.asList("2023.06", "2023.07", "2023.08", "2023.09", "2023.10"))
//        niceSpinner.attachDataSource(dataset)
//        niceSpinner.selectedIndex = 3
    }


    fun init(){
        spinnerInit()
        chartInit()
        with(binding){
            fragmentConsumeAnalysisBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


}
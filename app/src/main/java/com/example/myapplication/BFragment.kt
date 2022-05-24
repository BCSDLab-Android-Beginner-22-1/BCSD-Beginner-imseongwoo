package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val nList: Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val view: View = inflater.inflate(R.layout.fragment_b, container, false)
        val bFragmentText: TextView = view.findViewById(R.id.fragment_b_textview)
        bFragmentText.text = nList[requireArguments().getInt("pos")].toString()

        return view
    }
}
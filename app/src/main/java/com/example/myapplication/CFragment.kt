package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class CFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val aList = ArrayList<String>()
        val view: View = inflater.inflate(R.layout.fragment_c, container, false)
        val cFragmentText: TextView = view.findViewById(R.id.cFragmentTextView)
        for (i in 'A'..'Z') {
            aList.add(i.toString())
        }
        cFragmentText.text = aList[requireArguments().getInt("pos")]

        return view
    }
}
package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.text.FieldPosition

class AFragment(): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cList = mutableListOf<String>("#FF0000", "#FF7E00", "#FFFF00", "#75FF00", "#000080", "#0E0F37", "#B640FF")
        val view: View = inflater.inflate(R.layout.fragment_a,container,false)

        view.setBackgroundColor(Color.parseColor(cList[requireArguments().getInt("pos")]))
        return view
    }
}
package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import java.util.*
import kotlin.properties.Delegates

class RandomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_random, container, false)
        val randomText: TextView = rootView.findViewById(R.id.random_textview)
        val countNum = requireArguments().getInt("count")
        var randomNum: Int? = null
        randomNum = Random().nextInt(countNum.toInt() + 1)
        randomText.text = randomNum.toString()

        setFragmentResult("requestKey", bundleOf("count" to randomNum))

        return rootView
    }


}
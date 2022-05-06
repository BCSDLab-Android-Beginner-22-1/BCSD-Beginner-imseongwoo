package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.properties.Delegates

class RandomFragment : Fragment() {
    private var count: String? = null
    private var randomNum: Int? = null
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            count = it.getString("count")
        }

        val view = inflater.inflate(R.layout.fragment_random, container, false)
        val randomText: TextView = view.findViewById(R.id.random_textview)

        randomNum = Random().nextInt(count!!.toInt() + 1)
        randomText.text = randomNum.toString()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val rActivity = activity as RandomActivity
                rActivity.receiveData(randomNum.toString())
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this@RandomFragment)
                    ?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}
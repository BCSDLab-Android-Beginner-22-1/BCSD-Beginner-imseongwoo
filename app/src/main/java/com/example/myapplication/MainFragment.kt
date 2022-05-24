package com.example.myapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class MainFragment : Fragment() {
    private lateinit var countNum: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        val countBtn = rootView.findViewById<Button>(R.id.count_button)
        val toastBtn = rootView.findViewById<Button>(R.id.toast_button)
        val randomBtn = rootView.findViewById<Button>(R.id.random_button)
        var count = 0
        countNum = rootView.findViewById<TextView>(R.id.id_count_num)

        setFragmentResultListener("requestKey") { _, bundle ->
            count = bundle.getInt("count")
            countNum.text = count.toString()
        }

        toastBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Alert Dialog!")
                .setMessage("메세지 내용 부분")
                .setPositiveButton("positive",
                    DialogInterface.OnClickListener { dialog, id ->
                        countNum.text = "0"
                        count = 0

                    })
                .setNegativeButton("negative",
                    DialogInterface.OnClickListener { dialog, id ->
                    }
                )
                .setNeutralButton("neutral",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(requireContext(), "neutral 클릭", Toast.LENGTH_SHORT).show()
                    }

                )
            builder.show()
        }

        countBtn.setOnClickListener {
            count++
            countNum.text = count.toString()
        }

        randomBtn.setOnClickListener {
            val fragment: Fragment = RandomFragment()

            val bundle = Bundle().apply {
                putInt("count", count)
            }

            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit()
        }

        return rootView
    }

}
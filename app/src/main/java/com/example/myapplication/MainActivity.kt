package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = mutableListOf<String>()
        val editTextId = findViewById<EditText>(R.id.edittext_name)
        val plusButton = findViewById<Button>(R.id.plus_button)
        val editTextContent = editTextId.text
        val rv = findViewById<RecyclerView>(R.id.rv)

        plusButton.setOnClickListener {
            items.add(editTextContent.toString())
        }

        val rvAdapter = RVAdapter(items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

        rvAdapter.setOnItemClickListener(object: RVAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val dialog: AlertDialog = this@MainActivity.let {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(it)
                    builder.apply {
                        this.setMessage("${position + 1}번째 내용을 삭제하시겠습니까?")
                        this.setCancelable(false)
                        this.setPositiveButton("삭제") { dialog, _ ->
                            rvAdapter.dataDelete(position)
                        }
                        this.setNegativeButton("취소") { dialog, _ ->
                            dialog.cancel()
                        }
                    }
                    builder.create()
                }
                dialog.show()
            }

        })

        rvAdapter.setOnItemLongClickListener(object: RVAdapter.onItemLongClickListener{
            override fun onItemLongClick(position: Int) {
                val dialog = CustomDialog(this@MainActivity)
                dialog.showDia()
                dialog.setOnClickListener(object: CustomDialog.ButtonClickListener{
                    override fun onClicked(text: String) {
                        items[position] = text
                        rvAdapter.notifyDataSetChanged()

                    }

                })


            }

        })


    }
}





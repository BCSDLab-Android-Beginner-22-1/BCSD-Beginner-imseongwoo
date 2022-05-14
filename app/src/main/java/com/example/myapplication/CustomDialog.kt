package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class CustomDialog(context: Context) {
    private val dialog = Dialog(context)

    fun showDia(){
        dialog.setContentView(R.layout.edit_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val dialogEditText = dialog.findViewById<EditText>(R.id.custom_dialog_edittext)
        val okBtn = dialog.findViewById<Button>(R.id.button_ok)
        val cancelBtn = dialog.findViewById<Button>(R.id.button_cancel)

        okBtn.setOnClickListener {
            onClickListener.onClicked(dialogEditText.text.toString())
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    interface ButtonClickListener{
        fun onClicked(text: String)
    }

    private lateinit var onClickListener: ButtonClickListener

    fun setOnClickListener(listener: ButtonClickListener){
        onClickListener = listener
    }

}
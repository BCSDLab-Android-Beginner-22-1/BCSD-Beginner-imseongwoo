package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //ActivityResultLauncher<T>객체를 생성해주고 초기화 해준다.
    //T는 내가 호출할 엑티비티에서 결과값으로 받아올 자료형을 말한다.
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        var count = 0
        val countnum = findViewById<TextView>(R.id.id_count_num)
        val countbtn = findViewById<Button>(R.id.button_count)

        // toast 메시지 출력1
//        val buttontoast = findViewById<Button>(R.id.button_toast)
//        buttontoast.setOnClickListener { Toast.makeText(this,"토스트",Toast.LENGTH_SHORT).show() }

        // toast 메시지 출력2
        binding.buttonToast.setOnClickListener {
            Toast.makeText(this,"toast message",Toast.LENGTH_SHORT).show()
        }

        // count 구현 예정
//        binding.buttonCount.setOnClickListener {
//            count++
//            binding.idCountNum.setText(count)
//        }

        // count 구현
        countbtn.setOnClickListener {
            count++
            countnum.text = count.toString()
        }



        binding.buttonRandom.setOnClickListener(this)

        //RegisterActivityResult(Contract자료형, 콜백메서드)를 이용해서
        //ActivityResultLauncher를 초기화 해준다.
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {//Result 매개변수 콜백 메서드
                //ActivityResultLauncher<T>에서 T를 intent로 설정했으므로
                //intent자료형을 Result 매개변수(콜백)를 통해 받아온다
                //엑티비티에서 데이터를 갖고왔을 때만 실행
                if (it.resultCode == RESULT_OK) {
                    //SubActivity에서 갖고온 Intent(It)
                    val myData: Intent? = it.data
                    val address = it.data?.getStringExtra("KEY1") ?: ""
                    Log.e(TAG, address)
                }
            }


    }


    override fun onClick(v: View?) {

        when (v?.id) {
            //버튼을 누르면 메뉴 엑티비티가 실행되게 하였다.
            //launch메서드를 이용해 intent를 실행하고 새 엑티비티로부터 응답을받는다.
            //그리고 RequestCode가 사라졌다.
            binding.buttonRandom.id -> {
                val intent = Intent(applicationContext, RandomActivity::class.java)
                activityResultLauncher.launch(intent)
            }

            else -> {


            }
        }


    }


}
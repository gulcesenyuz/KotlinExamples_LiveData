package com.example.livedatakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val number:TextView=findViewById(R.id.tv_number)
        val btnStart:Button=findViewById(R.id.btn_add)
        val btnStop:Button=findViewById(R.id.btn_stop)
        val inputNumber:EditText=findViewById(R.id.et_input_number)
        val viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)


        viewModel.second().observe(this, Observer {
            number.text=it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if(it){
                Toast.makeText(this,"Finished ", Toast.LENGTH_SHORT).show()
            }
        })

        btnStart.setOnClickListener{
            if (inputNumber.text.isEmpty()||inputNumber.text.length<4){
                Toast.makeText(this,"Invalid Number", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.timerValue.value= inputNumber.text.toString().toLong()
                viewModel.startTimer()

            }

        }
        btnStop.setOnClickListener{
            viewModel.stopTimer()
        }
    }
}
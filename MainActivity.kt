package com.example.bmi_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weight = findViewById<EditText>(R.id.et_weight)
        val height = findViewById<EditText>(R.id.et_height)
        val calc = findViewById<Button>(R.id.btn_calculate)

        calc.setOnClickListener(){
            val w = weight.text.toString()
            val h = height.text.toString()
            if (validateinput(w,h)) {
                val bmi = w.toFloat() / ((h.toFloat() / 100) * (h.toFloat() / 100))
                val result = String.format("%.2f", bmi).toFloat()
                dispresult(result)
            }
        }
    }

    private fun validateinput(w:String?, h:String?):Boolean{
        return when{
            w.isNullOrEmpty()->{
                Toast.makeText(this,"Enter Weight", Toast.LENGTH_LONG).show()
                return false
            }
            h.isNullOrEmpty()->{
                Toast.makeText(this,"Enter Height", Toast.LENGTH_LONG).show()
                return false
            }
            else->{
                return true
            }
        }
    }

    private fun dispresult(bmi : Float){
        val r = findViewById<TextView>(R.id.tv_index)
        val des = findViewById<TextView>(R.id.tv1)
        val info = findViewById<TextView>(R.id.tv2)

        r.text = bmi.toString()
        var description = ""
        var colour = 0

        when{
            bmi<18.50 ->{
                description = "Under-weight"
                colour = R.color.under_w
            }
            bmi in 18.50..24.99 ->{
                description = "You are healthy!!"
                colour = R.color.normal
            }
            bmi in 15.00..29.99 ->{
                description = "Over-weight"
                colour = R.color.over_w
            }
            bmi>29.99 ->{
                description = "Obese"
                colour = R.color.obese
            }
        }
        des.setTextColor(ContextCompat.getColor(this, colour))
        des.text = description
    }
}
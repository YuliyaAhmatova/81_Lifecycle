package com.example.a81_lifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var heightET: EditText
    private lateinit var weightET: EditText
    private lateinit var calculateBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        heightET = findViewById(R.id.heightET)
        weightET = findViewById(R.id.weightET)
        calculateBTN = findViewById(R.id.calculateBTN)

        calculateBTN.setOnClickListener {
            if (heightET.text.isEmpty() || !heightET.text.isDigitsOnly() || weightET.text.isEmpty() || !weightET.text.isDigitsOnly()) {
                return@setOnClickListener
            }

            val result = calculatingTheIndex(
                heightET.text.toString().toDouble(),
                weightET.text.toString().toDouble()
            )
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", result.toString())
            startActivity(intent)
        }
    }

    private fun calculatingTheIndex(height: Double, weight: Double): Double {
        val index = weight / ((height / 100) * (height / 100))
        return index
    }
}
package com.example.a81_lifecycle

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var indexWeightBodyTV: TextView
    private lateinit var recommendationsTV: TextView

    private lateinit var bodyTypeIV: ImageView
    private lateinit var mainScreenBTN: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        indexWeightBodyTV = findViewById(R.id.indexWeightBodyTV)
        recommendationsTV = findViewById(R.id.recommendationsTV)
        bodyTypeIV = findViewById(R.id.bodyTypeIV)
        mainScreenBTN = findViewById(R.id.mainScreenBTN)

        val index = intent.getStringExtra("key")
        indexWeightBodyTV.text = "Индекс массы тела: $index"
        val bodyType = BodyType()
        when {
            index!!.toDouble() < 18.0 -> {
                recommendationsTV.text = bodyType.textThin
                bodyTypeIV.setImageResource(R.drawable.thin)
            }

            index.toDouble() in 18.0..24.9 -> {
                recommendationsTV.text = bodyType.textSlender
                bodyTypeIV.setImageResource(R.drawable.slender)
            }

            index.toDouble() > 24.9 -> {
                recommendationsTV.text = bodyType.textFull
                bodyTypeIV.setImageResource(R.drawable.full)
            }

            else -> recommendationsTV.text = ""
        }

        mainScreenBTN.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("key", "")
            startActivity(intent)
        }
    }
}

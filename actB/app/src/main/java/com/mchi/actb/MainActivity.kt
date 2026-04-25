package com.mchi.actb

import  android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var tilNom : TextInputLayout
    lateinit var edNom : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tilNom= findViewById(R.id.tilNombre)
        edNom = findViewById(R.id.etNombre)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tilNom.counterMaxLength=8
        tilNom.isCounterEnabled=true
        if(edNom.text.toString().trim().length >=8){
            Toast.makeText(this, "No mas de 8 p", Toast.LENGTH_LONG).show()
        }
    }
}
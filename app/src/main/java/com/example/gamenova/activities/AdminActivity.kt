package com.example.gamenova.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamenova.R

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        val btnProductos: TextView = findViewById(R.id.btnGestionProductos)

        btnProductos.setOnClickListener {
            val intent = Intent(this, AdminProductsActivity::class.java)
            startActivity(intent)
        }
    }
}
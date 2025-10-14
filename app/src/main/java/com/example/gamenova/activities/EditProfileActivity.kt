package com.example.gamenova.activities

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.gamenova.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val sharedPref = getSharedPreferences("datos", MODE_PRIVATE)

        val etNombres = findViewById<EditText>(R.id.editNombres)
        val etApellidos = findViewById<EditText>(R.id.editApellidos)
        val etTelefono = findViewById<EditText>(R.id.editTelefono)
        val etCorreo = findViewById<EditText>(R.id.editCorreo)

        // precargar datos
        etNombres.setText(sharedPref.getString("nombres", ""))
        etApellidos.setText(sharedPref.getString("apellidos", ""))
        etTelefono.setText(sharedPref.getString("telefono", ""))
        etCorreo.setText(sharedPref.getString("correo", ""))

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            sharedPref.edit {
                putString("nombres", etNombres.text.toString())
                putString("apellidos", etApellidos.text.toString())
                putString("telefono", etTelefono.text.toString())
                putString("correo", etCorreo.text.toString())
            }

            finish() // volver al perfil
        }


    }
}
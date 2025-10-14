package com.example.gamenova.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gamenova.R

class RecuperationActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperation)

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnRecuperar = findViewById<Button>(R.id.solicitud)
        val volver = findViewById<Button>(R.id.volver)



        sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)

        volver.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnRecuperar.setOnClickListener {
            val correoIngresado = etCorreo.text.toString().trim()
            val correoGuardado = sharedPreferences.getString("correo", null)
            val contraseñaGuardada = sharedPreferences.getString("contraseña", null)

            if (correoIngresado.isEmpty()) {
                Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (correoGuardado == null) {
                Toast.makeText(this, "No hay cuentas registradas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (correoIngresado == correoGuardado) {
                // Mostrar contraseña en un AlertDialog por seguridad mínima
                AlertDialog.Builder(this)
                    .setTitle("Recuperación de contraseña")
                    .setMessage("Tu contraseña registrada es:\n\n$contraseñaGuardada")
                    .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
                    .show()
            } else {
                Toast.makeText(this, "Correo no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
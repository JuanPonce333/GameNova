package com.example.gamenova.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.gamenova.R
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registrarse: TextView = findViewById(R.id.sinCuenta)
        registrarse.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        val recuperar: TextView = findViewById(R.id.recuperarContraseña)
        recuperar.setOnClickListener {
            startActivity(Intent(this, RecuperationActivity::class.java))
        }

        val etUsuario = findViewById<EditText>(R.id.usuario)
        val etContraseña = findViewById<EditText>(R.id.contraseña)
        val btnIngresar = findViewById<Button>(R.id.ingresar)

        sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)

        btnIngresar.setOnClickListener {
            comparacionLogin(etUsuario, etContraseña)
        }
    }

    private fun comparacionLogin(etUsuario: EditText, etContraseña: EditText) {
        val usuario = etUsuario.text.toString().trim()
        val contraseña = etContraseña.text.toString().trim()


        // 🧱 Validación de campos vacíos
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val savedUsuario = sharedPreferences.getString("correo", null)
        val savedContraseña = sharedPreferences.getString("contraseña", null)
        val savedRol = sharedPreferences.getString("rol", "usuario")

        if (savedUsuario.isNullOrEmpty() || savedContraseña.isNullOrEmpty()) {
            Toast.makeText(this, "Primero debe registrarse", Toast.LENGTH_SHORT).show()
            return
        }

        if (usuario == savedUsuario && contraseña == savedContraseña) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            if (savedRol == "admin"){
                val intent = Intent(this, AdminProductsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}
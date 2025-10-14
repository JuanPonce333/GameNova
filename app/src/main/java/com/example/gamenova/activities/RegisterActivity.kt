package com.example.gamenova.activities


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamenova.R


class RegisterActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tienesCuenta: TextView = findViewById(R.id.tienesCuenta)

        tienesCuenta.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        val etNombres = findViewById<EditText>(R.id.etNombres)
        val etApellidos = findViewById<EditText>(R.id.etApellidos)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContraseña = findViewById<EditText>(R.id.etContraseña)
        val etConfirmarContraseña = findViewById<EditText>(R.id.etConfirmar)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val btnRegistro = findViewById<Button>(R.id.registrarse)

        btnRegistro.setOnClickListener {
            val nombres = etNombres.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contraseña = etContraseña.text.toString().trim()
            val confirmar = etConfirmarContraseña.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val checkBoxTerminos = findViewById<CheckBox>(R.id.aceptarTerminos)

            if (validarCampos(nombres, apellidos, correo, contraseña, confirmar, telefono, checkBoxTerminos)) {
                guardarDatos(nombres, apellidos, correo, contraseña, telefono)

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun validarCampos(
        nombres: String,
        apellidos: String,
        correo: String,
        contraseña: String,
        confirmar: String,
        telefono: String,
        checkBoxTerminos: CheckBox
    ): Boolean {
        if (nombres.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if (apellidos.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (correo.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su correo", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (contraseña.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }
        if (confirmar.isEmpty()){
            Toast.makeText(this, "Confirme su contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (contraseña != confirmar){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        if (telefono.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su teléfono", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!telefono.matches(Regex("\\d{7,10}"))) {
            Toast.makeText(this, "Teléfono inválido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!checkBoxTerminos.isChecked){
            Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun guardarDatos(
        nombres: String,
        apellidos: String,
        correo: String,
        contraseña: String,
        telefono: String
    ) {
        val editor = sharedPreferences.edit()

        if (correo == "admin@gamenova.com" && contraseña == "admin123") {
            editor.putString("rol", "admin")
        } else {
            editor.putString("rol", "usuario")
        }

        editor.putString("nombres", nombres)
        editor.putString("apellidos", apellidos)
        editor.putString("correo", correo)
        editor.putString("contraseña", contraseña)
        editor.putString("telefono", telefono)

        editor.apply()
    }
}
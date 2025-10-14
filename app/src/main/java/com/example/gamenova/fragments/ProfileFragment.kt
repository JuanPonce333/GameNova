package com.example.gamenova.fragments

import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.gamenova.R
import com.example.gamenova.activities.EditProfileActivity

class ProfileFragment : Fragment() {
    private lateinit var tvNombres: TextView
    private lateinit var tvApellidos: TextView
    private lateinit var tvTelefono: TextView
    private lateinit var tvCorreo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        tvNombres = view.findViewById(R.id.nombres)
        tvApellidos = view.findViewById(R.id.apellidos)
        tvTelefono = view.findViewById(R.id.telefono)
        tvCorreo = view.findViewById(R.id.correo)

        val btnEditar = view.findViewById<Button>(R.id.editarPerfil)
        btnEditar.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        // Cargar datos iniciales
        cargarDatos()

        return view
    }

    override fun onResume() {
        super.onResume()
        // Recargar datos cada vez que el usuario regrese
        cargarDatos()
    }

    private fun cargarDatos() {
        val sharedPref = requireActivity().getSharedPreferences("datos", 0)

        tvNombres.text = sharedPref.getString("nombres", "No definido")
        tvApellidos.text = sharedPref.getString("apellidos", "No definido")
        tvTelefono.text = sharedPref.getString("telefono", "No definido")
        tvCorreo.text = sharedPref.getString("correo", "No definido")
    }
}
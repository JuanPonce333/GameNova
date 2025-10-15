package com.example.gamenova.activities

import android.widget.ImageView
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamenova.R
import com.example.gamenova.utils.DataManager

class ProductoDetalleActivity : AppCompatActivity() {

    private lateinit var imgProducto: ImageView
    private lateinit var txtNombre: TextView
    private lateinit var txtPrecio: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtDisponibilidad: TextView
    private lateinit var btnAgregarCarrito: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        imgProducto = findViewById(R.id.imgProducto)
        txtNombre = findViewById(R.id.tvNombreProducto)
        txtPrecio = findViewById(R.id.tvPrecioProducto)
        txtDescripcion = findViewById(R.id.tvDescripcion)
        txtDisponibilidad = findViewById(R.id.tvDisponibilidad)
        btnAgregarCarrito = findViewById(R.id.btnAgregarCarrito)

        val idProducto = intent.getIntExtra("id", -1)
        val producto = DataManager.listaProductos.find { it.id == idProducto }

        if (producto != null) {
            txtNombre.text = producto.nombre
            txtPrecio.text = "$${producto.precio}"
            txtDescripcion.text = producto.descripcion

            btnAgregarCarrito.setOnClickListener {
                DataManager.carrito.add(producto)
                Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Error al cargar producto", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}


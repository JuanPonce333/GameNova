package com.example.gamenova.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenova.R
import com.google.android.material.button.MaterialButton

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerCarrito: RecyclerView
    private lateinit var totalTextView: TextView
    private lateinit var btnPagar: MaterialButton

    private val listaCarrito = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerCarrito = findViewById(R.id.recyclerCarrito)
        totalTextView = findViewById(R.id.tvTotal)
        btnPagar = findViewById(R.id.btnFinalizarCompra)

        recyclerCarrito.layoutManager = LinearLayoutManager(this)

        cargarCarrito()

        btnPagar.setOnClickListener {
            if (listaCarrito.isNotEmpty()) {
                Toast.makeText(this, "Procesando compra...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cargarCarrito() {
        listaCarrito.clear()
        listaCarrito.addAll(DataManager.carrito)

        val adapter = ProductoAdapter(
            listaCarrito.toMutableList(),
            onItemClick = {},
            onEliminarClick = { producto -> eliminarProducto(producto) },
            mostrarBotonEliminar = true // 👈 mostrar botón
        )

        recyclerCarrito.adapter = adapter
        actualizarTotal()
    }

    private fun eliminarProducto(producto: Producto) {
        DataManager.carrito.remove(producto)
        listaCarrito.remove(producto)
        recyclerCarrito.adapter?.notifyDataSetChanged()
        actualizarTotal()
        Toast.makeText(this, "${producto.nombre} eliminado", Toast.LENGTH_SHORT).show()
    }

    private fun actualizarTotal() {
        val total = listaCarrito.sumOf { it.precio }
        totalTextView.text = "Total: $${String.format("%,.0f", total)}"
    }
}

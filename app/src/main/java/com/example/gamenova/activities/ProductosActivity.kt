package com.example.gamenova.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenova.R

class ProductosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private val listaProductos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Datos de prueba (luego los cargarás desde SharedPreferences)
        listaProductos.add(
            Producto(1, "The Legend of Zelda: Tears of the Kingdom", 59.99, "Juego de aventura para Nintendo Switch", true)
        )
        listaProductos.add(
            Producto(2, "God of War: Ragnarok", 69.99, "Juego de acción para PS5", false)
        )

        adapter = ProductoAdapter(
            listaProductos = listaProductos,
            onItemClick = { producto ->
                val intent = Intent(this, ProductoDetalleActivity::class.java)
                intent.putExtra("id", producto.id)
                startActivity(intent)
            },
            onEliminarClick = null,
            mostrarBotonEliminar = false
        )

        recyclerView.adapter = adapter
    }
}
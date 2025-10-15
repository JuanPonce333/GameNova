package com.example.gamenova.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenova.R
import com.example.gamenova.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            val header = findViewById<LinearLayout>(R.id.header_layout)
            header.setPadding(
                header.paddingLeft,
                systemBars.top,
                header.paddingRight,
                header.paddingBottom
            )
            insets
        }

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        val btnMenu = findViewById<ImageView>(R.id.btnMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.main)

        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val carrito: ImageView = findViewById(R.id.carrito)
        carrito.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.popBackStack()
                    true
                }

                R.id.action_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .addToBackStack("profile")
                        .commit()
                    true
                }

                else -> false
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaProductos = mutableListOf<Producto>()

        listaProductos.add(
            Producto(1, "The Legend of Zelda: Tears of the Kingdom", 59.99, "Juego de aventura para Nintendo Switch", true)
        )
        listaProductos.add(
            Producto(2, "God of War: Ragnarok", 69.99, "Juego de acción para PS5", false)
        )

        val adapter = ProductoAdapter(
            listaProductos,
            onItemClick = { producto ->
                val intent = Intent(this, ProductoDetalleActivity::class.java)
                intent.putExtra("id", producto.id)
                intent.putExtra("nombre", producto.nombre)
                intent.putExtra("precio", producto.precio)
                intent.putExtra("descripcion", producto.descripcion)
                intent.putExtra("disponible", producto.disponible)
                startActivity(intent)
            },
            onEliminarClick = null,   // 👈 no se usa en Main
            mostrarBotonEliminar = false // 👈 botón oculto en Main
        )

        recyclerView.adapter = adapter
    }
}

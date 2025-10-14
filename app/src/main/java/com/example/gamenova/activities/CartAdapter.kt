package com.example.gamenova.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenova.R

class CartAdapter(
    private val productos: MutableList<Producto>,
    private val onEliminarClick: (Producto) -> Unit,
    private val mostrarBotonEliminar: Boolean = false
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.txtNombreProducto)
        val precio: TextView = view.findViewById(R.id.txtPrecioProducto)
        val eliminarBtn: Button = view.findViewById(R.id.btnEliminarProducto2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = "$${producto.precio}"

        holder.eliminarBtn.visibility = if (mostrarBotonEliminar) View.VISIBLE else View.GONE

        holder.eliminarBtn.setOnClickListener {
            onEliminarClick(producto)
        }
    }

    override fun getItemCount(): Int = productos.size

    // helper por si quieres actualizar la lista desde actividad
    fun updateList(newList: List<Producto>) {
        productos.clear()
        productos.addAll(newList)
        notifyDataSetChanged()
    }
}
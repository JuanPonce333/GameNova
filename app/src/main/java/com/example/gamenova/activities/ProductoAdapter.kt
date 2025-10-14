package com.example.gamenova.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenova.R

class ProductoAdapter(
    private val listaProductos: List<Producto>,
    private val onItemClick: (Producto) -> Unit,
    private val onEliminarClick: ((Producto) -> Unit)? = null, // 👈 opcional
    private val mostrarBotonEliminar: Boolean = false          // 👈 control de visibilidad
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto2)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreProducto)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecioProducto)
        val txtDisponibilidad: TextView = itemView.findViewById(R.id.txtDisponibilidad)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminarProducto2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_product, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = "$${producto.precio}"
        holder.txtDisponibilidad.text = if (producto.disponible) "En stock" else "Agotado"

        holder.imgProducto.setImageResource(R.drawable.ic_launcher_foreground)

        // 👇 Control de visibilidad del botón eliminar
        holder.btnEliminar.visibility = if (mostrarBotonEliminar) View.VISIBLE else View.GONE

        // 👇 Clic del botón eliminar (solo si se pasa la lambda)
        holder.btnEliminar.setOnClickListener {
            onEliminarClick?.invoke(producto)
        }

        // 👇 Clic en el producto
        holder.itemView.setOnClickListener {
            onItemClick(producto)
        }
    }

    override fun getItemCount(): Int = listaProductos.size
}

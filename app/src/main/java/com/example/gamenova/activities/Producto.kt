package com.example.gamenova.activities

data class Producto(
    val id: Int,
    var nombre: String,
    var precio: Double,
    var descripcion: String,
    var disponible: Boolean,
    var imagen: String? = null // aquí guardaremos el nombre del recurso o una URI si más adelante usas imágenes personalizadas
)
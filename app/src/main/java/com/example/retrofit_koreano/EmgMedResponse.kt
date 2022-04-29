package com.example.retrofit_koreano


import com.squareup.moshi.Json

data class EmgMedResponse(
    @field:Json(name = "EmgMedInfo")
    val emgMedInfo: List<EmgMedInfo>
)


data class EmgMedInfo(
    val row: List<Row?>
)


data class Row(
    @field: Json(name = "Calificacion")
    val calificacion: String?,
    @field: Json(name = "Costo_Av")
    val costoAv: String?,
    @field: Json(name = "direcc")
    val direcc: String?,
    @field: Json(name = "Fundacion")
    val fundacion: String?,
    @field: Json(name = "galeria")
    val galeria: List<String>?,
    @field: Json(name = "id")
    val id: Int?,
    @field: Json(name = "Imagen")
    val imagen: String?,
    @field: Json(name = "Nombre_rest")
    val nombreRest: String?,
    @field: Json(name = "reseñas")
    val reseñas: String?
)
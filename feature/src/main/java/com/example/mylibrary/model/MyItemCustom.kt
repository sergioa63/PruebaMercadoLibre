package com.example.mylibrary.model

data class MyItemCustom(
    val id: String = "",
    val title: String = "",
    val thumb: String = "",
    var price: String = "",
    var descrip: String = "",
    var selected: Boolean = false,
)

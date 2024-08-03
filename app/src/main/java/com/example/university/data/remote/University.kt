package com.example.university.data.remote

data class University(
    val name: String,
    val country: String,
    val alpha_two_code: String,
    val web_pages: List<String>
)

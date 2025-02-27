package com.example.tugas_uas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manhwa(
    val name: String,
    val genre: String,
    val deskripsi: String,
    val penulis: String,
    val rating: Float,
    val photo: Int
) : Parcelable

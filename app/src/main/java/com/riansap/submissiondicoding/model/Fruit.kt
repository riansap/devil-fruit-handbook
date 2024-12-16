package com.riansap.submissiondicoding.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val id: String,
    val name: String?,
    val roman_name: String?,
    val description: String?,
    val type: String?,
    val filename: String?
) : Parcelable

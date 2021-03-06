package com.github.UAS0678.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val login : String,
    val id : Int,
    val avatar_url : String,
    val html_url : String
): Parcelable

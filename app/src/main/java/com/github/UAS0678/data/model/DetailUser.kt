package com.github.UAS0678.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUser(
    val login: String,
    val id: Int,
    val html_url: String,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name: String,
    val public_repos: Int,
    val following: Int,
    val followers: Int
) : Parcelable

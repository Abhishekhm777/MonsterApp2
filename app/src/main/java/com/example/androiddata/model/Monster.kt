package com.example.androiddata.model

import com.example.androiddata.IMAGE_BSEURL
import com.squareup.moshi.Json

data class Monster(
    val imageFile :String,
    val monsterName :String,
    val caption :String,
    val description :String,
    val price :Double,
    val scariness : Int
 )
{
    val imageUrl
    get() = "$IMAGE_BSEURL/$imageFile.webp"

    val thumbnailUrl
        get() = "$IMAGE_BSEURL/${imageFile}_tn.webp"
}


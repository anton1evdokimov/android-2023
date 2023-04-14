package com.test.april.domain

data class ShopItem(
    val name: String, val count: Int, val enabled: Boolean, var Id: Int = DEFAULT_ID
){
    companion object{
        const val DEFAULT_ID = -1
    }
}

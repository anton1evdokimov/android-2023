package com.test.april.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.april.domain.ShopItem
import com.test.april.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for(i in 0 until 10){
            val item = ShopItem("Элемент $i", i*i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.Id == ShopItem.DEFAULT_ID) {
            shopItem.Id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateData( )
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateData()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.Id)
        deleteShopItem(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.Id == shopItemId }
            ?: throw  java.lang.RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateData(){
        shopListLD.value = shopList.toList()
    }
}
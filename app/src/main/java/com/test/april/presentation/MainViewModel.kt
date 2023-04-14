package com.test.april.presentation

import androidx.lifecycle.ViewModel
import com.test.april.data.ShopListRepositoryImpl
import com.test.april.domain.DeleteShopItemUseCase
import com.test.april.domain.EditShopItemUseCase
import com.test.april.domain.GetShopListUseCase
import com.test.april.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(item: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(item)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}
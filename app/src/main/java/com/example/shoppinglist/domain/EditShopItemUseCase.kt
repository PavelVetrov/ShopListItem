package com.example.shoppinglist.domain

import androidx.room.Index
import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository) {

   suspend fun editShopItem(shopItem: ShopItem){

        shopListRepository.editShopItem(shopItem)

    }
}
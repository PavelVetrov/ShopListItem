package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModal(shopItem: ShopItem): ShopItemDbModal {

        return ShopItemDbModal(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled
        )
    }

    fun mapDbModalEntityTo(shopItemDbModal: ShopItemDbModal): ShopItem {

        return ShopItem(
            id = shopItemDbModal.id,
            name = shopItemDbModal.name,
            count = shopItemDbModal.count,
            enabled = shopItemDbModal.enabled
        )
    }

    fun mapListDbModalToEntity(list: List<ShopItemDbModal>) = list.map {
        mapDbModalEntityTo(it)
    }
}
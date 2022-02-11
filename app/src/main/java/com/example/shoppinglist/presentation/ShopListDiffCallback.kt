package com.example.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.domain.ShopItem

class ShopListDiffCallback(
    private val oldShopList: List<ShopItem>,
    private val newShopList: List<ShopItem>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldShopList.size
    }

    override fun getNewListSize(): Int {
        return newShopList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = oldShopList[oldItemPosition]
        val newList = newShopList[newItemPosition]
        return oldList.id == newList.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = oldShopList[oldItemPosition]
        val newList = newShopList[newItemPosition]
        return oldList == newList
    }
}
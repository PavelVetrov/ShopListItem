package com.example.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.presentation.MainActivityViewModal
import com.example.shoppinglist.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModalModule {

    @Binds
    @IntoMap
    @ViewModalKey(MainActivityViewModal::class)
    fun bindMainViewModal(viewModal: MainActivityViewModal): ViewModel

    @Binds
    @IntoMap
    @ViewModalKey(ShopItemViewModel::class)
    fun bindFragmentViewModal(viewModal: ShopItemViewModel): ViewModel
}
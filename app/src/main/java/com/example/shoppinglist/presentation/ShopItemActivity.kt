package com.example.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.databinding.ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.example.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityShopItemBinding
import com.example.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ShopItemActivity : AppCompatActivity() {

    private lateinit var viewModel: ShopItemViewModel
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    private lateinit var binding: ActivityShopItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        addTextChangeListeners()
        launchRightMode()
        observeViewModal()

    }
    private fun observeViewModal(){
        viewModel.errorInputCount.observe(this) {
            val message = if (it)
                getString(R.string.error_Input_count)
            else null

            binding.tillCount.error = message

        }
        viewModel.errorInputName.observe(this) {
            val message = if (it)
                getString(R.string.error_Input_name)
            else null

            binding.tillName.error = message

        }
        viewModel.shouldCloseScreen.observe(this){
            finish()
        }

    }

    private fun launchRightMode(){
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }

    }

    private fun addTextChangeListeners(){
        binding.etName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }
            override fun afterTextChanged(p0: Editable?) {}

        })
        binding.etCount.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }
            override fun afterTextChanged(p0: Editable?) {}

        })

    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addShopItem(binding.etName.text?.toString(), binding.etCount.text?.toString())
        }

    }

    private fun launchEditMode() {

        viewModel.getShopItem(shopItemId)
        viewModel.shopItem.observe(this) {
            binding.etName.setText(it.name)
            binding.etCount.setText(it.count.toString())
        }
        binding.saveButton.setOnClickListener {
            viewModel.editShopItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }

    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_ID_SHOP_ITEM)) {
                throw RuntimeException("Param mode is  id absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_ID_SHOP_ITEM, ShopItem.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra mode"
        private const val EXTRA_ID_SHOP_ITEM = "extra id shop item"
        private const val MODE_ADD = "mode add"
        private const val MODE_EDIT = "mode edit"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent

        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_ID_SHOP_ITEM, shopItemId)
            return intent
        }
    }

}
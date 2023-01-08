package com.xsavzh.musicshop

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xsavzh.musicshop.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding

    private val addresses: String = "drockick@gmail.com"
    private val subject: String = "Order from Music Shop"

    private lateinit var userName: String
    private lateinit var goodsTitle: String
    private var quantity: Int = 0
    private var price: Int = 0
    private var orderPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Your Order"

        getData()
        setValue()

        binding.submitOrderButton.setOnClickListener {
            submitOrder()
        }
    }

    private fun getData() {
        userName = intent.getStringExtra("userName").toString()
        goodsTitle = intent.getStringExtra("goodsTitle").toString()
        quantity = intent.getIntExtra("quantity", 0)
        price = intent.getIntExtra("price", 0)
        orderPrice = intent.getIntExtra("orderPrice", 0)
    }

    @SuppressLint("SetTextI18n")
    private fun setValue() {
        binding.userNameTextView.text = "User name: $userName"
        binding.goodsTitleTextView.text = "Goods title: $goodsTitle"
        binding.quantityTextView.text = "Quantity: $quantity"
        binding.priceTextView.text = "Price: $price"
        binding.orderPriceTextView.text = "Order price: $orderPrice"
    }

    private fun submitOrder() {
        val emailText: String = "User name: $userName\n" +
                "Goods title: $goodsTitle\n" +
                "Quantity: $quantity\n" +
                "Price: $price\n" +
                "Order price: $orderPrice"

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, emailText)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
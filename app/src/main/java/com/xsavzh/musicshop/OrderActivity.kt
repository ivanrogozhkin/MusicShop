package com.xsavzh.musicshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xsavzh.musicshop.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedOrderIntent = intent
        userName = receivedOrderIntent.getStringExtra("userName").toString()
        binding.orderTextView.text = userName
    }
}
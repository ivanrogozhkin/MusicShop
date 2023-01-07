package com.xsavzh.musicshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xsavzh.musicshop.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("userName")!!

        binding.orderTextView.text = userName
    }
}
package com.xsavzh.musicshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.xsavzh.musicshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    private var quantity = 0
    private val spinnerArrayList: ArrayList<String> = ArrayList()
    private val goodsMap: HashMap<String, Int> = HashMap()
    private lateinit var goodsTitle: String
    private var price: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createSpinner()
        createMap()
        buttonsActions()
    }

    private fun createSpinner() {
        spinnerArrayList.add("Guitar")
        spinnerArrayList.add("Bass guitar")
        spinnerArrayList.add("Drums")

        val spinnerAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList)
        spinnerAdapter.dropDownViewTheme
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = this
    }

    private fun createMap() {
        goodsMap["Guitar"] = 500
        goodsMap["Bass guitar"] = 750
        goodsMap["Drums"] = 1000
    }

    private fun buttonsActions() {
        binding.plusButton.setOnClickListener {
            quantity++
            binding.quantityTextView.text = quantity.toString()
            binding.priceTextView.text = (quantity*price).toString()
        }

        binding.minusButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                binding.quantityTextView.text = quantity.toString()
            }
            binding.priceTextView.text = (quantity*price).toString()
        }

        binding.addToCartButton.setOnClickListener {
            val order = Order()

            order.userName = binding.nameEditText.text.toString()
            order.goodsTitle = goodsTitle
            order.quantity = quantity
            order.price = price
            order.orderPrice = quantity*price

            val orderIntent = Intent(this, OrderActivity::class.java)
            orderIntent.putExtra("userName", order.userName)
            orderIntent.putExtra("goodsTitle", order.goodsTitle)
            orderIntent.putExtra("quantity", order.quantity)
            orderIntent.putExtra("price", order.price)
            orderIntent.putExtra("orderPrice", order.orderPrice)
            startActivity(orderIntent)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        goodsTitle = binding.spinner.getItemAtPosition(position).toString()
        Toast.makeText(this, goodsTitle, Toast.LENGTH_LONG).show()
        price = goodsMap[goodsTitle]!!
        binding.priceTextView.text = (quantity*price).toString()

        when(goodsTitle) {
            "Guitar" -> binding.goodsImageView.setImageResource(R.drawable.guitar)
            "Bass guitar" -> binding.goodsImageView.setImageResource(R.drawable.bass)
            "Drums" -> binding.goodsImageView.setImageResource(R.drawable.drums)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}
package com.xsavzh.musicshop

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

        binding.plusButton.setOnClickListener {
            quantity++
            binding.quantityTextView.text = quantity.toString()
        }

        binding.minusButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                binding.quantityTextView.text = quantity.toString()
            }
        }

        spinnerArrayList.add("Guitar")
        spinnerArrayList.add("Bass guitar")
        spinnerArrayList.add("Drums")

        val spinnerAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList)
        spinnerAdapter.dropDownViewTheme
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = this

        goodsMap["Guitar"] = 500
        goodsMap["Bass guitar"] = 750
        goodsMap["Drums"] = 1000
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        goodsTitle = binding.spinner.getItemAtPosition(position).toString()
        Toast.makeText(this, goodsTitle, Toast.LENGTH_LONG).show()
        price = goodsMap[goodsTitle]!!
        binding.priceTextView.text = (quantity*price).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}
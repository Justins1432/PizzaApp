package com.vzudkin.pizzaapp.presenation.menu.ui.products

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vzudkin.pizzaapp.R
import com.vzudkin.pizzaapp.databinding.FragmentMenuBinding
import com.vzudkin.pizzaapp.data.model.Discount
import com.vzudkin.pizzaapp.data.model.Product
import com.vzudkin.pizzaapp.data.model.Tab
import com.vzudkin.pizzaapp.presenation.adapter.DiscountAdapter
import com.vzudkin.pizzaapp.presenation.adapter.ProductAdapter
import com.vzudkin.pizzaapp.presenation.adapter.TabAdapter

class MenuFragment : Fragment() {
    private lateinit var recyclerViewTabs: RecyclerView

    private lateinit var recyclerViewDiscount: RecyclerView
    private val discountAdapter = DiscountAdapter()

    private lateinit var recyclerViewProducts: RecyclerView
    private val listProduct = ArrayList<Product>()
    private val productAdapter: ProductAdapter = ProductAdapter(listProduct)

    private lateinit var menuViewModel: MenuViewModel
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var list: ArrayList<Tab>

    private lateinit var nestedScrollView: NestedScrollView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        menuViewModel =
            ViewModelProvider(this)[MenuViewModel::class.java]
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        list = ArrayList()
        list.addAll(tabs())

        val toolbar: Toolbar = binding.root.findViewById(R.id.toolbarLayout)
        nestedScrollView = binding.root.findViewById(R.id.nestedScrollView)

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
        (activity as AppCompatActivity).supportActionBar?.title = ""

        initRecyclerViewDiscount()
        initRecyclerViewProducts()
        initRecyclerViewTabs()

        discounts()
        products()

        return binding.root
    }

    private fun initRecyclerViewDiscount() {
        recyclerViewDiscount = binding.root.findViewById(R.id.discount_recycler_view_item)
        recyclerViewDiscount.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewDiscount.adapter = discountAdapter
    }

    private fun initRecyclerViewProducts() {
        recyclerViewProducts = binding.root.findViewById(R.id.products_recycler_view_item)
        recyclerViewProducts.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        recyclerViewProducts.adapter = productAdapter
    }

    private fun initRecyclerViewTabs() {
        recyclerViewTabs = binding.root.findViewById(R.id.tab_recycler_view_item)
        recyclerViewTabs.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTabs.adapter = TabAdapter(list) { cellActive, textView, cardView, type ->
            elementChecked(cellActive, textView, cardView)
            type?.let { scrollProduct(it) }
        }
    }

    private fun scrollProduct(type: String) {
        if (listProduct.isNotEmpty()) {
            listProduct.forEachIndexed { index, product ->
                if (product.type == type) {
                    recyclerViewProducts.smoothScrollToPosition(index)
                    return
                }
            }
        }
    }

    private fun discounts() {
        menuViewModel.getDiscounts().observe(viewLifecycleOwner, Observer { it ->
            it?.let { resource ->
                resource.data?.let {
                    Log.d(TAG, "$it")
                    discountList(it)
                }
            }
        })
    }

    private fun products() {
        menuViewModel.getProducts().observe(viewLifecycleOwner, Observer { it ->
            it?.let { resource ->
                resource.data?.let {
                    productList(it)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun productList(list: ArrayList<Product>) {
        this.productAdapter.apply {
            listProducts(list)
            notifyDataSetChanged()
        }
    }

    private fun discountList(list: ArrayList<Discount>) {
        this.discountAdapter.apply {
            getDiscounts(list)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NewApi")
    private fun elementChecked(
        cellActive: Boolean,
        textView: TextView,
        cardView: CardView
    ) {
        when (cellActive) {
            true -> {
                textView.setTextColor(resources.getColor(R.color.white, null))
                cardView.setCardBackgroundColor(
                    resources.getColor(
                        R.color.font_tab_on, null
                    )
                )
            }
            false -> {
                textView.setTextColor(resources.getColor(R.color.black, null))
                cardView.setCardBackgroundColor(
                    resources.getColor(R.color.font_tab_off, null)
                )
            }
        }
    }

    private fun tabs(): ArrayList<Tab> {
        val list = ArrayList<Tab>()
        list.add(Tab("Пицца"))
        list.add(Tab("Комбо"))
        list.add(Tab("Закуски"))
        list.add(Tab("Десерты"))
        list.add(Tab("Напитки"))
        list.add(Tab("Другие"))
        return list
    }

}
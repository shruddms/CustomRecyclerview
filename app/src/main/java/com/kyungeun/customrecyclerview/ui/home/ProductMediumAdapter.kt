package com.kyungeun.customrecyclerview.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyungeun.customrecyclerview.data.entity.Product
import com.kyungeun.customrecyclerview.databinding.ItemProductDetailBinding
import com.kyungeun.customrecyclerview.databinding.ItemProductMediumBinding

class ProductMediumAdapter() : RecyclerView.Adapter<ProductMediumViewHolder>() {

    lateinit var mListener : OnItemClickListener

    interface OnItemClickListener {
        fun onProductMediumItemClicked(id: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    private val items = ArrayList<Product>()

    fun setItems(items: ArrayList<Product>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductMediumViewHolder {
        val binding: ItemProductMediumBinding = ItemProductMediumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductMediumViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductMediumViewHolder, position: Int) = holder.bind(items[position])
}

class ProductMediumViewHolder(private val itemBinding: ItemProductMediumBinding, private val listener: ProductMediumAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var product: Product

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Product) {
        this.product = item

        Glide.with(itemBinding.root)
            .load(item.image)
            .override(512, 512)
            .dontAnimate()
            .into(itemBinding.image)

        itemBinding.name.text = product.name
        itemBinding.price.text = "$${product.price}"
    }

    override fun onClick(v: View?) {
        listener.onProductMediumItemClicked(product.id)
    }
}

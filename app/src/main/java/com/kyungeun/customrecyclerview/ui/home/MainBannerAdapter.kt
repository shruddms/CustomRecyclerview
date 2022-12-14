package com.kyungeun.customrecyclerview.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyungeun.customrecyclerview.data.entity.Banner
import com.kyungeun.customrecyclerview.databinding.ItemMainbannerBinding

class MainBannerAdapter(private val listener: BannerItemListener) : RecyclerView.Adapter<MainBannerViewHolder>() {

    interface BannerItemListener {
        fun onMainBannerClicked(id: Int)
    }

    private val items = ArrayList<Banner>()

    fun setItems(items: ArrayList<Banner>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBannerViewHolder {
        val binding: ItemMainbannerBinding = ItemMainbannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainBannerViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainBannerViewHolder, position: Int) = holder.bind(items[position])
}

class MainBannerViewHolder(private val itemBinding: ItemMainbannerBinding, private val listener: MainBannerAdapter.BannerItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var banner: Banner

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Banner) {
        this.banner = item

        Glide.with(itemBinding.root)
            .load(item.image)
            .override(512, 512)
            .dontAnimate()
            .into(itemBinding.imageView)
    }

    override fun onClick(v: View?) {
        listener.onMainBannerClicked(banner.id)
    }
}


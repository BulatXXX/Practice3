package com.example.practice3


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.practice3.databinding.ItemLayoutBinding

class ItemAdapter(val items:List<Item>): RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    class ItemHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Item){
            binding.dateTv.text = item.date
            binding.timeTv.text = item.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ItemHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHolder , position: Int) {
        holder.bind(items[position])
    }
}



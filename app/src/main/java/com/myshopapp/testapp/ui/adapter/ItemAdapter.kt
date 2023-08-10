package com.myshopapp.testapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myshopapp.testapp.arch.entities.ItemModel
import com.myshopapp.testapp.databinding.ItemsBinding

class ItemAdapter(
    val listener: (ItemModel) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    val list: ArrayList<ItemModel> = arrayListOf<ItemModel>()

    fun notifyAdapter(list: List<ItemModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        constructor(parent: ViewGroup) : this(
            ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, true)
        )

        init {

        }

        fun bindTo(itemMode: ItemModel) {
            binding.apply {
                item.text = itemMode.title
                Glide.with(itemView.context)
                    .load(itemMode.thumbnail)
                    .into(image)
                add.text = if (itemMode.selected > 0) "remove" else "add"
                add.setOnClickListener(this@ItemViewHolder)
            }
        }

        override fun onClick(p0: View?) {
            val model = list[absoluteAdapterPosition]
            if (model.selected == 0) {
                model.selected += 1
            } else {
                model.selected -= 1
            }
            binding.add.text = if (model.selected > 0) "remove" else "add"
            listener(model)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindTo(list[position])
    }
}
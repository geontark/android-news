package com.example.myrealtrip.presentaion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myrealtrip.R
import com.example.myrealtrip.databinding.NewsListItemBinding
import com.example.myrealtrip.presentaion.models.NewsData

class NewsListAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    var items = ArrayList<NewsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: NewsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_list_item,
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun add(list: ArrayList<NewsData>) {
        this.items = list
        notifyDataSetChanged()
    }
}
package com.example.myrealtrip.presentaion.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.myrealtrip.databinding.NewsListItemBinding
import com.example.myrealtrip.presentaion.activies.DetailActivity
import com.example.myrealtrip.presentaion.models.NewsData

class NewsViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener {
            binding.news?.let { news ->
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("news", news)
                it.context.startActivity(intent)
            }
        }
    }

    fun bind(newsData: NewsData) {
        binding.apply {
            news = newsData
        }
        binding.executePendingBindings()
    }
}
package com.example.news.presentaion.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsListItemBinding
import com.example.news.presentaion.activies.DetailActivity
import com.example.news.presentaion.models.NewsData

class NewsViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
//        아이템 클릭시 기사 디테일 엑티비티로 이동
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
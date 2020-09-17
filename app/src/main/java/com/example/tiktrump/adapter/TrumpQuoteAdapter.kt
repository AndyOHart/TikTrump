package com.example.tiktrump.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.tiktrump.databinding.ListItemBinding
import com.example.tiktrump.model.TrumpQuote

class TrumpQuoteAdapter : RecyclerView.Adapter<TrumpQuoteAdapter.TrumpQuoteViewHolder>() {

    private val items = ArrayList<TrumpQuote>()
    private lateinit var binding: ListItemBinding

    fun setItems(items: ArrayList<TrumpQuote>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrumpQuoteViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrumpQuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrumpQuoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TrumpQuoteViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var trumpQuote: TrumpQuote
        private val binding = ListItemBinding.bind(binding.root)

        fun bind(trumpQuote: TrumpQuote) {
            this.trumpQuote = trumpQuote
            binding.trumpQuoteTextView.text = trumpQuote.message
            Glide.with(binding.root)
                .load(trumpQuote.trumpFaceResId)
                .into(binding.trumpProfileImageView)
        }
    }
}

package com.training.movieinfo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.movieinfo.model.Movie
import com.training.movieinfo.databinding.MoviesItemCustomBinding

class AdapterCustom(var list: List<Movie.Item>, private val clickListener: (movieId: String) -> Unit) :
    RecyclerView.Adapter<AdapterCustom.RcItemViewHolder>() {

    class RcItemViewHolder(val rcItemViewHolder: MoviesItemCustomBinding) :
        RecyclerView.ViewHolder(rcItemViewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcItemViewHolder {
        return RcItemViewHolder(
            MoviesItemCustomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RcItemViewHolder, position: Int) {
        val item = holder.rcItemViewHolder
        val currentItem = list[position]
        item.textViewName.text = currentItem.name
        item.textViewLanguage.text = currentItem.language
        item.textViewDuration.text = "${currentItem.duration} phút"
        item.textViewCountry.text = "Quốc gia: ${currentItem.country}"
        item.textViewYear.text = "Năm: ${currentItem.years}"
        item.textViewProducer.text = "Sản xuất: ${currentItem.directer}"

        Picasso.get().load("${currentItem.coverImg}").into(item.imageViewImage)
        item.root.setOnClickListener { clickListener.invoke(currentItem.id.toString()) }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
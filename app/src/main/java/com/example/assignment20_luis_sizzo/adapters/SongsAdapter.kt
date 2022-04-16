package com.example.assignment20_luis_sizzo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment20_luis_sizzo.databinding.ItemSongBinding
import com.example.assignment20_luis_sizzo.model.data_class.SongResponse
import com.example.assignment20_luis_sizzo.utils.Dialogs
import com.example.assignment20_luis_sizzo.utils.click
import com.example.assignment20_luis_sizzo.utils.toast
import com.squareup.picasso.Picasso


class SongsAdapter(private val items: List<SongResponse>, val context: Context) :
    RecyclerView.Adapter<SongsAdapter.BooksViewHolder>() {

    class BooksViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        try {

            Picasso.get().load(items[position].artworkUrl60).into(holder.binding.imSong)
            holder.binding.tvColletionSongName.text = items[position].collectionName
            holder.binding.tvSongPrice.text = "${items[position].trackPrice} USD"
            holder.binding.tvArtistName.text = items[position].artistName
            holder.binding.cardViewItemSong.click {
                Dialogs().bottomSheetDialogChangeSignature(
                    context,
                    items[position].previewUrl,
                    items[position].collectionName,
                    items[position].artistName
                )
            }

        } catch (e: Exception) {
            context.toast(e.toString())
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
package com.example.assignment20_luis_sizzo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment20_luis_sizzo.databinding.ItemSongBinding
import com.example.assignment20_luis_sizzo.model.SongResponse
import com.example.assignment20_luis_sizzo.utils.Dialogs
import com.example.assignment20_luis_sizzo.utils.click
import com.example.assignment20_luis_sizzo.utils.toast
import com.squareup.picasso.Picasso
import java.io.IOException


class SongsAdapter(private val items: List<SongResponse>, val context: Context) : RecyclerView.Adapter<SongsAdapter.BooksViewHolder>() {

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
            holder.binding.tvSongName.text = items[position].artistName
            holder.binding.tvSongPrice.text = "${items[position].trackPrice} USD"
            holder.binding.tvCollectionName.text = items[position].collectionName
            holder.binding.cardViewItemSong.click {

                Dialogs().bottomSheetDialogChangeSignature(context, items[position].previewUrl)
            }

        } catch (e: Exception) {
            //context.toast(e.toString())
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun player(url: String) {

        val mPlayer = MediaPlayer()
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mPlayer.setDataSource(url)
        } catch (e: IllegalArgumentException) {
            context.toast("You might not set the URI correctly!", Toast.LENGTH_LONG)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            mPlayer.prepare()
        } catch (e: IllegalStateException) {
            context.toast("You might not set the URI correctly!", Toast.LENGTH_LONG)
        } catch (e: IOException) {
            context.toast("You might not set the URI correctly!", Toast.LENGTH_LONG)
        }
        mPlayer.start()
        mPlayer.setOnCompletionListener { mPlayer -> mPlayer.release() }
        val builder1: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
        builder1.setMessage("Stop the player?")
        builder1.setCancelable(true)

        builder1.setOnCancelListener {
            mPlayer.stop()
            mPlayer.release()
            it.cancel()
        }

        builder1.setPositiveButton(
            "Stop"
        ) { dialog, id ->
            mPlayer.stop()
            mPlayer.release()
            dialog.cancel()
        }.show()
    }
}
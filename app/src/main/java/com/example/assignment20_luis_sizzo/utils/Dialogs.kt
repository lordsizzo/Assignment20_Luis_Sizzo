package com.example.assignment20_luis_sizzo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.assignment20_luis_sizzo.databinding.BottomSheetPlayerBinding
import com.example.assignment20_luis_sizzo.databinding.SimpleDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.IOException

class Dialogs {
    private lateinit var mPlayer: MediaPlayer
    private var isPlaying: Boolean = true

    @SuppressLint("LongLogTag")
    fun bottomSheetDialogChangeSignature(
        context: Context,
        url: String,
        tvColletionSongName: String,
        tvArtistName: String
    ) {
        try {
            if (isPlaying){

                isPlaying = false
                mPlayer = MediaPlayer()
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

                mPlayer.setOnCompletionListener { mPlayer -> mPlayer.release() }


                if(mPlayer.isPlaying){
                    //context.toast("Se esta reproduciendo", Toast.LENGTH_LONG)
                }else{
                    mPlayer.start()
                }

                val dialogSignatureView: BottomSheetPlayerBinding = BottomSheetPlayerBinding.inflate(
                    LayoutInflater.from(context)
                )

                val dialogBSDAddUser = BottomSheetDialog(context)
                dialogBSDAddUser.setCancelable(true)

                dialogBSDAddUser.setOnCancelListener {
                    isPlaying = true
                    mPlayer.stop()
                    mPlayer.reset()
                    mPlayer.release()

                }
                dialogSignatureView.bsdColletionName.text = tvColletionSongName
                dialogSignatureView.bsdArtistName.text = tvArtistName
                dialogSignatureView.btnPlay.click {
                    dialogSignatureView.btnPause.visibility = View.VISIBLE
                    dialogSignatureView.btnPlay.visibility = View.GONE

                    mPlayer.start()
                }

                dialogSignatureView.btnPause.click {

                    dialogSignatureView.btnPause.visibility = View.GONE
                    dialogSignatureView.btnPlay.visibility = View.VISIBLE
                    context.toast("Paused", Toast.LENGTH_SHORT)
                    mPlayer.pause()
                }

                dialogBSDAddUser.setContentView(dialogSignatureView.root)
                dialogBSDAddUser.show()
            }
        } catch (e: Exception) {
            isPlaying = true
            context.toast(e.toString(), Toast.LENGTH_LONG)
        }
    }

    fun showDialog(msg: String, context: Context): androidx.appcompat.app.AlertDialog {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(context)
        var bindingPDialog: SimpleDialogBinding = SimpleDialogBinding.inflate(
            LayoutInflater.from(context))
        dialog.setTitle(msg)
        dialog.setView(bindingPDialog.root)
        dialog.setCancelable(false)
        return dialog.show()
    }
}
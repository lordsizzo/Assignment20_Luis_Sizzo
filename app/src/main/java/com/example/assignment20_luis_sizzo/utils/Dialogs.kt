package com.example.assignment20_luis_sizzo.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.widget.Toast
import com.example.assignment20_luis_sizzo.databinding.BottomSheetPlayerBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.IOException
import java.lang.Exception

class Dialogs {
    fun bottomSheetDialogChangeSignature(context: Context, url: String){
        try {
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

            var dialogSignatureView: BottomSheetPlayerBinding = BottomSheetPlayerBinding.inflate(
                LayoutInflater.from(context))

            val dialogBSDAddUser = BottomSheetDialog(context)
            dialogBSDAddUser.setCancelable(true)

            dialogBSDAddUser.setOnCancelListener {
                mPlayer.stop()
                mPlayer.release()
            }

            dialogSignatureView.btnPlay.click {
                mPlayer.release()
                mPlayer.start()
            }
            dialogSignatureView.btnStop.click {
                context.toast("cancelo", Toast.LENGTH_SHORT)
                mPlayer.stop()
            }

            dialogSignatureView.btnPause.click {
                context.toast("cancelo", Toast.LENGTH_SHORT)
                mPlayer.release()
            }

            dialogBSDAddUser.setContentView(dialogSignatureView.root)
            dialogBSDAddUser.show()
        } catch (e: Exception) {
            context.toast(e.toString(), Toast.LENGTH_LONG)
        }
    }
}
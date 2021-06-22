package com.javed.myFragmentApplication

import android.media.MediaPlayer

interface SongAdapterInterface {

    fun onItemClick(song: String, artist: String, track: MediaPlayer, pos :Int)
}
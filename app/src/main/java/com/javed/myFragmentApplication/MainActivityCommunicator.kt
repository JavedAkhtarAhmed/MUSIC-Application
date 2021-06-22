package com.javed.myFragmentApplication

import android.media.MediaPlayer

interface MainActivityCommunicator {

    fun getData(song: String?, artist: String?, track: MediaPlayer?, pos: Int)

//    fun playBtnClick()
}
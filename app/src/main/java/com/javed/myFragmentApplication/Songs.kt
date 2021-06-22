package com.javed.myFragmentApplication

import android.media.MediaPlayer
import android.provider.MediaStore

data class Songs(
    var songName: String,
    var artistName : String,
    var track : MediaPlayer
)

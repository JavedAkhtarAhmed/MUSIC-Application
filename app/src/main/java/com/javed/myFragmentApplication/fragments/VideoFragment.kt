package com.javed.myFragmentApplication.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.javed.myFragmentApplication.MainActivity
import com.javed.myFragmentApplication.R
import com.javed.myFragmentApplication.communicators.VideoFragmentToBaseCommunicator
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.coroutines.*
import me.tankery.lib.circularseekbar.CircularSeekBar


class VideoFragment() : Fragment() {
    private var videoCallBack: VideoFragmentToBaseCommunicator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoCallBack = activity as? VideoFragmentToBaseCommunicator
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val song = arguments?.getString("SongName")
        val name = arguments?.getString("ArtistName")
        val totalTime = arguments?.getInt("TotalTime")

        arguments?.let { l ->
            txt_song_name_video_fragment.text = song
            txt_artist_name_video_fragment.text = name
        }

        seek(totalTime)
        var i = 0
        lifecycleScope.launch(Dispatchers.Default) {
            totalTime?.let {
                while (i <= totalTime) {
                    withContext(Dispatchers.Main) {
                        i = (activity as? MainActivity)?.getCurrentPosition() ?: 0
                        txtElapsedTime.text =
                            i.let { it1 ->
                                createTimeLabel(it1).toString()
                            }
                        val remainingTime = totalTime - i
                        txtRemainingTime.text = createTimeLabel(remainingTime).toString()
                    }
                    delay(1000)
                }
            }

        }
    }

    private fun seek(totalTime: Int?) {

        totalTime?.let {
            circularSeekBar.max = it.toFloat()
        }

        circularSeekBar.setOnSeekBarChangeListener(
            object : CircularSeekBar.OnCircularSeekBarChangeListener {
                override fun onProgressChanged(
                    circularSeekBar: CircularSeekBar?,
                    progress: Float,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        videoCallBack?.getSeekBarProgressChange(progress)
                    }

                }

                override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {
                }

                override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {
                }
            }
        )
    }

//    @SuppressLint("HandlerLeak")
//    var handler = object : Handler() {
//        override fun handleMessage(msg: Message) {
//            val currentPosition = msg.what
//            circularSeekBar.progress = currentPosition.toFloat()
//
//            val elapsedTime = createTimeLabel(currentPosition)
//            txtElapsedTime.text = elapsedTime.toString()
//
////            val remainingTime = totalTime?.minus(currentPosition)?.let { createTimeLabel(it) }
////            txtRemainingTime.text = "-$remainingTime"
//        }
//    }

    private fun createTimeLabel(time: Int): Any {
        var timeLabel = ""
        val min = time / 1000 / 60
        val sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel

    }


}


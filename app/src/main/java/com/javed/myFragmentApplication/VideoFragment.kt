package com.javed.myFragmentApplication


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment() : Fragment() {
//    private  var videoCallBack :MainActivityCommunicator?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val song = arguments?.getString("SongName")
        val name = arguments?.getString("ArtistName")
        val totalTime : Float?=arguments?.getFloat("TotalTime")
        arguments?.let { l ->
            txt_song_name_video_fragment.text = song
            txt_artist_name_video_fragment.text = name
        }
//        videoCallBack = activity as? MainActivityCommunicator
//        videoCallBack?.playBtnClick()

        Log.d("DataTransfer", "in home frag data frm adapter through interface $song")
    }




//    var seekBar: CircularSeekBar = findViewById(R.id.circularSeekbar)
//    seekBar.setMax(mediaPlayer.getDuration());
//    seekBar.setProgress(mediaPlayer.getCurrentPosition());
//
//    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//        @Override
//        public void onProgressChanged(SeekBar seekBar, float
//                progress, boolean fromUser) {
//
//            int h = Math.round(progress);
//            //In the above line we are converting the float value into int because
//// media player required int value and seekbar library gives progress in float
//            if (mediaPlayer != null && fromUser) {
//                mediaPlayer.seekTo(h);
//            }
//        }
//
//        @Override
//        public void onStartTrackingTouch(SeekBar seekBar) {
//
//        }
//
//        @Override
//        public void onStopTrackingTouch(SeekBar seekBar) {
//
//        }
//    });


}
package com.javed.myFragmentApplication

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(), SongAdapterInterface {
    private var songName: String? = null
    private var artistName: String? = null
    private var trackName: MediaPlayer? = null
    private var mCallback: MainActivityCommunicator? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCallback = activity as? MainActivityCommunicator

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var songs = Songs("Love the way you lie", "Eminem",mp)
        val songsList: ArrayList<Songs> = ArrayList();
        songsList.add(Songs("Veda", "Sehrat Durmus", MediaPlayer.create(activity, R.raw.veda)))
        songsList.add(
            Songs(
                "La Calin",
                "Sehrat Durmus",
                MediaPlayer.create(activity, R.raw.lacalin)
            )
        )
        songsList.add(Songs("Get LOW", "Dj Snake", MediaPlayer.create(activity, R.raw.getlow)))
        songsList.add(Songs("Can We Kiss", "Kina", MediaPlayer.create(activity, R.raw.can_we_kiss)))
        songsList.add(Songs("Keep Up", "Akon", MediaPlayer.create(activity, R.raw.keepup)))
        songsList.add(Songs("Clouds", "Nf", MediaPlayer.create(activity, R.raw.clouds)))
        songsList.add(
            Songs(
                "Leave Me Alone",
                "Nf",
                MediaPlayer.create(activity, R.raw.leave_me_alone)
            )
        )
        songsList.add(
            Songs(
                "Woh Lamhe",
                "Atif Aslam",
                MediaPlayer.create(activity, R.raw.woh_lamhe)
            )
        )
        songsList.add(
            Songs(
                "Zindagi-The Train",
                "Shafaqat Ali",
                MediaPlayer.create(activity, R.raw.zindagi_train)
            )
        )
        songsList.add(
            Songs(
                "7 Years Old",
                "Lukas Graham",
                MediaPlayer.create(activity, R.raw.sevenyears)
            )
        )

        val songAdapter = SongAdapter(songsList, this)
        view.rv.adapter = songAdapter

//        mCallback?.getData(songName, artistName)

    }

    override fun onItemClick(song: String, artist: String, track: MediaPlayer, pos: Int) {
        songName = song
        artistName = artist
        trackName = track
        Log.d("DataTransfer", "in home frag data frm adapter through interface $song")
        mCallback?.getData(songName, artistName, trackName, pos)
    }
}
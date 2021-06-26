package com.javed.myFragmentApplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javed.myFragmentApplication.*
import com.javed.myFragmentApplication.communicators.MainActivityCommunicator
import com.javed.myFragmentApplication.communicators.SongAdapterInterface
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(), SongAdapterInterface {

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
        songsList.add(Songs("Veda", "Sehrat Durmus", R.raw.veda))
        songsList.add(Songs("La Calin", "Sehrat Durmus", R.raw.lacalin))
        songsList.add(Songs("Get LOW", "Dj Snake", R.raw.getlow))
        songsList.add(Songs("Can We Kiss", "Kina", R.raw.can_we_kiss))
        songsList.add(Songs("Keep Up", "Akon", R.raw.keepup))
        songsList.add(Songs("Clouds", "Nf", R.raw.clouds))
        songsList.add(Songs("Leave Me Alone", "Nf", R.raw.leave_me_alone))
        songsList.add(Songs("Woh Lamhe", "Atif Aslam", R.raw.woh_lamhe))
        songsList.add(Songs("Zindagi-The Train", "Shafaqat Ali", R.raw.zindagi_train))
        songsList.add(Songs("7 Years Old", "Lukas Graham", R.raw.sevenyears))

        val songAdapter = SongAdapter(songsList, this)
        view.rv.adapter = songAdapter
    }

    override fun onItemClick(songs: Songs) {
        mCallback?.getData(songs)
    }
}
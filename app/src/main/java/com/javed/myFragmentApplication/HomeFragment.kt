package com.javed.myFragmentApplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//  list
        var songs = Songs("Love the way you lie", "Eminem")
        val songsList: ArrayList<Songs> = ArrayList();
        songsList.add(Songs("Love the way you lie", "Akon"))
        songsList.add(Songs("Talikng to the moon", "Sicmick"))
        songsList.add(Songs("Finding Hopes", "Holly Grummond"))
        songsList.add(Songs("Konvicted", "Akon"))
        songsList.add(Songs("Keep Up", "Akon"))
        songsList.add(Songs("Kiss you forever", "Kina"))
        songsList.add(Songs("Where are you now", "Alan Walker"))
        songsList.add(Songs("Finding Hopes", "Holly Grummond"))
        songsList.add(Songs("Hello", "Adele"))
        songsList.add(Songs("Fall Apart", "Post Malone"))

//  adapter
        val songAdapter = SongAdapter(songsList)
        view.rv.adapter = songAdapter
        return view


    }


}
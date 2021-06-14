package com.javed.myFragmentApplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.song.view.*

class SongAdapter(var songsList: ArrayList<Songs>) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.song, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = songsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songsList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(songs: Songs) {
            itemView.txt_song_name.text = songs.songName
            itemView.txt_artist_name.text = songs.artistName

        }
    }
}
package com.javed.myFragmentApplication

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javed.myFragmentApplication.communicators.SongAdapterInterface
import kotlinx.android.synthetic.main.song.view.*

class SongAdapter(
    private var songsList: ArrayList<Songs>,
    private var callback: SongAdapterInterface
) :
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(songs: Songs) {
            itemView.txt_song_name_video_fragment.text = songs.songName
            itemView.txt_artist_name_video_fragment.text = songs.artistName

            itemView.cl_song.setOnClickListener {
                callback.onItemClick(songs)
                itemView.setBackgroundColor(Color.DKGRAY)
//                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show()
                Log.d("DataTransfer", "in adapter onclick $songs.songName")
            }

        }
    }
}

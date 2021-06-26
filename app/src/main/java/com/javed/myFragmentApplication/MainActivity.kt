package com.javed.myFragmentApplication

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.javed.myFragmentApplication.communicators.MainActivityCommunicator
import com.javed.myFragmentApplication.communicators.VideoFragmentToBaseCommunicator
import com.javed.myFragmentApplication.fragments.HomeFragment
import com.javed.myFragmentApplication.fragments.SearchFragment
import com.javed.myFragmentApplication.fragments.SettingFragment
import com.javed.myFragmentApplication.fragments.VideoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityCommunicator,
    VideoFragmentToBaseCommunicator {
    private var selectedIcon = home
    private var mp: MediaPlayer? = null
    private var selectedSong: Songs? = null

    companion object {
        const val home = "Home"
        const val search = "Search"
        const val video = "Video"
        const val setting = "Setting"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initHome()

        btnHome.setOnClickListener {
            unselectPrevTab()
            initHome()
            selectedIcon = home
        }

        btnMid.setOnClickListener {
            if (mp == null) {
                Toast.makeText(this, "select a song", Toast.LENGTH_SHORT).show()
            } else {
                playBtnClick()
            }
        }

        btnSearch.setOnClickListener {
            selectSearch()
        }

        btnVideo.setOnClickListener {

            selectVideo(getSongBundle())
        }

        btnSetting.setOnClickListener {
            selectSetting()
        }
    }

    private fun playTrack(track: Int) {
        if (mp?.isPlaying == true) {
            mp?.release()
        }

        mp = MediaPlayer.create(this, track)
        mp?.start()
        mp?.isLooping = true
        mp?.setVolume(5f, 5f)

        btnMid.setBackgroundResource(R.drawable.ic_pause)
    }

    private fun playBtnClick() {
        if (mp?.isPlaying == true) {
            // Stop
            mp?.pause()

            btnMid.setBackgroundResource(R.drawable.ic_play)
        } else {
            // start
            mp?.start()

            btnMid.setBackgroundResource(R.drawable.ic_pause)
        }
    }

    private fun selectVideo(data: Bundle?) {
        fragmentVideo(VideoFragment(), data)
        unselectPrevTab()

        btnVideo.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_videos_selected,
                null
            )
        )

        selectedIcon = video
    }

    private fun selectSearch() {
        fragmentSearch(SearchFragment())
        unselectPrevTab()

        btnSearch.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_search_selected,
                null
            )
        )

        selectedIcon = search
    }

    private fun selectSetting() {
        fragmentSetting(SettingFragment())
        unselectPrevTab()

        btnSetting.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_notification_selected,
                null
            )
        )

        selectedIcon = setting
    }


    private fun fragmentHome(frag1: HomeFragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmemt, frag1)
        ft.commit()
    }

    private fun fragmentSearch(frag2: SearchFragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmemt, frag2)
        ft.commit()
    }

    private fun fragmentVideo(frag3: VideoFragment, bundle: Bundle?) {
        bundle?.let { l ->
            frag3.arguments = l
        }
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmemt, frag3)
        ft.commit()
    }

    private fun fragmentSetting(frag4: SettingFragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmemt, frag4)
        ft.commit()
    }

    private fun unselectPrevTab() {
        when (selectedIcon) {
            home -> {
                btnHome.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_home_unselected,
                        null
                    )
                )
            }

            search -> {
                btnSearch.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_search_unselected,
                        null
                    )
                )
            }

            video -> {
                btnVideo.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_videos_unselected,
                        null
                    )
                )
            }

            setting -> {
                btnSetting.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_notification_unselected,
                        null
                    )
                )
            }
        }
    }

    private fun initHome() {
        fragmentHome(HomeFragment())
        btnHome.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_home_selected,
                null
            )
        )
    }

    override fun getData(songs: Songs) {
        selectedSong = songs
        playTrack(songs.track)
        selectVideo(getSongBundle())
    }

    override fun getSeekBarProgressChange(progress: Float) {
        mp?.seekTo(progress.toInt())
    }

    fun getCurrentPosition(): Int? {
        return mp?.currentPosition
    }

    private fun getSongBundle(): Bundle {
        return Bundle().apply {
            putString("SongName", selectedSong?.songName)
            putString("ArtistName", selectedSong?.artistName)
            mp?.duration?.let { putInt("TotalTime", it) }
        }
    }
}





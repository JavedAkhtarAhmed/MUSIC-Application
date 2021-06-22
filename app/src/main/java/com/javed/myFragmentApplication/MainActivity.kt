package com.javed.myFragmentApplication

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityCommunicator {
    private var selectedIcon = home
    private var mp: MediaPlayer? = null
    private var totalTime: Int? = 0
    private var oldPosition: Int? = null
    private var oldMp: MediaPlayer? = null


    companion object {
        const val home = "Home"
        const val search = "Search"
        const val video = "Video"
        const val setting = "Setting"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        btnMid.setOnClickListener {
            if (oldPosition == null) {
                Toast.makeText(this, "select a song", Toast.LENGTH_SHORT).show()
            } else {
                playBtnClick()
            }
        }


        initHome()
        btnHome.setOnClickListener {
            unselectPrevTab()
            initHome()
            selectedIcon = home
        }

        btnSearch.setOnClickListener {
            selectSearch()
        }

        btnVideo.setOnClickListener {
            selectVideo(null)
        }

        btnSetting.setOnClickListener {
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
    }

    private fun playTrack(position: Int?) {
        mp?.isLooping = true
        mp?.setVolume(5f, 5f)
        totalTime = mp?.duration
        oldPosition = position


    }

    private fun checkPosition(position: Int?) {
        if (oldPosition == position) {
            playBtnClick()
        } else {
            if (oldMp == null) {
                mp?.start()
                oldMp = mp
                oldPosition = position
                btnMid.setBackgroundResource(R.drawable.ic_pause)
            } else {
//            start
                oldMp?.stop()
                mp?.start()
                oldMp = mp
                oldPosition = position
                btnMid.setBackgroundResource(R.drawable.ic_pause)
            }
        }

    }

    private fun playBtnClick() {
        if (mp!!.isPlaying) {
//            Stop
            mp?.pause()
            btnMid.setBackgroundResource(R.drawable.ic_play)
        } else {
//            start
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

    override fun getData(song: String?, artist: String?, track: MediaPlayer?, pos: Int) {
        Log.d("DataTransfer", "in mainactivity  $track")
        mp = track
        val bundle = Bundle().apply {
            putString("SongName", song)
            putString("ArtistName", artist)

        }
        checkPosition(pos)
        selectVideo(bundle)

    }


}

package com.javed.myFragmentApplication

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var selectedIcon = home

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

        initHome()
        btnHome.setOnClickListener {
            revertColor()
            initHome()
            selectedIcon = home
        }

        btnSearch.setOnClickListener {
            fragmentSearch(SearchFragment())
            revertColor()
            btnSearch.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_search_selected,
                    null
                )
            )
            selectedIcon = search
        }

        btnVideo.setOnClickListener {
            fragmentVideo(VideoFragment())
            revertColor()
            btnVideo.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_videos_selected,
                    null
                )
            )
            selectedIcon = video
        }

        btnSetting.setOnClickListener {
            fragmentSetting(SettingFragment())
            revertColor()
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

    private fun fragmentVideo(frag3: VideoFragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmemt, frag3)
        ft.commit()
    }

    private fun fragmentSetting(frag4: SettingFragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmemt, frag4)
        ft.commit()
    }

    private fun revertColor() {

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

}

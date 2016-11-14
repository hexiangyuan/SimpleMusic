package io.github.hexiangyuan.simplemusic.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.widget.RadioButton
import io.github.hexiangyuan.simplemusic.R
import io.github.hexiangyuan.simplemusic.ui.main.localfile.MusicPlayerFragment
import io.github.hexiangyuan.simplemusic.ui.main.musicplayer.LocalFileFragment
import io.github.hexiangyuan.simplemusic.ui.main.playlist.PlayListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_toolbar_menu.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playListFragment = PlayListFragment()
        val musicPlayerFragment = MusicPlayerFragment()
        val localFileFragment = LocalFileFragment()
        val fragments = listOf(playListFragment, musicPlayerFragment, localFileFragment)
        val titles = listOf("Music List", "Music", "Local File")
        val adapter = MainAdapter(supportFragmentManager, titles, fragments)
        viewPager.adapter = adapter
        radioGroupControls.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.radioBtnPlayList -> viewPager.currentItem = 0
                R.id.radioBtnMusicPlayer -> viewPager.currentItem = 1
                R.id.radioBtnLocalFile -> viewPager.currentItem = 2
            }
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                val radioButton = radioGroupControls.getChildAt(position) as RadioButton
                radioButton.isChecked = true
                toolBar.title = titles[position]
            }
        })

    }

    class MainAdapter(fm: FragmentManager?, val titles: List<String>, val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence = titles[position]

    }
}

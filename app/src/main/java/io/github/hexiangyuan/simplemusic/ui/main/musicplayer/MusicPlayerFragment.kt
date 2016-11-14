package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.hexiangyuan.simplemusic.R


/**
* Creator:HeXiangyuan
* Date  : 16-11-14.
*/
class MusicPlayerFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater!!.inflate(R.layout.fragment_music_player,container,false)
}
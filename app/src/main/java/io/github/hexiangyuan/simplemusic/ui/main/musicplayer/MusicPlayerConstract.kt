package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import android.app.Service
import io.github.hexiangyuan.simplemusic.BasePresenter
import io.github.hexiangyuan.simplemusic.BaseView
import io.github.hexiangyuan.simplemusic.data.Song

/**
* Creator : HeXiangyuan
* Date   : 16-11-14
*/
interface MusicPlayerConstract {
    interface View :BaseView<Presenter>{

        fun musicView(song:Song)

        fun SeekTo(time:Int)

        fun pauseMusic()

        fun startMusic()

        fun changePlayMode(mode:String)

        fun addedToFavorite()

        fun removedFromFavorite()
    }

    interface Presenter :BasePresenter{
        fun loadMusic()

        fun bindPlayerService(service: Service)

        fun unBindPlayerService(service: Service)

        fun playMusic(song: Song)

        fun pauseMusic(song: Song)

        fun nextMusic(song: Song)

        fun lastMusic(song: Song)

        fun addToFavorite(song: Song)

        fun removeFromeFavorite(song: Song)

        fun changeMode(mode: String)
    }
}
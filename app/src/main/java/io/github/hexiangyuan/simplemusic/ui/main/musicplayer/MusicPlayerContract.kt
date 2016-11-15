package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import android.app.Service
import io.github.hexiangyuan.simplemusic.BasePresenter
import io.github.hexiangyuan.simplemusic.BaseView
import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.data.Song
import io.github.hexiangyuan.simplemusic.player.PlayerModel
import rx.Subscriber

/**
 * Creator : HeXiangyuan
 * Date   : 16-11-14
 */
interface MusicPlayerContract {
    interface View : BaseView<Presenter> {

        fun loadPlayerModel(playerMode: PlayerModel)

        fun SeekTo(time: Int)

        fun pauseMusic()

        fun startMusic()

        fun changePlayMode(mode: String)

        fun addedToFavorite()

        fun removedFromFavorite()

        fun showError(errorMsg: String)
    }

    interface Presenter : BasePresenter {
        fun loadMusic()

        fun bindPlayerService(service: Service)

        fun unBindPlayerService(service: Service)

        fun playMusic(song: Song)

        fun pauseMusic(song: Song)

        fun nextMusic(song: Song)

        fun lastMusic(song: Song)

        fun addToFavorite(song: Song)

        fun removeFromFavorite(song: Song)

        fun changeMode(mode: String)
    }

    interface MusicResource {
        fun loadPlayerModel(subscribe: Subscriber<PlayerModel>)
    }
}
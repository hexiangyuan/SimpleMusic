package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import io.github.hexiangyuan.simplemusic.BasePresenter
import io.github.hexiangyuan.simplemusic.BaseView
import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.player.PlayerModel

/**
 * Creator : HeXiangyuan
 * Date   : 16-11-14
 */
interface MusicPlayerContract {
    interface View : BaseView<Presenter> {

        fun loadPlayerModel(playerModel: PlayerModel)

        fun initProgress(progress: Int)

        fun initStartPause(isStart: Boolean)

        fun SeekTo(time: Int)

        fun pauseMusic()

        fun startMusic()

        fun changePlayMode(mode: PlayMode)

        fun addedToFavorite()

        fun removedFromFavorite()

        fun showError(errorMsg: String)


    }

    interface Presenter : BasePresenter {

        fun initPlayerInfo()

        fun playMusic()

        fun pauseMusic()

        fun nextMusic()

        fun lastMusic()

        fun seekTo(progress: Int)

        fun addToFavorite()

        fun removeFromFavorite()

        fun changeMode()

        fun bindService()

        fun uniBindServices()
    }
}
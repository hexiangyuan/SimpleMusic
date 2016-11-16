package io.github.hexiangyuan.simplemusic.ui.main.localfile

import io.github.hexiangyuan.simplemusic.BasePresenter
import io.github.hexiangyuan.simplemusic.BaseView
import io.github.hexiangyuan.simplemusic.data.Song

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-16
 */
interface LocalMusicContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun handleErrorMsg(exception: Exception)

        fun onLocalMusicLoaded(list: List<Song>)

    }

    interface Presenter : BasePresenter {
        fun loadLocalMusicList()
    }
}
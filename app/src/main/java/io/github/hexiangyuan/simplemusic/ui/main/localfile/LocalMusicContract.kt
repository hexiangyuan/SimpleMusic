package io.github.hexiangyuan.simplemusic.ui.main.localfile

import io.github.hexiangyuan.simplemusic.BasePresenter
import io.github.hexiangyuan.simplemusic.BaseView
import io.github.hexiangyuan.simplemusic.data.Song
import java.util.*

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-16
 */
interface LocalMusicContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun handleErrorMsg(exception: Throwable)

        fun onLocalMusicLoaded(list: ArrayList<Song>)

    }

    interface Presenter : BasePresenter {
        fun loadLocalMusicList()
    }
}
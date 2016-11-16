package io.github.hexiangyuan.simplemusic.ui.main.localfile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.hexiangyuan.simplemusic.data.Song
import io.github.hexiangyuan.simplemusic.player.Player
import java.util.*


/**
* Creator:HeXiangYuan
* Date  : 16-11-14
*/
class LocalFileFragment : Fragment(), LocalMusicContract.View {
    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun handleErrorMsg(exception: Exception) {
    }

    override fun onLocalMusicLoaded(list: ArrayList<Song>) {
        (view as TextView).text = list[0].displayName
        Player.instance.play(list)
    }

    override fun setPresenter(t: LocalMusicContract.Presenter) {
    }

    val mPresenter: LocalMusicPresenter by lazy {
        LocalMusicPresenter(this, context)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(context)
        textView.text = "LocalFileFragment"
        loaderManager.initLoader(0, null, mPresenter)
        return textView
    }
}
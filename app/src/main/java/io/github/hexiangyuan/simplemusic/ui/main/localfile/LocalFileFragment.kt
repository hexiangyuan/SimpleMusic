package io.github.hexiangyuan.simplemusic.ui.main.localfile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.hexiangyuan.simplemusic.data.Song


/**
* Creator:HeXiangYuan
* Date  : 16-11-14
*/
class LocalFileFragment : Fragment(), LocalMusicContract.View {
    override fun showProgress() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleErrorMsg(exception: Exception) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLocalMusicLoaded(list: List<Song>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(t: LocalMusicContract.Presenter) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
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
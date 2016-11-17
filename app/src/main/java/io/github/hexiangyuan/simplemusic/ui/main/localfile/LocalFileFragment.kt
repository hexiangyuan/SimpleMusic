package io.github.hexiangyuan.simplemusic.ui.main.localfile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.hexiangyuan.simplemusic.R
import io.github.hexiangyuan.simplemusic.data.Song
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.toast
import java.util.*


/**
* Creator:HeXiangYuan
* Date  : 16-11-14
*/
class LocalFileFragment : Fragment(), LocalMusicContract.View {
    private var songs: ArrayList<Song> = ArrayList()
    private val adapter by lazy { LocalMusicListAdapter(songs) }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun handleErrorMsg(exception: Throwable) {
        context.toast(exception.message!!)
    }

    override fun onLocalMusicLoaded(list: ArrayList<Song>) {
        songs = list
        adapter.songs = songs
        adapter.notifyDataSetChanged()
    }

    override fun setPresenter(t: LocalMusicContract.Presenter) {
    }

    val mPresenter: LocalMusicPresenter by lazy {
        LocalMusicPresenter(this, context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        loaderManager.initLoader(0,null,mPresenter)
    }
}
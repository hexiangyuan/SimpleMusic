package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import android.app.Service
import io.github.hexiangyuan.simplemusic.data.Song
import io.github.hexiangyuan.simplemusic.player.PlayerModel
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import rx.Observer
import rx.Subscriber
import rx.functions.Action1
import rx.internal.util.ActionSubscriber
import rx.internal.util.ObserverSubscriber

/**
 * Creator : xiyoung
 * Date   : 16-11-15
 */
class MusicPlayerPresenter(val view: MusicPlayerContract.View) : MusicPlayerContract.Presenter {
    var model: MusicPlayerModel

    init {
        model = MusicPlayerModel()
        view.setPresenter(this)
    }

    override fun loadMusic() {
        model.loadPlayerModel(object : Subscriber<PlayerModel>() {
            override fun onNext(t: PlayerModel?) {
                if (t != null) view.loadPlayerModel(t)
                else view.showError("no music")
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
                view.showError(e?.message!!)
            }
        })
    }


    override fun bindPlayerService(service: Service) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unBindPlayerService(service: Service) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playMusic(song: Song) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pauseMusic(song: Song) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nextMusic(song: Song) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lastMusic(song: Song) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addToFavorite(song: Song) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeFromFavorite(song: Song) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeMode(mode: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
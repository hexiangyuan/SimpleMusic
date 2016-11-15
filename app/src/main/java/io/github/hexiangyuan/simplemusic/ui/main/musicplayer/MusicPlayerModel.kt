package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import io.github.hexiangyuan.simplemusic.player.PlayerModel
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Creator : xiyoung
 * Date   : 16-11-15
 */
class MusicPlayerModel : MusicPlayerContract.MusicResource {
    override fun loadPlayerModel(subscribe: Subscriber<PlayerModel>) {
        Observable.create<PlayerModel> {}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscribe)
    }
}
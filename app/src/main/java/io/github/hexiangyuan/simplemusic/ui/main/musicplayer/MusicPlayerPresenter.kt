package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.data.Song
import io.github.hexiangyuan.simplemusic.player.IPlayerBack
import io.github.hexiangyuan.simplemusic.service.MusicPlayerService
import org.jetbrains.anko.toast


/**
 * Creator : xiyoung
 * Date   : 16-11-15
 */
class MusicPlayerPresenter(val view: MusicPlayerContract.View, val mContext: Context) : MusicPlayerContract.Presenter, IPlayerBack.CallBack {
    override fun updateProgress(progress: Int) {
      view.updateProgress(progress)
    }

    override fun onComplete(next: Song) {
        view.loadPlayerModel(playerModel)
    }

    override fun onSwitchLast(last: Song) {
        view.loadPlayerModel(playerModel)
    }

    override fun onSwitchNext(next: Song) {
        view.loadPlayerModel(playerModel)
    }

    override fun onPlayStatusChange(isPlaying: Boolean) {
        view.updateBtnStart(isPlaying)
        initPlayerInfo()
    }

    override fun onPlayModeChange(mode: PlayMode) {

    }

    var playerService: MusicPlayerService? = null

    val playerModel by lazy { playerService!!.getPlayerModel() }

    var hasBound = false

    init {
        view.setPresenter(this)
    }

    private  val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val mBinder = service as MusicPlayerService.LocalBinder
            playerService = mBinder.getService()
            playerService!!.unRegisterCallBack(this@MusicPlayerPresenter)
            playerService!!.registerCallBack(this@MusicPlayerPresenter)
            hasBound = true
            initPlayerInfo()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            hasBound = false
        }
    }

    override fun initPlayerInfo() {
        if (hasBound) {
            view.loadPlayerModel(playerModel)
            view.initProgress(playerService!!.getProgress())
            view.initStartPause(playerService!!.isPlaying())
        }
    }

    override fun bindService() {
        val intent = Intent(mContext, MusicPlayerService::class.java)
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun pauseMusic() {
        if (hasBound) {
            if (playerService!!.isPlaying())
                playerService!!.pause()
            else
                playerService!!.playFromPause()
        }
    }

    override fun nextMusic() {
        if (hasBound) {
            if (playerService!!.playNext()){
                initPlayerInfo()
            }
        }
    }

    override fun lastMusic() {
        if (hasBound) {
            if (playerService!!.playLast()){
                initPlayerInfo()
            }

        }
    }

    override fun addToFavorite() {
        if (hasBound) {
            mContext.toast("add favorite")
            view.addedToFavorite()
        }
    }


    override fun removeFromFavorite() {
        if (hasBound) {
            mContext.toast("remove favorite")
            view.removedFromFavorite()
        }
    }

    override fun changeMode() {
        if (hasBound) {
            when (playerModel.playMode) {
                PlayMode.LIST -> {
                    changeMode(PlayMode.SINGLE)
                }
                PlayMode.SINGLE -> {
                    changeMode(PlayMode.RANDOM)
                }
                PlayMode.RANDOM -> {
                    changeMode(PlayMode.LIST)
                }
            }
        }
    }

    fun changeMode(mode: PlayMode) {
        playerService!!.setPlayMode(mode)
        view.changePlayMode(mode)
    }

    override fun seekTo(progress: Int) {
        if (hasBound) {
            if (playerService!!.seekTo(progress)) view.SeekTo(progress)
        }
    }

    override fun uniBindServices() {
        if (hasBound) {
            mContext.unbindService(mConnection)
            hasBound = false
        }
    }


}

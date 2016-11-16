package io.github.hexiangyuan.simplemusic.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.data.Song
import io.github.hexiangyuan.simplemusic.player.IPlayerBack
import io.github.hexiangyuan.simplemusic.player.Player
import io.github.hexiangyuan.simplemusic.player.PlayerModel
import java.util.*

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-16
 */
class MusicPlayerService : Service(), IPlayerBack {
    val mBinder: Binder = LocalBinder()
    val mPlayer: Player by lazy { Player.instance }

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    inner class LocalBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }

    override fun onCreate() {
        super.onCreate()

    }

    fun getPlayerModel(): PlayerModel = mPlayer.playModel

    override fun playFromPause(): Boolean {
        return mPlayer.playFromPause()
    }

    override fun pause(): Boolean {
        return mPlayer.pause()
    }

    override fun play(): Boolean {
        return mPlayer.play()
    }

    override fun play(song: Song): Boolean {
        return mPlayer.play(song)
    }

    override fun play(playList: ArrayList<Song>, starPosition: Int): Boolean {
        return mPlayer.play(playList, starPosition)
    }

    override fun setPlayMode(playMode: PlayMode) {
        return mPlayer.setPlayMode(playMode)
    }

    override fun playNext(): Boolean {
        return mPlayer.playNext()
    }

    override fun playLast(): Boolean {
        return mPlayer.playLast()
    }

    override fun isPlaying(): Boolean {
        return mPlayer.isPlaying()
    }

    override fun seekTo(progress: Int): Boolean {
        return mPlayer.seekTo(progress)
    }

    override fun getProgress(): Int {
        return mPlayer.getProgress()
    }

    override fun release() {
        mPlayer.release()
    }

    override fun registerCallBack(callBack: IPlayerBack.CallBack) {
        mPlayer.registerCallBack(callBack)
    }

    override fun unRegisterCallBack(callBack: IPlayerBack.CallBack) {
        mPlayer.unRegisterCallBack(callBack)
    }

    override fun unRegisterAllCallBack() {
        mPlayer.unRegisterAllCallBack()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
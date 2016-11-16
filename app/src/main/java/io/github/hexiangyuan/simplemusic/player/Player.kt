package io.github.hexiangyuan.simplemusic.player

import android.media.MediaPlayer
import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.data.Song
import java.util.*

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-15
 */
class Player private constructor() : IPlayerBack {
    private var mediaPlayer: MediaPlayer

    private var callBack: ArrayList<IPlayerBack.CallBack> = ArrayList()
    var playModel: PlayerModel

    init {
        playModel = PlayerModel()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnCompletionListener {
            onComplete()
        }
    }

    private object Holder {

        val player = Player()

    }

    companion object {
        val instance: Player by lazy { Holder.player }
    }

    override fun play(): Boolean {
        return play(playModel.getCurrentSong())
    }
    override fun play(song: Song): Boolean {
        if (playModel.hasMusic()) {
            try {
                mediaPlayer.reset()
                mediaPlayer.setDataSource(song.path)
                mediaPlayer.prepare()
                mediaPlayer.start()
                onPlayStatusChange(true)
            } catch (e: Exception) {
                return false
            }
        }
        return false
    }

    override fun pause(): Boolean {
        if (isPlaying()) {
            mediaPlayer.pause()
        }
        return !isPlaying()
    }

    override fun playFromPause(): Boolean {
        mediaPlayer.start()
        onPlayStatusChange(true)
        return true
    }

    override fun play(playList: ArrayList<Song>, starPosition: Int): Boolean {
        if (starPosition < 0 && starPosition >= playList.size) return false
        playModel.musicList = playList
        playModel.currentPosition = starPosition
        return play(playModel.musicList[playModel.currentPosition])
    }

    override fun release() {
        playModel.clear()
        mediaPlayer.reset()
        mediaPlayer.release()
    }


    override fun setPlayMode(playMode: PlayMode) {
        this.playModel.playMode = playMode
        onPlayModeChange(playMode)
    }

    override fun playNext(): Boolean {
        val nextSong = playModel.next() ?: return false
        if (play(nextSong)) {
            onSwitchNext(nextSong)
        }
        return play(nextSong)
    }

    override fun playLast(): Boolean {
        val lastSong = playModel.last() ?: return false
        if (play(lastSong)) {
            onSwitchLast(lastSong)
        }
        return play(lastSong)
    }

    override fun isPlaying(): Boolean = mediaPlayer.isPlaying

    override fun seekTo(progress: Int): Boolean {
        if (!playModel.hasMusic()) return false
        val currentSong = playModel.getCurrentSong()
        if (currentSong.duration >= progress) {
            mediaPlayer.seekTo(progress)
            return true
        } else {
            onComplete()
        }
        return false
    }

    private fun onComplete() {
        if (playNext()) {
            callBack.forEach { back ->
                back.onComplete(playModel.getCurrentSong())
            }
        }
    }

    override fun getProgress(): Int = mediaPlayer.duration

    override fun registerCallBack(callBack: IPlayerBack.CallBack) {
        this.callBack.add(callBack)
    }

    override fun unRegisterCallBack(callBack: IPlayerBack.CallBack) {
        this.callBack.remove(callBack)
    }

    override fun unRegisterAllCallBack() {
        this.callBack.clear()
    }


    private fun onPlayStatusChange(b: Boolean) {
        callBack.forEach { back ->
            back.onPlayStatusChange(b)
        }
    }

    private fun onPlayModeChange(mode: PlayMode) {
        callBack.forEach { back ->
            back.onPlayModeChange(mode)
        }
    }

    private fun onSwitchLast(song: Song) {
        callBack.forEach { back ->
            back.onSwitchLast(song)
        }
    }

    private fun onSwitchNext(song: Song) {
        callBack.forEach { back ->
            back.onSwitchNext(song)
        }
    }
}

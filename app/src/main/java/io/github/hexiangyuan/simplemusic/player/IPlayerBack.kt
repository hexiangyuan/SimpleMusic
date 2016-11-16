package io.github.hexiangyuan.simplemusic.player

import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.data.Song
import java.util.*

/**
 * Creator : xiyoung
 * Date   : 16-11-14
 */
interface IPlayerBack{
    fun playFromPause(): Boolean
    fun pause(): Boolean
    fun play():Boolean
    fun play(song: Song): Boolean
    fun play(playList: ArrayList<Song>, starPosition: Int = 0): Boolean
    fun setPlayMode(playMode: PlayMode)
    fun playNext():Boolean
    fun playLast():Boolean
    fun isPlaying():Boolean
    fun seekTo(progress:Int):Boolean
    fun getProgress():Int
    fun release()
    fun registerCallBack(callBack: CallBack)
    fun unRegisterCallBack(callBack: CallBack)
    fun unRegisterAllCallBack()

    interface CallBack{
        fun onComplete(next:Song)
        fun onSwitchLast(last:Song)
        fun onSwitchNext(next: Song)
        fun onPlayStatusChange(isPlaying: Boolean)
        fun onPlayModeChange(mode: PlayMode)
        fun updateProgress(progress: Int)
    }
}


package io.github.hexiangyuan.simplemusic.player

import io.github.hexiangyuan.simplemusic.data.PlayList
import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.data.Song
import java.text.FieldPosition

/**
 * Creator : xiyoung
 * Date   : 16-11-14
 */
interface IPlayerBack{
    fun setPlayList(playList: PlayList)
    fun InitPlayer()
    fun play(song: Song)
    fun play(playList: PlayList)
    fun play(playList: PlayList,starPosition:Int)
    fun setPlayMode(playMode: PlayMode)
    fun playNext():Boolean
    fun playLast():Boolean
    fun isPlaying():Boolean
    fun seekTo(progress:Int):Boolean
    fun getProgress():Int
    fun registerCallBack(callBack: CallBack)
    fun unRegisterCallBack(callBack: CallBack)

    interface CallBack{
        fun onComplete(next:Song)
        fun onSwitchLast(last:Song)
        fun onSwitchNext(next: Song)
        fun onPlayStatusChange(isPlaying: Boolean)
    }
}


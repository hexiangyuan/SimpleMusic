package io.github.hexiangyuan.simplemusic.player

import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.data.Song
import java.util.*

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-15
 */
class PlayerModel {
    var musicList: ArrayList<Song> = ArrayList()
    var currentPosition: Int = -1
    var playMode: PlayMode = PlayMode.LIST

    fun hasMusic(): Boolean {
        if (!musicList.isEmpty()) {
            if (currentPosition == -1) {
                currentPosition = 0
            }
            return true
        }
        return false
    }

    fun clear() {
        musicList.clear()
        currentPosition = -1
    }

    fun add(song: Song) {
        musicList.add(song)
    }

    fun getCurrentSong(): Song {
        return musicList[currentPosition]
    }

    /**
     * 计算下一首
     */
    fun next(): Song? {
        if (!hasMusic()) return null
        if (musicList.size == 1) {
            return musicList[0]
        }
        when (playMode) {
            PlayMode.RANDOM -> {
                currentPosition = (Math.random() * musicList.size).toInt()
                return musicList[currentPosition]
            }
            else -> {
                when (currentPosition) {
                    musicList.size - 1 -> {
                        currentPosition = 0
                        return musicList[currentPosition]
                    }
                    in 0..musicList.size - 2 -> {
                        currentPosition += 1
                        return musicList[currentPosition]
                    }
                }
                return musicList[currentPosition]
            }
        }
    }

    /**
     * 计算上一首歌曲
     */
    fun last(): Song? {
        if (!hasMusic()) return null
        if (musicList.size == 1) {
            return musicList[0]
        }
        when (playMode) {
            PlayMode.RANDOM -> {
                currentPosition = (Math.random() * musicList.size).toInt()
                return musicList[currentPosition]
            }
            else -> {
                when (currentPosition) {
                    0 -> {
                        currentPosition = 0
                        return musicList[currentPosition]
                    }
                    in 1..musicList.size - 1 -> {
                        currentPosition -= 1
                        return musicList[currentPosition]
                    }
                }
                return musicList[currentPosition]
            }
        }
    }

    /**
     * finish　－> 下一首
     */
    fun complete(): Song {
        return next()!!
    }
}
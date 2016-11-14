package io.github.hexiangyuan.simplemusic.data

/**
 * Creator : HeXiangYuan
 * Date   : 16-11-14
 */

data class Song(var song:String,var artist:String,var time:Long)
data class PlayList(var songList: List<Song>)

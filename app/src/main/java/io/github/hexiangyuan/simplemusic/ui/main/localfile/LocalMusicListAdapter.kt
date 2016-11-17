package io.github.hexiangyuan.simplemusic.ui.main.localfile

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.hexiangyuan.simplemusic.R
import io.github.hexiangyuan.simplemusic.data.Song
import io.github.hexiangyuan.simplemusic.extention.formatTime
import io.github.hexiangyuan.simplemusic.player.Player
import org.jetbrains.anko.find
import java.util.*

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-17
 */
class LocalMusicListAdapter(var songs: ArrayList<Song>? = null) : RecyclerView.Adapter<LocalMusicListAdapter.MusicListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MusicListViewHolder = MusicListViewHolder(View.inflate(parent!!.context, R.layout.item_music_list, null))

    override fun onBindViewHolder(holder: MusicListViewHolder?, position: Int) {
        holder!!.onBind(songs!![position])
    }

    override fun getItemCount(): Int = songs?.size ?: 0

    inner class MusicListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songName: TextView
        val songArtist: TextView
        val songDuration: TextView

        init {
            songName = itemView.find<TextView>(R.id.songName)
            songArtist = itemView.find<TextView>(R.id.songArtist)
            songDuration = itemView.find<TextView>(R.id.duration)
        }

        fun onBind(song: Song) {
            songName.text = song.displayName
            songArtist.text = song.artist
            songDuration.text = song.duration.formatTime()
            itemView.setOnClickListener {
                Player.instance.play(songs!!, layoutPosition)
            }
        }
    }
}
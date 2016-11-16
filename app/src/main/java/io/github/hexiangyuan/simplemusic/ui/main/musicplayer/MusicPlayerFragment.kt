package io.github.hexiangyuan.simplemusic.ui.main.musicplayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import io.github.hexiangyuan.simplemusic.R
import io.github.hexiangyuan.simplemusic.data.PlayMode
import io.github.hexiangyuan.simplemusic.extention.formatTime
import io.github.hexiangyuan.simplemusic.player.PlayerModel
import kotlinx.android.synthetic.main.fragment_music_player.*
import org.jetbrains.anko.toast


/**
* Creator:HeXiangyuan
* Date  : 16-11-14.
*/
class MusicPlayerFragment : Fragment(), MusicPlayerContract.View, View.OnClickListener {

    val mPresenter: MusicPlayerContract.Presenter by lazy {
        MusicPlayerPresenter(this, context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater!!.inflate(R.layout.fragment_music_player,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.bindService()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mPresenter.seekTo(p0!!.progress)
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

        })
        btnPlayMode.setOnClickListener(this)
        btnPlayNext.setOnClickListener(this)
        btnPlayLast.setOnClickListener(this)
        btnFavorite.setOnClickListener(this)
        btnPlayPause.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.initPlayerInfo()
    }

    override fun initProgress(progress: Int) {
        seekBar.progress = progress
        startTime.text = progress.formatTime("mm:ss")
    }

    override fun initStartPause(isStart: Boolean) {
        if (isStart) btnPlayPause.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pause))
        else btnPlayPause.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play))
    }

    override fun loadPlayerModel(playerModel: PlayerModel) {
        if (playerModel.hasMusic()) {
            seekBar.max = playerModel.getCurrentSong().duration
            endTime.text = playerModel.getCurrentSong().duration.formatTime("mm:ss")
            songName.text = playerModel.getCurrentSong().title
            songArtist.text = playerModel.getCurrentSong().artist
//        albumImage
            changePlayMode(playerModel.playMode)
        }

    }

    override fun SeekTo(time: Int) {
        seekBar.progress = time
        startTime.text = time.toString()
    }

    override fun pauseMusic() {
        btnPlayPause.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pause))
    }

    override fun startMusic() {

    }

    override fun changePlayMode(mode: PlayMode) {
        when (mode) {
            PlayMode.LIST -> btnPlayMode.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play_mode_list))
            PlayMode.RANDOM -> btnPlayMode.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play_mode_shuffle))
            PlayMode.SINGLE -> btnPlayMode.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play_mode_single))
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btnPlayMode -> {
                mPresenter.changeMode()
            }
            btnPlayPause -> {

            }
            btnPlayLast -> {
                mPresenter.lastMusic()
            }
            btnPlayNext -> {
                mPresenter.nextMusic()
            }
            btnFavorite -> {
                mPresenter.addToFavorite()
            }
        }
    }

    override fun addedToFavorite() {
        btnFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_yes))
    }

    override fun removedFromFavorite() {
        btnFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_no))
    }

    override fun showError(errorMsg: String) {
        context.toast(errorMsg)
    }

    override fun setPresenter(t: MusicPlayerContract.Presenter) {
    }
}

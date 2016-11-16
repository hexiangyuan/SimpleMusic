package io.github.hexiangyuan.simplemusic.ui.main.localfile


import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.util.Log
import io.github.hexiangyuan.simplemusic.FileUtils
import io.github.hexiangyuan.simplemusic.data.Song
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.exceptions.OnErrorNotImplementedException
import rx.functions.Func1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.io.File
import java.util.*


/**
 * Creator:HeXiangYuan
 * Date  : 16-11-16
 */
class LocalMusicPresenter(val view: LocalMusicContract.View, val context: Context) : LocalMusicContract.Presenter, LoaderManager.LoaderCallbacks<Cursor> {
    private val MEDIA_URI by lazy { MediaStore.Audio.Media.EXTERNAL_CONTENT_URI }

    private val WHERE by lazy { MediaStore.Audio.Media.IS_MUSIC + "=1 AND " + MediaStore.Audio.Media.SIZE + ">0" }
    private val ORDER_BY by lazy { MediaStore.Audio.Media.DISPLAY_NAME + " ASC" }
    private val PROJECTIONS  by lazy {
        arrayOf(
                MediaStore.Audio.Media.DATA, // the real path
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.MIME_TYPE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.IS_RINGTONE,
                MediaStore.Audio.Media.IS_MUSIC,
                MediaStore.Audio.Media.IS_NOTIFICATION,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE)
    }

    val mSubscriptions: CompositeSubscription by lazy { CompositeSubscription() }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
                context,
                MEDIA_URI,
                PROJECTIONS,
                WHERE,
                null,
                ORDER_BY
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        val subscribe = Observable.just(data)
                .flatMap(Func1<Cursor?, Observable<ArrayList<Song>>> {
                    val songs = ArrayList<Song>()
                    if (data != null && data.count > 0) {
                        data.moveToFirst()
                        do {
                            val song = cursorToMusic(data)
                            songs.add(song)
                        } while (data.moveToNext())
                    }
                    return@Func1 Observable.just(songs)
                })
                .doOnNext { songs -> songs.sortedBy(Song::title) }
                .doOnError { e -> Log.e("abcd", e.message) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { songs -> view.onLocalMusicLoaded(songs) }
        mSubscriptions.add(subscribe)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {

    }

    override fun loadLocalMusicList() {

    }

    private fun cursorToMusic(cursor: Cursor): Song {
        val realPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
        val songFile = File(realPath)
        var song: Song?
        if (songFile.exists()) {
            // Using title parsed from file to avoid encoding problems
            song = FileUtils.fileToMusic(songFile)
            if (song != null) {
                return song
            }
        }
        song = Song()
        song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
        var displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
        if (displayName.endsWith(".mp3")) {
            displayName = displayName.substring(0, displayName.length - 4)
        }
        song.displayName = displayName
        song.artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
        song.album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
        song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
        song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
        song.size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))
        return song
    }

}






package io.github.hexiangyuan.simplemusic.ui.main.localfile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
* Creator:HeXiangYuan
* Date  : 16-11-14.
*/
class MusicPlayerFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(context)
        textView.text = "MusicPlayerFragment"
        return textView
    }
}
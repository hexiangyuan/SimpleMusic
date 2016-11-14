package io.github.hexiangyuan.simplemusic

/**
* Creator : HeXiangYuan
* Date   : 16-11-14
*/
interface BaseView<in T:BasePresenter> {
    fun setPresenter(t:T)
}
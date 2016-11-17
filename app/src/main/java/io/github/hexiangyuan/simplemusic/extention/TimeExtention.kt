package io.github.hexiangyuan.simplemusic.extention

import java.text.SimpleDateFormat

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-16
 */
inline fun Int.formatTime(format: String = "mm:ss"): String {
    val sdf = SimpleDateFormat(format)
    return sdf.format(this)
}

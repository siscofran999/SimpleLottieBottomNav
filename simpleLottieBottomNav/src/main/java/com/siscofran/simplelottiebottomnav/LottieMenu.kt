package com.siscofran.simplelottiebottomnav

import com.google.gson.annotations.SerializedName

class LottieMenu {

    companion object {
        val MENU_TRASH = 0
        val MENU_TIMER = 1
        val MENU_SHARE = 2
        val MENU_LOCK = 3
        val MENU_DOWNLOAD = 4
    }

    val MENU_HOME = 0

    @SerializedName("id")
    var mId = 0
    @SerializedName("title")
    var mTitle: String? = null
    @SerializedName("lottie_asset")
    var mLottieAsset: String? = null
    @SerializedName("active_animation_start_index")
    var mActiveStartIndex = 0
    @SerializedName("inactive_still_index")
    var mInactiveStillIndex = 0
}
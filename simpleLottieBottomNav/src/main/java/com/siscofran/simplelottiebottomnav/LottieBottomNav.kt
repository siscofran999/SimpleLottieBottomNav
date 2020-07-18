package com.siscofran.simplelottiebottomnav

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream

class LottieBottomNav : LinearLayout {

    private var mLayoutParams: LayoutParams? = null
    private var mActiveView: LottieAnimationView? = null
    private var mMenus: List<LottieMenu>? = null
    private var mListener: OnNavigationItemSelectedListener? = null
    private var mActiveMenuId = 0

    val KEY_MENU = "menu"
    val DEFAULT_ACTIVE_TAB_ID = LottieMenu.MENU_TRASH

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        orientation = HORIZONTAL
        background = ContextCompat.getDrawable(context, R.drawable.background_default)
        mLayoutParams = LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
        mLayoutParams!!.weight = 1f
        mMenus = createMenusFromRawAsset()
        for (menu in mMenus!!) {
            addMenu(menu)
        }
        requestLayout()
    }

    private fun createMenusFromRawAsset(): List<LottieMenu> {
        val menuJson = JsonParser.parseString(inputStreamToString(resources.openRawResource(R.raw.menu)))
        val listType = object : TypeToken<List<LottieMenu?>?>() {}.type
        return Gson().fromJson(menuJson.asJsonObject[KEY_MENU].asJsonArray, listType)
    }

    private fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }

    private fun addMenu(menu: LottieMenu) {
        val lottieAnimationView = LottieAnimationView(context)
        lottieAnimationView.tag = menu.mId
        lottieAnimationView.setAnimation(menu.mLottieAsset)
        lottieAnimationView.repeatCount = 0
        lottieAnimationView.repeatMode = LottieDrawable.RESTART
        lottieAnimationView.enableMergePathsForKitKatAndAbove(true)
        lottieAnimationView.speed = 1.0f
        lottieAnimationView.setOnClickListener {
            if (mListener != null) {
                mListener!!.onNavigationItemSelected(menu.mId)
            }
            mActiveMenuId = menu.mId
            if (mActiveView != null) {
                if (mActiveView!!.tag === lottieAnimationView.tag) return@setOnClickListener
                mActiveView!!.cancelAnimation()
                mActiveView!!.frame = menu.mInactiveStillIndex
            }
            setActiveView(lottieAnimationView)
        }
        if (menu.mId == DEFAULT_ACTIVE_TAB_ID) {
            setActiveView(lottieAnimationView)
        }

        addView(lottieAnimationView, mLayoutParams)
    }

    private fun setActiveView(lottieAnimationView: LottieAnimationView) {
        mActiveView = lottieAnimationView
        lottieAnimationView.playAnimation()
    }

    fun setOnMenuSelectedListener(menuSelectedListener: OnNavigationItemSelectedListener?) {
        mListener = menuSelectedListener
    }

    interface OnNavigationItemSelectedListener {
        fun onNavigationItemSelected(menuId: Int)
    }
}
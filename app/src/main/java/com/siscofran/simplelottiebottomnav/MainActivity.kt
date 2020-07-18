package com.siscofran.simplelottiebottomnav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.siscofran.simplelottiebottomnav.fragment.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), LottieBottomNav.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frm_layout,
            TrashFragment()
        )
        ft.commit()

        navigation.setOnMenuSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuId: Int) {
        when(menuId){
            LottieMenu.MENU_TRASH -> {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.frm_layout,
                    TrashFragment()
                )
                ft.commit()
            }
            LottieMenu.MENU_DOWNLOAD -> {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.frm_layout,
                    DownloadFragment()
                )
                ft.commit()
            }
            LottieMenu.MENU_LOCK -> {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.frm_layout,
                    LockFragment()
                )
                ft.commit()
            }
            LottieMenu.MENU_SHARE -> {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.frm_layout,
                    ShareFragment()
                )
                ft.commit()
            }
            LottieMenu.MENU_TIMER -> {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.frm_layout,
                    TimerFragment()
                )
                ft.commit()
            }
        }
    }
}
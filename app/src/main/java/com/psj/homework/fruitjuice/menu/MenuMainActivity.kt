package com.psj.homework.fruitjuice.menu

import android.os.Bundle
import com.psj.homework.fruitjuice.menu.list.MenuListPagerAdapter
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.psj.homework.fruitjuice.R

class MenuMainActivity : BaseActivity<MenuMainViewModel>() {

    override val viewModelType = MenuMainViewModel::class

    private val ui by lazy {
        MenuMainUI(getViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        ui.viewPager.adapter = MenuListPagerAdapter(supportFragmentManager)
        ui.tablayout.setupWithViewPager(ui.viewPager)
        setupDrawerListener()
    }

    private fun setupDrawerListener() {
        val toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolBar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        ui.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }
}
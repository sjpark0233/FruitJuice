package com.psj.homework.fruitjuice.menu

import android.view.Gravity
import android.view.MenuItem
import android.view.View.generateViewId
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_FILL
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.psj.homework.fruitjuice.R
import com.psj.homework.fruitjuice.view.borderBottom
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.support.v4.viewPager

class MenuMainUI(
    private val viewModel: MenuMainViewModel
) : AnkoComponent<MenuMainActivity>,
    NavigationView.OnNavigationItemSelectedListener
{

    lateinit var navigationView: NavigationView
    lateinit var tablayout: TabLayout
    lateinit var drawerLayout: DrawerLayout
    lateinit var viewPager: ViewPager
    lateinit var toolBar: Toolbar

    override fun createView(ui: AnkoContext<MenuMainActivity>) = ui.drawerLayout {
        drawerLayout = this

        frameLayout {
            verticalLayout {
                toolBar = toolbar {
                    title = "FRUIT JUICE"
                    bottomPadding = dip(1)
                    background = borderBottom(width = dip(1))
                }.lparams(matchParent, wrapContent)

                tablayout = themedTabLayout(
                    R.style.Widget_MaterialComponents_TabLayout
                ) {
                    bottomPadding = dip(1)
                    tabMode = MODE_SCROLLABLE
                    tabGravity = GRAVITY_FILL
                    background = borderBottom(width = dip(1))
                    lparams(matchParent, wrapContent)
                }

                viewPager = viewPager {
                    id = generateViewId()
                }.lparams(matchParent, matchParent)
            }
        }

        navigationView = navigationView {
            MenuMainNavHeader()
                .createView(AnkoContext.create(context, this))
                .run(::addHeaderView)

            /*
            menu.apply {
                add(NONE, MENU_ID_INQUIRY, NONE, "문의").apply {
                    setIcon(R.drawable.ic_chat)
                }
            }
            */
            setNavigationItemSelectedListener(this@MenuMainUI)
        }.lparams(wrapContent, matchParent) {
            gravity = Gravity.START
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        /*
        when (item.itemId) {
            MENU_ID_INQUIRY -> {
                viewModel.startActivity<MyInquiryActivity>()
            }
        }
         */

        drawerLayout.closeDrawer(navigationView)

        return true
    }
}
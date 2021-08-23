package com.psj.homework.fruitjuice.menu.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.psj.homework.fruitjuice.menu.category.categoryList

class MenuListPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val fragments = categoryList.map {
        MenuListFragment.newInstance(it.id, it.name)
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = getItem(position).title
}
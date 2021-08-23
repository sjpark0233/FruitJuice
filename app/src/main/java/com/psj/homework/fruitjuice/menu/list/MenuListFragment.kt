package com.psj.homework.fruitjuice.menu.list

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class MenuListFragment : BaseFragment<MenuListViewModel>() {

    override val viewModelType = MenuListViewModel::class

    val categoryId get() = arguments?.getInt("categoryId")
        ?: throw IllegalStateException("categoryId 없음")
    val title get() = arguments?.getString("title")
        ?: throw IllegalStateException("title 없음")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = getViewModel()
        viewModel.categoryId = categoryId

        return MenuListUI(viewModel).createView(AnkoContext.create(ctx, this))
    }

    companion object {
        fun newInstance(categoryId: Int, title: String) =
            MenuListFragment().apply {
                arguments = Bundle().also {
                    it.putInt("categoryId", categoryId)
                    it.putString("title", title)
                }
            }
    }
}
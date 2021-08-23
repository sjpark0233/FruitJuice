package com.psj.homework.fruitjuice.menu.list

import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout.*
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MenuListUI(
    private val viewModel: MenuListViewModel
) : AnkoComponent<MenuListFragment> {

    override fun createView(ui: AnkoContext<MenuListFragment>) =
        ui.verticalLayout {
            recyclerView {
                layoutManager = LinearLayoutManager(ui.ctx)
                lparams(matchParent, matchParent)
                bindVisibility(ui.owner, viewModel.itemMains) {
                    it.isNotEmpty()
                }
                bindPagedList(
                    ui.owner,
                    MenuListPagedAdapter(viewModel),
                    viewModel.itemMains
                )
            }
            textView("상품이 없습니다.") {
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.itemMains) {
                    it.isEmpty()
                }
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.CENTER
            }
        }
}
package com.psj.homework.fruitjuice.menuDetail

import android.os.Bundle
import android.view.MenuItem
import com.psj.homework.fruitjuice.database.entities.ItemOption
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class MenuDetailActivity : BaseActivity<MenuDetailViewModel>() {

    override val viewModelType = MenuDetailViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "메인 과일"

        val viewModel = getViewModel()
        val itemMainId = intent.getLongExtra(ITEM_MAIN_ID, -1)

        viewModel.loadDetail(itemMainId)
        var menuDetailUI = MenuDetailUI(getViewModel())
        menuDetailUI.itemMainId = itemMainId
        menuDetailUI.setContentView(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item?.let {
            when(item.itemId) {
                android.R.id.home -> onBackPressed()
                else -> {}
            }
        }
        return true
    }

    companion object {
        val ITEM_MAIN_ID = "itemMainId"
    }
}
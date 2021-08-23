package com.psj.homework.fruitjuice.bill

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class BillActivity : BaseActivity<BillViewModel>()  {

    override val viewModelType = BillViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "주문 계산"

        val viewModel = getViewModel()
        val billNum = intent.getLongExtra(BillActivity.BILL_NUM, -1)

        var billUI = BillUI(getViewModel())
        billUI.billNum = billNum
        billUI.billDetailMains = viewModel.getBillDetailMain(billNum)!!
        billUI.billDetailOptions = viewModel.getBillDetailOption(billNum)!!
        billUI.setContentView(this)
    }

    companion object {
        val BILL_NUM = "billNum"
    }
}
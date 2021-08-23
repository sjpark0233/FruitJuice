package com.psj.homework.fruitjuice.bill

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.lifecycle.MutableLiveData
import com.psj.homework.fruitjuice.R
import com.psj.homework.fruitjuice.database.entities.*
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.NumberFormat

class BillUI(
    private val viewModel: BillViewModel
) : AnkoComponent<BillActivity> {

    var billNum: Long = -1
    lateinit var billDetailMains: List<BillDetailMainDto>
    lateinit var billDetailOptions: List<BillDetailOptionDto>

    override fun createView(ui: AnkoContext<BillActivity>) = ui.constraintLayout {
        val content = scrollView {
            id = View.generateViewId()
            lparams(matchParent, 0)

            verticalLayout {
                padding = dip(20)

                textView("[주문내역]") {
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                    textColor = Color.BLACK
                }.lparams(matchParent, wrapContent) {
                    topMargin = dip(20)
                }

                var totalPrice = 0

                if (billDetailMains != null && billDetailMains.size > 0) {

                    for (billDetailMain in billDetailMains) {

                        gridLayout {
                            orientation = GridLayout.HORIZONTAL
                            columnCount = 2
                            rowCount = 1

                            textView(billDetailMain.name) {
                                textSize = 16f
                                typeface = Typeface.DEFAULT_BOLD
                                textColor = Color.BLACK
                            }.lparams {
                                rightMargin = dip(20)
                            }

                            textView {
                                textSize = 16f
                                typeface = Typeface.DEFAULT_BOLD
                                textColorResource = R.color.colorAccent
                                bindString(
                                    ui.owner,
                                    MutableLiveData<String>(
                                        "₩" + NumberFormat.getInstance()
                                            .format(billDetailMain?.price)
                                    )
                                )
                            }.lparams {
                                rightMargin = dip(40)
                            }
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(30)
                            bottomMargin = dip(20)
                        }

                        totalPrice += billDetailMain.price

                        if (billDetailOptions != null && billDetailOptions.size > 0) {
                            for (billDetailOption in billDetailOptions) {
                                if (billDetailOption.billNum == billDetailMain.billNum
                                    && billDetailOption.seq == billDetailMain.seq
                                ) {

                                    gridLayout {
                                        orientation = GridLayout.HORIZONTAL
                                        columnCount = 4
                                        rowCount = 1

                                        textView(billDetailOption.name + "(추가)") {
                                            textSize = 13f
                                            textColor = Color.BLACK
                                        }.lparams {
                                            rightMargin = dip(20)
                                        }

                                        textView(billDetailOption.quantity.toString() + "개") {
                                            textSize = 13f
                                            textColor = Color.BLACK
                                        }.lparams {
                                            rightMargin = dip(20)
                                        }

                                        textView {
                                            textSize = 13f
                                            typeface = Typeface.DEFAULT_BOLD
                                            textColorResource = R.color.colorAccent
                                            bindString(
                                                ui.owner,
                                                MutableLiveData<String>(
                                                    "단가 ₩" + NumberFormat.getInstance()
                                                        .format(billDetailOption?.price)
                                                )
                                            )
                                        }.lparams {
                                            rightMargin = dip(40)
                                        }

                                        var sumPrice =
                                            billDetailOption.quantity * billDetailOption.price
                                        totalPrice += sumPrice

                                        textView {
                                            textSize = 13f
                                            typeface = Typeface.DEFAULT_BOLD
                                            textColorResource = R.color.colorAccent
                                            bindString(
                                                ui.owner,
                                                MutableLiveData<String>(
                                                    "합계 ₩" + NumberFormat.getInstance()
                                                        .format(sumPrice)
                                                )
                                            )
                                        }.lparams {
                                            rightMargin = dip(40)
                                        }
                                    }.lparams(matchParent, wrapContent) {
                                        topMargin = dip(20)
                                    }
                                }
                            }
                        }

                    }
                }

                gridLayout {
                    orientation = GridLayout.HORIZONTAL
                    columnCount = 2
                    rowCount = 1

                    textView("총 합계 : ") {
                        textSize = 20f
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = Color.BLACK
                    }.lparams {
                        rightMargin = dip(30)
                    }

                    textView("₩" + NumberFormat.getInstance().format(totalPrice)) {
                        textSize = 20f
                        typeface = Typeface.DEFAULT_BOLD
                        textColorResource = R.color.colorAccent
                    }.lparams {
                        rightMargin = dip(30)
                    }
                }.lparams(matchParent, wrapContent) {
                    topMargin = dip(40)
                }

                button("계산하기") {
                    backgroundColorResource = R.color.colorPrimary
                    textColor = Color.WHITE
                    onClick { viewModel.doPay(billNum) }
                }.lparams(matchParent, wrapContent) {
                    topMargin = dip(40)
                }
            }
        }
    }
}
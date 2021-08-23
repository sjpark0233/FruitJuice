package com.psj.homework.fruitjuice.menuDetail

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.psj.homework.fruitjuice.R
import com.psj.homework.fruitjuice.database.entities.ItemOption
import net.codephobia.ankomvvm.databinding.*
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.NumberFormat

class MenuDetailUI(
    private val viewModel : MenuDetailViewModel
) : AnkoComponent<MenuDetailActivity> {

    var itemMainId: Long = -1

    lateinit var itemOptions: List<ItemOption>

    var itemOptionIds: ArrayList<Long> = arrayListOf()
    var textBoxQuantities: ArrayList<TextView> = arrayListOf()

    override fun createView(ui: AnkoContext<MenuDetailActivity>) = ui.constraintLayout {
        val content = scrollView {
            id = View.generateViewId()
            lparams(matchParent, 0)

            verticalLayout {
                constraintLayout {
                    lparams(matchParent, matchParent)
                    viewPager {
                        backgroundColor = Color.GRAY
                        adapter = ImageSliderAdapter().apply {
                            bindItem(ui.owner, viewModel.itemMainImageUrl) {
                                updateItems(it)
                            }
                        }
                    }.lparams(matchParent, dip(0)) {
                        dimensionRatio = "4:3"
                    }
                }

                verticalLayout {
                    padding = dip(20)

                    textView {
                        textSize = 20f
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = Color.BLACK
                        bindString(ui.owner, viewModel.itemMainName)
                    }.lparams(matchParent, wrapContent)

                    textView {
                        textSize = 18f
                        typeface = Typeface.DEFAULT_BOLD
                        textColorResource = R.color.colorAccent
                        bindString(ui.owner, viewModel.itemMainPrice)
                    }.lparams(matchParent, wrapContent) {
                        topMargin = dip(15)
                    }

                    itemOptions = viewModel.getOptionListData(itemMainId)!!

                    if (itemOptions != null && itemOptions!!.isNotEmpty()) {
                        var index = 0

                        verticalLayout {
                            for (itemOption in itemOptions!!) {
                                itemOptionIds.add(itemOption.id)

                                gridLayout {
                                    orientation = GridLayout.HORIZONTAL
                                    columnCount = 5
                                    rowCount = 1

                                    textView {
                                        textSize = 16f
                                        typeface = Typeface.DEFAULT_BOLD
                                        textColor = Color.BLACK
                                        bindString(
                                            ui.owner,
                                            MutableLiveData<String>(itemOption.name + "(추가)")
                                        )
                                    }.lparams {
                                        rightMargin = dip(20)
                                    }

                                    textView {
                                        textSize = 16f
                                        typeface = Typeface.DEFAULT_BOLD
                                        textColorResource = R.color.colorAccent
                                        bindString(
                                            ui.owner,
                                            MutableLiveData<String>("₩" + NumberFormat.getInstance().format(itemOption?.price))
                                        )
                                    }.lparams {
                                        rightMargin = dip(40)
                                    }

                                    button("-") {
                                        tag = index.toString()
                                        onClick {
                                            downOptionQuantity(tag.toString())
                                        }
                                    }.lparams {
                                        rightMargin = dip(10)
                                        width = dip(50)
                                        height = dip(50)
                                    }

                                    var quantityText =
                                    textView {
                                        textSize = 16f
                                        typeface = Typeface.DEFAULT_BOLD
                                        textColor = Color.BLACK
                                        bindString(
                                            ui.owner,
                                            MutableLiveData<String>("0")
                                        )
                                    }.lparams {
                                        rightMargin = dip(10)
                                    }
                                    textBoxQuantities.add(quantityText)

                                    button("+") {
                                        tag = index.toString()
                                        onClick { upOptionQuantity(tag.toString()) }
                                    }.lparams {
                                        width = dip(50)
                                        height = dip(50)
                                    }

                                }.lparams(matchParent, wrapContent)

                                index = index + 1
                            }
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(20)
                        }
                    }

                    button("주문하기") {
                        backgroundColorResource = R.color.colorPrimary
                        textColor = Color.WHITE
                        onClick { viewModel.calculateBill(itemMainId, itemOptionIds, textBoxQuantities) }
                    }.lparams(matchParent, wrapContent) {
                        topMargin = dip(40)
                    }
                }
            }
        }
    }

    private fun downOptionQuantity(tag: String) {
        var index = Integer.parseInt(tag.toString())
        var value = Integer.parseInt(this.textBoxQuantities.get(index).getText().toString())

        if (value > 0) {
            value--
        }

        this.textBoxQuantities.get(index).setText(value.toString())
    }

    private fun upOptionQuantity(tag: String) {
        var index = Integer.parseInt(tag.toString())
        var value = Integer.parseInt(this.textBoxQuantities.get(index).getText().toString())
        var sum = 0

        for (textView in this.textBoxQuantities) {
            sum += Integer.parseInt(textView.getText().toString())
        }

        if (sum < 5) {
            value++
        }

        this.textBoxQuantities.get(index).setText(value.toString())
    }
}
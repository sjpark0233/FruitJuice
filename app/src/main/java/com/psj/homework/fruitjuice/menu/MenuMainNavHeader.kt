package com.psj.homework.fruitjuice.menu

import android.graphics.Typeface
import android.view.View
import com.psj.homework.fruitjuice.R
import com.psj.homework.fruitjuice.view.borderBottom
import org.jetbrains.anko.*

class MenuMainNavHeader : AnkoComponent<View> {

    override fun createView(ui: AnkoContext<View>) = ui.verticalLayout {
        padding = dip(20)
        background = borderBottom(width = dip(1))

        imageView(R.drawable.ic_logo)
            .lparams(dip(54), dip(54))

        textView("FRUIT JUICE") {
            topPadding = dip(8)
            textSize = 20f
            typeface = Typeface.DEFAULT_BOLD
        }
    }
}
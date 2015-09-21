package pl.schibsted.alphabetdecorator

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.text.TextPaint
import android.view.View

/**
 * Created by Damian Petla on 19/09/15.
 */
class AlphabetDecorator(val paddingLeft: Int = 0, val color: Int = Color.DKGRAY) : RecyclerView.ItemDecoration() {

    val itemList: MutableList<ItemData> = arrayListOf()
    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    init {
        textPaint.setColor(color)
        textPaint.setTextAlign(Paint.Align.LEFT)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val left = parent.getPaddingLeft()
        val childCount = parent.getChildCount()
        if (childCount < 1) {
            return
        }
        itemList.clear()
        for (i in 0..childCount - 1) {
            val child = parent.getChildAt(i)
            val holder = parent.getChildViewHolder(child) as TextHolder
            val firstLetter = holder.textView.getText().charAt(0)
            val item = ItemData(firstLetter, child, i)
            if (item !in itemList) {
                itemList.add(item)
            }
        }

        val reverted = itemList.reverse()
        var lastIndex = -1
        reverted.forEach {
            val (char, view, index) = it
            val last = lastIndex == 1 && index == 0
            val top = view.getTop()
            val bottom = view.getBottom()
            val h = bottom - top
            val letterSize = h.toFloat() / 2
            textPaint.setTextSize(letterSize)
            var y = bottom - (letterSize / 2) - (textPaint.descent() / 2)
            if (index == 0 && !last) {
                y -= top
            }
            if (index == 0 && last) {
                textPaint.setAlpha((bottom.toFloat() * 255f / h.toFloat()).toInt())
            } else {
                textPaint.setAlpha(255)
            }
            c.drawText(char.toString(), paddingLeft + left.toFloat(), y, textPaint)
            lastIndex = index
        }

    }

    data class ItemData(val c: Char, val v: View, var index: Int) {
        override fun equals(other: Any?): Boolean {
            return other is ItemData && c.equals(other.c, true)
        }
    }
}
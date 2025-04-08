package co.feip.fefu2025

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class CustomFlexBoxLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var lineWidth = 0
        var totalHeight = 0
        var lineHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            if(lineWidth + childWidth > widthSize) {
                totalHeight += lineHeight
                lineHeight = 0
                lineWidth = 0
            }
            lineWidth += childWidth
            lineHeight = maxOf(lineHeight, childHeight)


        }
        totalHeight += lineHeight
        setMeasuredDimension(widthSize, totalHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val width = r - l
        var x = 0
        var y = 0
        var lineHeight = 0
        for (i in 0 until childCount) {
            val child  = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            if (x + childWidth > width) {
                x = 0
                y += lineHeight
                lineHeight = 0
            }
            child.layout(x, y, x + childWidth, y + childHeight)
            x += childWidth
            lineHeight = maxOf(lineHeight, childHeight)
        }

    }

}

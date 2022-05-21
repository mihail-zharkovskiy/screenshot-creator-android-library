package developer.mihailzharkovskiy.screenshotcreator

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Чтобы понять принцип работы,  в активити или фраменте сделай примерно это:
 * 1) val bitmap = ScreenshotCreator.takeScreenshot(binding.root)
 * 2) ScreenshotUtil.seeScreenshot(bitmap, context)
 * **/
object ScreenshotCreator {

    /**ДЕЛАЕТ ПОЛНОРАЗМЕРНЫЙ СКРИНШОТ RECYCLER VIEW.**/
    fun takeScreenshot(recycler: RecyclerView): Bitmap {
        recycler.measure(
            View.MeasureSpec.makeMeasureSpec(recycler.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        val width = recycler.measuredWidth
        val height = recycler.measuredHeight
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        recycler.draw(canvas)
        return bitmap
    }

    /**
     * ДЕЛАЕТ СКРИНШОТ ПЕРЕДАННОЙ VIEW
     * 1) если передать root view, то будет сделан "скриншот" всего видимого экрана
     * 2) если передать например text view, то на "скриншоте" будет видна только эта view
     * 3) необязательно передавать view из layout-а который виден в данный момент на экране
     * NB!!! если передать recycler view, то скриншот будет содержать только те items
     * которые видны в данный момент, для полноразмерного сриншота recyclerview используй
     * [takeScreenshot]
     * **/
    fun takeScreenshot(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}
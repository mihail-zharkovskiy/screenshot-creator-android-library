package developer.mihailzharkovskiy.screenshotcreator

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

object ScreenshotUtil {
    fun viewScreenshot(screenshot: Bitmap, context: Context) {
        val image = ImageView(context)
        image.setImageBitmap(screenshot)
        AlertDialog.Builder(context).setView(image).show()
    }
}
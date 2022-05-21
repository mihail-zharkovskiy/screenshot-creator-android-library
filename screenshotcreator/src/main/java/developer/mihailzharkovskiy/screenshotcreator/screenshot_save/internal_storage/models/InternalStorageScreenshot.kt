package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.models

import android.graphics.Bitmap

data class InternalStorageScreenshot(
    val name: String,
    val bitmap: Bitmap,
)
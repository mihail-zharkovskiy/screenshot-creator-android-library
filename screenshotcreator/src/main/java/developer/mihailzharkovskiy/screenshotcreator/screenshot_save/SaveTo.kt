package developer.mihailzharkovskiy.screenshotcreator.screenshot_save

import android.graphics.Bitmap

sealed class SaveTo {
    class ExternalStorage(val name: String, val screenshot: Bitmap) : SaveTo()
    class InternalStorage(val name: String, val screenshot: Bitmap) : SaveTo()
}
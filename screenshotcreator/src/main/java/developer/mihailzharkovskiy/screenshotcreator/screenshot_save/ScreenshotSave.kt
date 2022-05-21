package developer.mihailzharkovskiy.screenshotcreator.screenshot_save

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.annotation.WorkerThread

class ScreenshotSave() {

    @RequiresPermission(allOf = [Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE])
    @WorkerThread
    fun saveScreenshot(action: SaveTo, resolver: ContentResolver, context: Context) {
        when (action) {
            is SaveTo.ExternalStorage -> {}
            is SaveTo.InternalStorage -> {}
        }
    }
}


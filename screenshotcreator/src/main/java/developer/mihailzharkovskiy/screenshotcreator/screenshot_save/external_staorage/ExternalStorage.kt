package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.external_staorage

import android.Manifest
import android.content.ContentResolver
import android.graphics.Bitmap
import androidx.annotation.RequiresPermission
import androidx.annotation.WorkerThread
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.external_staorage.states.SaveToExternalStorageState

interface ExternalStorage {

    @RequiresPermission(allOf = [Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE])
    @WorkerThread
    fun saveScreenshot(
        name: String,
        screenshot: Bitmap,
        resolver: ContentResolver,
    ): SaveToExternalStorageState
}
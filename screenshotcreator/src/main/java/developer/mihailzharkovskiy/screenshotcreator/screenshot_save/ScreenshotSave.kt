package developer.mihailzharkovskiy.screenshotcreator.screenshot_save

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.annotation.WorkerThread
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.external_staorage.ExternalStorage
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.external_staorage.ExternalStorageImpl
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.InternalStorage
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.InternalStorageImpl

object ScreenshotSave {

    private val internalStorage: InternalStorage = InternalStorageImpl()
    private val externalStorage: ExternalStorage = ExternalStorageImpl()

    @RequiresPermission(allOf = [Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE])
    @WorkerThread
    fun save(action: SaveTo, resolver: ContentResolver, context: Context) {
        when (action) {
            is SaveTo.ExternalStorage -> {
                externalStorage.saveScreenshot(action.name, action.screenshot, resolver)
            }
            is SaveTo.InternalStorage -> {
                internalStorage.saveScreenshot(action.name, action.screenshot, context)
            }
        }
    }
}


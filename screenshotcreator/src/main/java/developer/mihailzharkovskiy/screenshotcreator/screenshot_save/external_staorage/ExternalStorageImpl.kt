package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.external_staorage

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresPermission
import androidx.annotation.WorkerThread
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.external_staorage.states.SaveToExternalStorageState
import java.io.IOException

class ExternalStorageImpl : ExternalStorage {

    @RequiresPermission(allOf = [Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE])
    @WorkerThread
    override fun saveScreenshot(
        name: String,
        screenshot: Bitmap,
        resolver: ContentResolver,
    ): SaveToExternalStorageState {

        val image = getUriForSave()
        val imageValues = createContentValues(name, screenshot)

        return try {
            resolver.insert(image, imageValues)?.let { uri ->
                resolver.openOutputStream(uri).use { stream ->
                    val result = screenshot.compress(Bitmap.CompressFormat.JPEG, 95, stream)
                    if (result) SaveToExternalStorageState.Success
                    else SaveToExternalStorageState.Error("couldn't save screenshot, error compress")
                }
            } ?: SaveToExternalStorageState.Error("couldn't save screenshot, uri == null")
        } catch (e: IOException) {
            e.printStackTrace()
            SaveToExternalStorageState.Error(e.message ?: "couldn't save screenshot")
        }
    }

    private fun getUriForSave(): Uri {
        return sdk29AndUp {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }

    private fun createContentValues(name: String, screenshot: Bitmap): ContentValues {
        return ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$name.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.WIDTH, screenshot.width)
            put(MediaStore.Images.Media.HEIGHT, screenshot.height)
        }
    }

    private inline fun <T> sdk29AndUp(action: () -> T): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) action() else null
    }
}
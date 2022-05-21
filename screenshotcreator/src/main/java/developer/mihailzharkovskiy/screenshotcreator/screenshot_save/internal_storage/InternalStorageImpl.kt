package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.WorkerThread
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.models.InternalStorageScreenshot
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states.GetAllScreenshotsState
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states.GetScreenshotState
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states.ScreenshotDeleteState
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states.ScreenshotSaveState
import java.io.File
import java.io.IOException

class InternalStorageImpl : InternalStorage {

    private fun filterScreenshotFile(file: File): Boolean {
        return file.canRead() && file.isFile && file.name.endsWith(".jpg", true)
    }

    /**NB!!! вызывать только после проверки файла через [filterScreenshotFile]**/
    private fun mapToInternalStorageScreenshot(file: File): InternalStorageScreenshot {
        val bytes = file.readBytes()
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        return InternalStorageScreenshot(file.name, bitmap)
    }

    @WorkerThread
    override fun saveScreenshot(name: String, btm: Bitmap, context: Context): ScreenshotSaveState {
        return try {
            context.openFileOutput(name, MODE_PRIVATE).use { stream ->
                val result = btm.compress(Bitmap.CompressFormat.JPEG, 95, stream)
                if (result) ScreenshotSaveState.Success
                else ScreenshotSaveState.Error("couldn't save file")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ScreenshotSaveState.Error(e.message ?: "couldn't save file")
        }
    }

    @WorkerThread
    override fun getAllScreenshot(context: Context): GetAllScreenshotsState {
        val files = context.filesDir.listFiles()
        return if (files != null) {
            val btmFiles = files.filter { filterScreenshotFile(it) }
            val screenshots = btmFiles.map { mapToInternalStorageScreenshot(it) }
            GetAllScreenshotsState.YesData(screenshots)
        } else GetAllScreenshotsState.NoData
    }

    @WorkerThread
    override fun getLastScreenshot(context: Context): GetScreenshotState {
        val files = context.filesDir.listFiles()
        return if (files != null) {
            val screenshotFile = files.last { filterScreenshotFile(it) }
            val screenshot = { mapToInternalStorageScreenshot(screenshotFile) }
            GetScreenshotState.YesData(screenshot())
        } else GetScreenshotState.NoData
    }

    @WorkerThread
    override fun deleteScreenshot(name: String, context: Context): ScreenshotDeleteState {
        return try {
            if (context.deleteFile(name)) ScreenshotDeleteState.Success
            else ScreenshotDeleteState.Error("filed to delete file")
        } catch (e: Exception) {
            e.printStackTrace()
            ScreenshotDeleteState.Error(e.message ?: "filed to delete file")
        }
    }
}







package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage

import android.content.Context
import android.graphics.Bitmap
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states.GetAllScreenshotsState
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states.GetScreenshotState
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states.ScreenshotDeleteState
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.states.ScreenshotSaveState

interface InternalStorage {

    fun saveScreenshot(name: String, btm: Bitmap, context: Context): ScreenshotSaveState

    fun getAllScreenshot(context: Context): GetAllScreenshotsState

    fun getLastScreenshot(context: Context): GetScreenshotState

    fun deleteScreenshot(name: String, context: Context): ScreenshotDeleteState

}
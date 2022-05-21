package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states

import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.models.InternalStorageScreenshot

sealed class GetScreenshotState {
    class YesData(val screenshot: InternalStorageScreenshot) : GetScreenshotState()
    object NoData : GetScreenshotState()
}
package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states

import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.models.InternalStorageScreenshot

sealed class GetAllScreenshotsState {
    class YesData(val screenshots: List<InternalStorageScreenshot>) : GetAllScreenshotsState()
    object NoData : GetAllScreenshotsState()
}
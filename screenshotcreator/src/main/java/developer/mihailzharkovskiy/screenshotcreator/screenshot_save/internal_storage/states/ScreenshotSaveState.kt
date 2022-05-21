package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states

sealed class ScreenshotSaveState() {
    object Success : ScreenshotSaveState()
    class Error(val message: String) : ScreenshotSaveState()
}
package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.internal_storage.states

sealed class ScreenshotDeleteState() {
    object Success : ScreenshotDeleteState()
    class Error(val message: String) : ScreenshotDeleteState()
}
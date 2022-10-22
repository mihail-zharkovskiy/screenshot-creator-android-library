package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.states

sealed class ScreenshotSaveState() {
    object Success : ScreenshotSaveState()
    class Error(val message: String) : ScreenshotSaveState()
}
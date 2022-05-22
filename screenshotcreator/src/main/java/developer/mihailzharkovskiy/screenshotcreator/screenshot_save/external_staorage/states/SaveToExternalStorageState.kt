package developer.mihailzharkovskiy.screenshotcreator.screenshot_save.external_staorage.states

sealed class SaveToExternalStorageState() {
    object Success : SaveToExternalStorageState()
    class Error(val message: String) : SaveToExternalStorageState()
}
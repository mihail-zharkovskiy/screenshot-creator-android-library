package developer.mihailzharkovskiy.screenshotcreatorexample

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import developer.mihailzharkovskiy.screenshotcreator.ScreenshotCreator
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.SaveTo
import developer.mihailzharkovskiy.screenshotcreator.screenshot_save.ScreenshotSave
import developer.mihailzharkovskiy.screenshotcreator.util.ScreenshotUtil
import developer.mihailzharkovskiy.screenshotcreatorexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var screenshot: Bitmap? = null

    private val perRead = Manifest.permission.READ_EXTERNAL_STORAGE
    private val perWrite = Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val perGranted = PackageManager.PERMISSION_GRANTED
    private val perRequestCode = 123

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            adapter = Adapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.btTakeScreenshot.setOnClickListener {
            screenshot = ScreenshotCreator.takeScreenshot(binding.root)
        }
        binding.btTakeScreenshotRv.setOnClickListener {
            screenshot = ScreenshotCreator.takeScreenshot(binding.recyclerView)
        }
        binding.btViewScreenshot.setOnClickListener {
            screenshot?.let { bitmap -> ScreenshotUtil.viewScreenshot(bitmap, this) }
        }
        binding.btSaveScreenshot.setOnClickListener {
            screenshot?.let {
                if (checkPermission()) {
                    ScreenshotSave.save(SaveTo.ExternalStorage("example", it), contentResolver, this)
                } else requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        val readPer = ActivityCompat.checkSelfPermission(this, perRead) == perGranted
        val writePer = ActivityCompat.checkSelfPermission(this, perWrite) == perGranted
        return if (readPer && writePer) return true else false
    }

    private fun requestPermission() {
        // TODO доработать запрос разрешений и сохранение для android 11 и больше
        ActivityCompat.requestPermissions(this, arrayOf(perWrite, perRead), perRequestCode)
    }
}
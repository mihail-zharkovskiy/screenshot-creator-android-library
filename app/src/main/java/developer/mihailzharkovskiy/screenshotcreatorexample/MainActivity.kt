package developer.mihailzharkovskiy.screenshotcreatorexample

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import developer.mihailzharkovskiy.screenshotcreator.ScreenshotCreator
import developer.mihailzharkovskiy.screenshotcreator.ScreenshotUtil
import developer.mihailzharkovskiy.screenshotcreatorexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var screenshot: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btTakeScreenshot.setOnClickListener {
            screenshot = ScreenshotCreator.takeScreenshot(binding.root)
        }
        binding.btViewScreenshot.setOnClickListener {
            screenshot?.let { bitmap -> ScreenshotUtil.viewScreenshot(bitmap, this) }
        }
        binding.btSaveScreenshot.setOnClickListener {

        }
    }
}
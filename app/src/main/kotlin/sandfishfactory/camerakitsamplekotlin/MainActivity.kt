package sandfishfactory.camerakitsamplekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.camerakit.CameraKitView
import kotlinx.android.synthetic.main.activity_main.shutterButton
import android.view.View
import java.io.*
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var cameraKitView: CameraKitView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraKitView = findViewById(R.id.camera)

        shutterButton.setOnClickListener(onClickListener)
    }

    override fun onStart() {
        super.onStart()
        cameraKitView!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        cameraKitView!!.onResume()
    }

    override fun onPause() {
        cameraKitView!!.onPause()
        super.onPause()
    }

    override fun onStop() {
        cameraKitView!!.onStop()
        super.onStop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraKitView!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    val onClickListener = { view:View ->
        Log.d("sample", "click shutterButton")
        cameraKitView!!.captureImage(CameraKitView.ImageCallback { cameraKitView, capturedImage ->

            val savedPhoto = File(applicationContext.filesDir, "photo.jpg")
            Log.d("sample", "capture image:" + savedPhoto.absolutePath)
            try {
                val outputStream = FileOutputStream(savedPhoto.getPath())
                outputStream.write(capturedImage)
                outputStream.close()
            } catch (e: java.io.IOException) {
                e.printStackTrace()
            }
        })
    }
}

package kampukter.mtshop.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.BarcodeCallback
import kampukter.mtshop.R
import kotlinx.android.synthetic.main.bar_code_read.*


class BarCodeReadActivity : AppCompatActivity() {

    private var lastText : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bar_code_read)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("blablabla", " Permission is not granted")
        }


    }

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            if (result.text == null || result.text == lastText) {
                // Prevent duplicate scans
                Log.d("blablabla", "Scan code - " + result.text + " ------")

                return
            }

            lastText = result.text
            zxing_barcode_scanner.setStatusText(result.text)
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }

}
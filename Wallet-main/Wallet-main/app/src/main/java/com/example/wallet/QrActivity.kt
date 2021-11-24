package com.example.wallet

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView


class QrActivity : AppCompatActivity() {
    private var mCodeScanner: CodeScanner? = null
    var CameraPermission = false
    val CAMERA_PERM = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        val scannerView: CodeScannerView = findViewById(R.id.scanner_view)
        mCodeScanner = CodeScanner(this, scannerView)
        askPermission()
        if (CameraPermission) {
            var resultText: String
            scannerView.setOnClickListener { mCodeScanner!!.startPreview() }
            mCodeScanner!!.setDecodeCallback { result ->

                runOnUiThread {
                    Toast.makeText(
                        this@QrActivity,
                        result.getText(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                resultText = result.getText()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent.putExtra("key", resultText))
            }
        }
    }

    private fun askPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@QrActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERM
                )
            } else {
                mCodeScanner!!.startPreview()
                CameraPermission = true
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERM) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mCodeScanner!!.startPreview()
                CameraPermission = true
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CAMERA
                    )
                ) {
                    android.app.AlertDialog.Builder(this)
                        .setTitle("Permission")
                        .setMessage("Please provide the camera permission for using all the features of the app")
                        .setPositiveButton("Proceed",
                            DialogInterface.OnClickListener { dialog, which ->
                                ActivityCompat.requestPermissions(
                                    this@QrActivity, arrayOf(
                                        Manifest.permission.CAMERA
                                    ), CAMERA_PERM
                                )
                            }).setNegativeButton("Cancel",
                            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                        .create().show()
                } else {
                    android.app.AlertDialog.Builder(this)
                        .setTitle("Permission")
                        .setMessage("You have denied some permission. Allow all permission at [Settings] > [Permissions]")
                        .setPositiveButton("Settings",
                            DialogInterface.OnClickListener { dialog, which ->
                                dialog.dismiss()
                                val intent = Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", packageName, null)
                                )
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()
                            }).setNegativeButton("No, Exit app",
                            DialogInterface.OnClickListener { dialog, which ->
                                dialog.dismiss()
                                finish()
                            }).create().show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onPause() {
        if (CameraPermission) {
            mCodeScanner!!.releaseResources()
        }
        super.onPause()
    }
}
package com.bytebuilding.opengl.ui.activity

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bytebuilding.opengllesson1.R
import android.app.ActivityManager
import android.content.Context
import android.widget.Toast
import com.bytebuilding.opengl.renderer.OpenGLRenderer
import com.bytebuilding.opengl.ui.glview.GLViewProvider


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    lateinit var mGLSurfaceView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isSupportsES2()) {
            Toast
                    .makeText(this, "OpenGl ES 2.0 is not supported", Toast.LENGTH_LONG)
                    .show();
            finish();
            return;
        } else mGLSurfaceView = GLViewProvider.provideGLView(this)

        setContentView(mGLSurfaceView)
    }

    override fun onResume() {
        super.onResume()

        mGLSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()

        mGLSurfaceView.onPause()
    }

    private fun isSupportsES2(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val configurationInfo = activityManager.deviceConfigurationInfo

        return configurationInfo.reqGlEsVersion >= 0x20000
    }
}

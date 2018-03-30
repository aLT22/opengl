package com.bytebuilding.opengllesson1.ui.activity

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bytebuilding.opengllesson1.R
import android.app.ActivityManager
import android.content.Context
import android.widget.Toast
import com.bytebuilding.opengllesson1.renderer.OpenGLRenderer


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"

        val EGL_CONTEXT_CLIENT_VERSION = 2
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
        }

        mGLSurfaceView = GLSurfaceView(this)
        mGLSurfaceView.setEGLContextClientVersion(EGL_CONTEXT_CLIENT_VERSION)
        mGLSurfaceView.setRenderer(OpenGLRenderer())

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

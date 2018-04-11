package com.bytebuilding.opengl.ui.glview

import android.content.Context
import android.opengl.GLSurfaceView
import com.bytebuilding.opengl.renderer.OpenGLRenderer

/**
 * Created by Turkin A. on 30.03.2018.
 */
class GLViewProvider {

    companion object {
        val TAG = "GLViewProvider"

        val EGL_CONTEXT_CLIENT_VERSION = 2

        fun provideGLView(context: Context): GLSurfaceView {
            val glSurfaceView = GLSurfaceView(context)

            glSurfaceView.setEGLContextClientVersion(EGL_CONTEXT_CLIENT_VERSION)
            glSurfaceView.setRenderer(OpenGLRenderer(context))

            return glSurfaceView
        }
    }

}
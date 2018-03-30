package com.bytebuilding.opengllesson1.renderer

import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by Turkin A. on 30.03.2018.
 */
class OpenGLRenderer : GLSurfaceView.Renderer {

    companion object {
        val TAG = "OpenGLRenderer"
    }

    override fun onDrawFrame(gl: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        glClearColor(0f, 1f, 0f, 1f)
    }
}
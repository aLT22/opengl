package com.bytebuilding.opengl.renderer

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import com.bytebuilding.opengl.utils.createProgram
import com.bytebuilding.opengl.utils.createShader
import com.bytebuilding.opengllesson1.R
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by Turkin A. on 30.03.2018.
 */
class OpenGLRenderer() : GLSurfaceView.Renderer {

    companion object {
        const val TAG = "OpenGLRenderer"

        const val U_COLOR = "u_Color"
        const val A_POSITION = "a_Position"
    }

    lateinit var mContext: Context

    var mProgramId = 0
    var mVertexData: FloatBuffer? = null
    var mColorLocation: Int = 0
    var mPositionLocation: Int = 0

    constructor(context: Context) : this() {
        this.mContext = context
        prepareData()
    }

    override fun onDrawFrame(gl: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)
        glDrawArrays(GL_TRIANGLES, 0, 3)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        glClearColor(0f, 0f, 0f, 1f)

        val shaderVertexId = createShader(mContext, GL_VERTEX_SHADER, R.raw.vertex_shader)
        val shaderFragmentId = createShader(mContext, GL_FRAGMENT_SHADER, R.raw.fragment_shader)
        mProgramId = createProgram(shaderVertexId, shaderFragmentId)

        glUseProgram(mProgramId)

        bindData()
    }

    private fun prepareData() {
        val vertices = floatArrayOf(-0.5f, -0.2f, 0.0f, 0.2f, 0.5f, -0.2f)
        mVertexData = ByteBuffer
                .allocateDirect(vertices.size * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
        mVertexData?.put(vertices)
    }

    private fun bindData() {
        mColorLocation = glGetUniformLocation(mProgramId, U_COLOR)
        glUniform4f(mColorLocation, 0.0f, 0.0f, 1.0f, 1.0f)

        mPositionLocation = glGetAttribLocation(mProgramId, A_POSITION)
        mVertexData?.position(0)

        glVertexAttribPointer(mPositionLocation, 2, GL_FLOAT, false, 0, mVertexData)
        glEnableVertexAttribArray(mPositionLocation)
    }
}
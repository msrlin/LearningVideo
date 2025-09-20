package com.cxp.learningvideo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cxp.learningvideo.opengl.DefGLSurfaceView
import com.cxp.learningvideo.opengl.SimpleRender
import com.cxp.learningvideo.opengl.drawer.BitmapDrawer
import com.cxp.learningvideo.opengl.drawer.IDrawer
import com.cxp.learningvideo.opengl.drawer.TriangleDrawer


/**
 * 简单渲染页面
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since LearningVideo
 * @version LearningVideo
 * @Datetime 2019-10-09 09:23
 *
 */
class SimpleRenderActivity : AppCompatActivity() {
    private lateinit var drawer: IDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simpler_render)

        drawer = if (intent.getIntExtra("type", 0) == 0) {
            TriangleDrawer()
        } else {
            BitmapDrawer(BitmapFactory.decodeResource(CONTEXT!!.resources, R.drawable.cover))
        }
        initRender(drawer)
    }

    private fun initRender(drawer: IDrawer) {
        findViewById<DefGLSurfaceView>(R.id.gl_surface).setEGLContextClientVersion(2)

        val render = SimpleRender()
        render.addDrawer(drawer)
        findViewById<DefGLSurfaceView>(R.id.gl_surface).setRenderer(render)
    }

    override fun onDestroy() {
        drawer.release()
        super.onDestroy()
    }
}
package com.example.matt.openglplayground;

import android.content.Context;

class MyGLSurfaceView extends android.opengl.GLSurfaceView {

    private final MyGLRenderer mGLRenderer;
    public MyGLSurfaceView( Context context ){
        super( context );

        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        mGLRenderer = new MyGLRenderer();
        setRenderer(mGLRenderer);
    }
}

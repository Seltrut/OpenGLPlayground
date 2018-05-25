package com.example.matt.openglplayground;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";


    private FloatBuffer vertexBuffer;
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex



    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {
            -0.5f, 0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };
    float color[] = { 0.5f, 0.0f, 0.5f, 1.0f }; //some kinda purple orr pink??

    public Triangle(){
        //initialize vertex byte buffer for coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect( triangleCoords.length * 4 );
        //use devices native byte order
        bb.order(ByteOrder.nativeOrder());

        //create float buffer from byte buffer
        vertexBuffer = bb.asFloatBuffer();
        //add coordinates to buffer
        vertexBuffer.put(triangleCoords);
        //set buffer to read first coordinate
        vertexBuffer.position(0);

        //attack the shader code
        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        //create empty openGlES program
        mProgram = GLES20.glCreateProgram();

        //add vertex shader
        GLES20.glAttachShader(mProgram, vertexShader);

        //add fragment shader
        GLES20.glAttachShader(mProgram, fragmentShader);

        //link program
        GLES20.glLinkProgram(mProgram);
    }

    public void draw() {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}

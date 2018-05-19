package com.example.matt.openglplayground;

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
    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {
            0.0f, 0.622008459f, 0.0f,
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
    }
}

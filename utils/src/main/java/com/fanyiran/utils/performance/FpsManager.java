package com.fanyiran.utils.performance;

import android.view.Choreographer;

/**
 * Created by fanqiang on 2018/12/14.
 */
public class FpsManager {
    private static FpsManager fpsManager;

    public static FpsManager getFpsManager() {
        if(fpsManager == null){
            fpsManager = new FpsManager();
        }
        return fpsManager;
    }
    private FpsManager(){

    }

    public void start(){
        Choreographer.getInstance().postFrameCallback(callback);
    }

    private Choreographer.FrameCallback callback = new Choreographer.FrameCallback() {
        private final int DURATION = 1000*1000000;
        private int fps;
        private long lastFramTitmeNanos;

        @Override
        public void doFrame(long frameTimeNanos) {
            if(frameTimeNanos - lastFramTitmeNanos >= DURATION){
                fps = 0;
            }
            fps++;
            Choreographer.getInstance().postFrameCallback(callback);
        }

        public int getFps(){
            return fps;
        }
    };



}

package com.fanyiran.utils;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by fanqiang on 2018/12/7.
 */
public class AsycTaskUtil {
    private static AsycTaskUtil instance;
    private HashMap<Callable<Object>, AsyncTaskCustom> runningTaskMap;

    public static AsycTaskUtil getInstance() {
        if (instance == null) {
            instance = new AsycTaskUtil();
        }
        return instance;
    }

    private AsycTaskUtil() {
    }

    public void createAsycTask(Callable runnable, OnTaskListener listener) {
        AsyncTaskCustom asycTask = new AsyncTaskCustom();
        asycTask.setCallable(runnable);
        asycTask.setListener(listener);
        asycTask.executeOnExecutor(ExecutorUtil.getExecutor());
        if (runningTaskMap == null) {
            runningTaskMap = new HashMap<>();
        }
        runningTaskMap.put(runnable, asycTask);
    }

    public void cancelTask(Callable runnable) {
        if (runningTaskMap == null)
            return;
        AsyncTaskCustom taskCustom = runningTaskMap.get(runnable);
        if (taskCustom == null) {
            return;
        }
        taskCustom.cancel(true);
        taskCustom.setCallable(null);
        taskCustom.setListener(null);
        runningTaskMap.remove(runnable);
    }

    private static class AsyncTaskCustom extends AsyncTask {
        private Callable<Object> callable;
        private OnTaskListener listener;

        @Override
        protected Object doInBackground(Object[] objects) {
            if (callable != null) {
                if (!isCancelled()){
                    try {
                        return callable.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (listener != null) {
                listener.onTaskFinished(o);
            }
        }

        public void setCallable(Callable callable) {
            this.callable = callable;
        }

        public void setListener(OnTaskListener listener) {
            this.listener = listener;
        }
    }

    public interface OnTaskListener {
        void onTaskFinished(Object result);
    }
}

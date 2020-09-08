package com.fanyiran.utils.utils.http;

import com.fanyiran.utils.utils.http.callback.OnGetFileCallback;

import java.util.Map;

public interface IHttpHelper {
    int SUCCESS = 0;
    int ERROR_IO = 1;
    int ERROR_JSON = 2;

    <T extends JsonRequestResult> JsonResult post(String url, Map<String, String> formBodyMap, Class<T> jsonClass);

    <T extends JsonRequestResult> JsonResult post(String url, String picPath, Map<String, String> formBodyMap, Class<T> jsonClass);

    <T extends JsonRequestResult> JsonResult post(String url, Map<String, String> formBodyMap, Map<String, String> headerMap, Class<T> jsonClass);

    <T extends JsonRequestResult> JsonResult post(String url, String picPath, Map<String, String> formBodyMap, Map<String, String> headerMap, Class<T> jsonClass);

    void getFile(String fileUrl, String saveFileUrl, OnGetFileCallback onGetFileListener);
}

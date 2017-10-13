package com.example.tyz.latte.net;

import android.content.Context;

import com.example.tyz.latte.net.callback.IError;
import com.example.tyz.latte.net.callback.IFailure;
import com.example.tyz.latte.net.callback.IRequest;
import com.example.tyz.latte.net.callback.ISucces;
import com.example.tyz.latte.net.callback.RequestCallbacks;
import com.example.tyz.latte.net.download.DownloadHandler;
import com.example.tyz.latte.ui.LatteLoader;
import com.example.tyz.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/9/22.
 */

public class RestClient {
    private final String URL;
    private final WeakHashMap PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISucces SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final File FILE;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    public RestClient(String URL,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest REQUEST, ISucces SUCCESS,
                      IFailure FAILURE, IError ERROR,
                      RequestBody BODY,File file,LoaderStyle loaderStyle,Context context) {
        this.URL = URL;
        this.PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.BODY = BODY;
        this.FILE = file;
        this.CONTEXT=context;
        this.LOADER_STYLE=loaderStyle;

    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        try {
            if (REQUEST != null) {
                REQUEST.onRequestStart();
            }
            if (LOADER_STYLE!=null){
                LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call=service.postRaw(URL,BODY);
                break;
            case PUT_RAW:
                call=service.putRaw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody= RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body= MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallbacks());
        }
    }

    private Callback<String> getRequestCallbacks() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR,LOADER_STYLE);

    }

    public void get() {
        request(HttpMethod.GET);
    }



    public void post() {
        if (BODY==null){
            request(HttpMethod.POST);
        }
        else {
            if (!PARAMS.isEmpty()){
               throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public void delete() {
        request(HttpMethod.DELETE);
    }

    public void put() {
        if (BODY==null){
            request(HttpMethod.PUT);
        }
        else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }


}
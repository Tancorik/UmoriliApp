package com.example.tancorik.umoriliapp.data;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import com.example.tancorik.umoriliapp.domain.IRemotePostService;
import com.example.tancorik.umoriliapp.domain.IRemotePostServiceCallback;
import com.example.tancorik.umoriliapp.presentation.model.PostModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RemotePostService implements IRemotePostService {

    private static final String LOG_TAG = "RemotePostServiceLogs";
    private static final String BASE_URL = "http://umorili.herokuapp.com/api/get?";

    private Handler mWorkerHandler;
    private Handler mMainThreadHandler;

    public RemotePostService() {
        HandlerThread handlerThread = new HandlerThread("workThread");
        handlerThread.start();
        mWorkerHandler = new Handler(handlerThread.getLooper());
        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getPostInfoByRubric(String rubric, IRemotePostServiceCallback callback) {
        mWorkerHandler.post(() -> {
            String url = createNeededURL(rubric);
            handleRequest(url, callback);
        });
    }

    private void handleRequest(String requestUrl, IRemotePostServiceCallback callback) {
        try {
            Log.i(LOG_TAG, "Request " + requestUrl);
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("Код ответа сервера :" + connection.getResponseCode());
            }
            Type collectionType = new TypeToken<List<PostModel>>(){}.getType();
            Reader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            List<PostModel> postModels = new Gson().fromJson(reader, collectionType);
            mMainThreadHandler.post(() -> callback.onSuccess(postModels));
        } catch (MalformedURLException e){
            Log.i(LOG_TAG, e.getMessage(), e);
            mMainThreadHandler.post(() -> callback.onError(e));
        } catch (IOException e) {
            Log.i(LOG_TAG, e.getMessage(), e);
            mMainThreadHandler.post(() -> callback.onError(e));
        }
    }

    private String createNeededURL(String rubric) {
        String site;
        switch (rubric) {
            case "bash":
            case "abyss":
                site = "bash.im";
                break;
            case "zadolbali":
                site = "zadolba.li";
                break;
            case "new+anekdot":
            case "new+story":
            case "new+aforizm":
            case "new+stihi":
                site = "anekdot.ru";
                break;
            default:
                throw new IllegalArgumentException("Не правильно задана категория");
        }
        return BASE_URL + "site=" + site + "&name=" + rubric;
    }
}
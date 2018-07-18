package com.example.tancorik.umoriliapp.domain;

import com.example.tancorik.umoriliapp.presentation.model.PostModel;

import java.util.List;

public interface IRemotePostServiceCallback {
    void onSuccess(List<PostModel> postingList);
    void onError(Throwable error);
}

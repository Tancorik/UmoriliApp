package com.example.tancorik.umoriliapp.domain;

public interface IRemotePostService {

    void getPostInfoByRubric(String rubric, IRemotePostServiceCallback callback);
}

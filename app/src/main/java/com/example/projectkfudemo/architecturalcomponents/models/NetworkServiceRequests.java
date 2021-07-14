package com.example.projectkfudemo.architecturalcomponents.models;

import com.example.projectkfudemo.jsonapi.JSONApiDeclarerList;
import com.example.projectkfudemo.jsonapi.JSONApiGlobalSearch;
import com.example.projectkfudemo.jsonapi.JSONApiIsCommentScript;
import com.example.projectkfudemo.jsonapi.JSONApiIsEmployeeScript;
import com.example.projectkfudemo.jsonapi.JSONApiRequest;
import com.example.projectkfudemo.jsonapi.JSONApiUserRequest;
import com.example.projectkfudemo.jsonapi.JSONApiWorkCategoryList;
import com.example.projectkfudemo.jsonapi.JSONApiWorkersList;
import com.example.projectkfudemo.jsonapi.JSONLoginApi;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceRequests {
    private static NetworkServiceRequests mInstance;
    private static final String BASE_URL = "https://portal-dis.kpfu.ru/"; //основной адрес
    private final Retrofit mRetrofit;

    private NetworkServiceRequests() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkServiceRequests getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceRequests();
        }
        return mInstance;
    }
    public JSONApiGlobalSearch getJSONGlobalSearchApi() {
        return mRetrofit.create(JSONApiGlobalSearch.class);
    }

    public JSONApiUserRequest getJSONUserRequestApi() {
        return mRetrofit.create(JSONApiUserRequest.class);
    }

    public JSONApiRequest getJSONRequestApi() {
        return mRetrofit.create(JSONApiRequest.class);
    }

    public JSONLoginApi getJSONUserApi() {
        return  mRetrofit.create(JSONLoginApi.class);
    }

    public JSONApiWorkersList getJSONWorkersListApi() {
        return mRetrofit.create(JSONApiWorkersList.class);
    }

    public JSONApiDeclarerList getJSONDeclarerListApi() {
        return mRetrofit.create(JSONApiDeclarerList.class);
    }

    public JSONApiWorkCategoryList getJSONWorkCategoryListApi() {
        return mRetrofit.create(JSONApiWorkCategoryList.class);
    }

    public JSONApiIsCommentScript getJSONIsCommentScriptApi() {
        return mRetrofit.create(JSONApiIsCommentScript.class);
    }

    public JSONApiIsEmployeeScript getJSONIsEmployeeScriptApi() {
        return mRetrofit.create(JSONApiIsEmployeeScript.class);
    }
}

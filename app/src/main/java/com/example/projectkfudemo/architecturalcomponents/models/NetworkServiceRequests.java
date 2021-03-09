package com.example.projectkfudemo.architecturalcomponents.models;

import com.example.projectkfudemo.JSONApi.JSONApiDeclarerList;
import com.example.projectkfudemo.JSONApi.JSONApiGlobalSearch;
import com.example.projectkfudemo.JSONApi.JSONApiRequest;
import com.example.projectkfudemo.JSONApi.JSONApiUserRequest;
import com.example.projectkfudemo.JSONApi.JSONApiWorkCategoryList;
import com.example.projectkfudemo.JSONApi.JSONApiWorkersList;
import com.example.projectkfudemo.JSONApi.JSONLoginApi;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceRequests {
    private static NetworkServiceRequests mInstance;
    private static final String BASE_URL = "https://portal-dis.kpfu.ru/"; //основной адрес
    private Retrofit mRetrofit;

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
    public JSONApiGlobalSearch getJSONApiGlobalSearch() {
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

    public JSONApiWorkCategoryList getJSONWorkCategoryList() {
        return mRetrofit.create(JSONApiWorkCategoryList.class);
    }
}

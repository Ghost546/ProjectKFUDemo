package com.example.projectkfudemo.architecturalcomponents.models;

import android.util.Log;

import com.example.projectkfudemo.User;
import com.example.projectkfudemo.forjson.SearchDeclarer;
import com.example.projectkfudemo.forjson.SearchDeclarerList;
import com.example.projectkfudemo.forjson.SearchWorkers;
import com.example.projectkfudemo.forjson.SearchWorkersList;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServerRequestsByRx { //из этого класса отправляются прямые запросы на получение данных с сервера
    private final String TAG = this.getClass().getSimpleName();

    User user;//главный параметр для отправки заявок
    ServerRequestsByRx(User user) { //конструктор позволяющий создавать класс только при условии получении данных от пользователя
        this.user = user;
    }

    //массив для Заявку зарегистрировал
    List<String> searchDeclarerStrings;
    List<SearchDeclarer> searchDeclarers;

    //массив для фио исполнителя
    List<String> searchWorkersStrings;
    List<SearchWorkers> searchWorkers;


    public void sendRequestForDataBySpinners() { // непосредственно отправляет запрос для получения данных для выпадающих списков
        setWorkerArraysForSpinnerRequest();
        setDeclarerArraysForSpinnerRequest();
    }



    public void setWorkerArraysForSpinnerRequest() {
        NetworkServiceRequests.getInstance().getJSONWorkersListApi().getSearchWorkersList(user.getUserId())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<SearchWorkersList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchWorkersList searchWorkersList) {
                        searchWorkers = searchWorkersList.getWorkersList();
                        if (searchWorkers.size() == 0) {
                            Log.i(TAG, "!Пришел пустой массив на вывод! метод setWorkerArraysForSpinner");
                        } else {
                            Log.i(TAG, "!Массив пришел! метод setWorkerArraysForSpinner");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setDeclarerArraysForSpinnerRequest() {
        NetworkServiceRequests.getInstance().getJSONDeclarerListApi().getSearchDeclarerList(user.getUserId())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<SearchDeclarerList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchDeclarerList searchDeclarerList) {
                        searchDeclarers = searchDeclarerList.getDeclarersList();
                        if (searchDeclarers.size() == 0) {
                            Log.i(TAG, "!Пришел пустой массив на вывод! метод setDeclarerArraysForSpinner");
                        } else {
                            Log.i(TAG, "!Массив пришел! метод setDeclarerArraysForSpinner");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void GlobalSearchRequest() {

    }
}

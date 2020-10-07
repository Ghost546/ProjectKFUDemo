package com.example.projectkfudemo.architecturalcomponents.models;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.projectkfudemo.User;
import com.example.projectkfudemo.architecturalcomponents.models.NetworkServiceRequests;
import com.example.projectkfudemo.forjson.SearchDeclarer;
import com.example.projectkfudemo.forjson.SearchDeclarerList;
import com.example.projectkfudemo.forjson.SearchWorkers;
import com.example.projectkfudemo.forjson.SearchWorkersList;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServerRequestsByRx { //из этого класса отправляются прямые запросы на получение данных с сервера
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
        setWorkerArraysForSpinner();
        setDeclarerArraysForSpinner();
    }



    public void setWorkerArraysForSpinner() {
        NetworkServiceRequests.getInstance().getJSONWorkersListApi().getSearchWorkersList(user.getUserId())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<SearchWorkersList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchWorkersList searchWorkersList) {
                        if(searchWorkersList.getWorkersList().size()>0) {
                            searchWorkers = new ArrayList<>();
                            searchWorkers = searchWorkersList.getWorkersList();
                        }
//                        else {
//
//                        }
//                        adapterRequestRegistration = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_spinner_item, searchDeclarerStrings);
//                        adapterRequestRegistration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        spinnerRequestRegistration.setAdapter(adapterRequestRegistration);
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

    public void setDeclarerArraysForSpinner() {
        NetworkServiceRequests.getInstance().getJSONDeclarerListApi().getSearchDeclarerList(user.getUserId())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<SearchDeclarerList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchDeclarerList searchDeclarerList) {
                        if(searchDeclarerList.getDeclarersList().size()>0) {
                            searchDeclarers = new ArrayList<>();
                            searchDeclarers = searchDeclarerList.getDeclarersList();
                        }
//                        else {
//
//                        }
//
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
}

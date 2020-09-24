package com.example.projectkfudemo;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

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

public class ServerRequestsByRx implements LifecycleObserver {
    User user;
    ServerRequestsByRx(User user) {
        this.user = user;
    }

    //массив для Заявку зарегистрировал
    List<String> searchDeclarerStrings;
    List<SearchDeclarer> searchDeclarers;

    //массив для фио исполнителя
    List<String> searchWorkersStrings;
    List<SearchWorkers> searchWorkers;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void send(User user) {
        setWorkerArraysForSpinner();
        setWorkerArraysForSpinner();
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
                        searchWorkersStrings = new ArrayList<>();
                        searchWorkers = new ArrayList<>();
                        if(searchWorkersList.getWorkersList().size()>0) {
                            searchWorkers = searchWorkersList.getWorkersList();
                        } else {
                            searchWorkersStrings.add("Cписок пуст");
                        }
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
                        searchDeclarerStrings = new ArrayList<>();
                        searchDeclarers = new ArrayList<>();
                        if(searchDeclarerList.getDeclarersList().size()>0) {
                            searchDeclarers = searchDeclarerList.getDeclarersList();
                        } else {

                        }
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

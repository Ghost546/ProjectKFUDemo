package com.example.projectkfudemo.architecturalcomponents.models;

import android.util.Log;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.parametrclasses.GlobalSearchParams;
import com.example.projectkfudemo.parametrclasses.User;
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarer;
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarerList;
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkers;
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestList;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServerRequestsByRx { //из этого класса отправляются прямые запросы на получение данных с сервера

    public ServerRequestsByRx() {

    }

    public ServerRequestsByRx(ModelsByRequestToServer modelsByRequestToServer, User user) {
        this.modelsByRequestToServer = modelsByRequestToServer;
        this.user=user;
    }

    private final String TAG = this.getClass().getSimpleName();

    User user;//главный параметр для отправки заявок
    ServerRequestsByRx(User user) { //конструктор позволяющий создавать класс только при условии получении данных от пользователя
        this.user = user;
    }

    String declarerFIO;
    Integer cod;
    String date1;
    String date2;
    Integer regType;
    Integer statusId;
    Integer regUserId;
    Integer workerId;
    RequestList requestListStates;

    List<Request> requestListFromServer;

    RequestList requestListCurrentTask;

    ModelsByRequestToServer modelsByRequestToServer;

    //массив для Заявку зарегистрировал
    SearchDeclarerList searchDeclarers;

    //массив для фио исполнителя
    SearchWorkersList searchWorkers;


    //Параметры для GlobalSearch
    GlobalSearchParams globalSearchParams = new GlobalSearchParams();

    int position;

    public void setParamsForRequestOnGlobalSearchToVariables(String declarerFIO, Integer cod, String date1,
                                                  String date2, Integer regType, Integer statusId,
                                                  Integer regUserId, Integer workerId) {
        this.declarerFIO = declarerFIO;
        this.cod = cod;
        this.date1 = date1;
        this.date2 = date2;
        this.regType = regType;
        this.statusId = statusId;
        this.regUserId = regUserId;
        this.workerId = workerId;
    }

    public void setParamsGlobalSearchFromVariablesToParamsObject() {
        globalSearchParams.setDeclarerFIO(declarerFIO);
        globalSearchParams.setCod(cod);
        globalSearchParams.setDate1(date1);
        globalSearchParams.setDate2(date2);
        globalSearchParams.setRegType(regType);
        globalSearchParams.setStatusId(statusId);
        globalSearchParams.setRegUserId(regUserId);
        globalSearchParams.setWorkerId(workerId);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public RequestList getRequestListStates() {
        return requestListStates;
    }

    // непосредственно отправляет запрос для получения данных для выпадающих списков
    public void sendRequestForDataBySpinners() {
        setWorkerArraysForSpinnerRequest();
        setDeclarerArraysForSpinnerRequest();
    }

    public void sendRequestsForRequestOnGlobalSearch() {
        Log.i(TAG, "Отправлен запрос GlobalSearch, поиск заявок по всей БД");
        setRequestListByGlobalSearchRequest();
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
                        searchWorkers = searchWorkersList;
                        if (searchWorkers != null) {
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
        NetworkServiceRequests.getInstance().getJSONDeclarerListApi()
                .getSearchDeclarerList(user.getUserId())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<SearchDeclarerList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchDeclarerList searchDeclarerList) {
                        searchDeclarers = searchDeclarerList;
                        if (searchDeclarers != null) {
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

    public void setRequestListByCurrentTask() {
        NetworkServiceRequests.getInstance().getJSONRequestApi().getRequestWithLoginPassword(user.getUserId(), user.getP2(), position)
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestList requestList) {
                        requestListCurrentTask = requestList;
                        if (requestList != null) {
                            FirebaseCrashlytics.getInstance().log("Пришел пустой массив на вывод! В текущих заявках. Class CurrentTaskFragment метод getRequestListView");
//                            throw new RuntimeException("Test Crash");
                        }
                        Log.i(TAG, "!Размер requestList: " + requestList.getRequests().size());
                        modelsByRequestToServer.setData();
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

    //запрос на глобальный поиск
    public void setRequestListByGlobalSearchRequest() {
        Log.i(TAG, "!Отправляемые данные: " + user.getUserId() + " | " + user.getP2() + " | " +globalSearchParams.getDeclarerFIO()+ " | " +
                globalSearchParams.getCod()+" | " + globalSearchParams.getDate1()+ " | " +globalSearchParams.getDate2()+" | " +
                globalSearchParams.getRegType()+ " | " +globalSearchParams.getStatusId()+" | " +
                globalSearchParams.getRegUserId()+" | " + globalSearchParams.getWorkerId()+ " | " +null+ " | " +null +"!");
        NetworkServiceRequests.getInstance().getJSONApiGlobalSearch().
                getRequestListForSearch(user.getUserId(), user.getP2(), globalSearchParams.getDeclarerFIO(),
                        globalSearchParams.getCod(), globalSearchParams.getDate1(), globalSearchParams.getDate2(),
                        globalSearchParams.getRegType(), globalSearchParams.getStatusId(),
                        globalSearchParams.getRegUserId(), globalSearchParams.getWorkerId(), null, null)
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestList requestList) {
                        requestListFromServer = requestList.getRequests();
                        if(requestListFromServer != null) {
                            Log.i(TAG, "!Массив requestList пришел!");
                            Log.i(TAG, "!Размер states: " + requestListFromServer.size());
                        } else {
                            Log.i(TAG, "!Массив requestList пришел пустой!");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public List<Request> getRequestListFromServer() {
        return requestListFromServer;
    }
}

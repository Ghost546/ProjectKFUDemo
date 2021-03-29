package com.example.projectkfudemo.architecturalcomponents.models;

import android.util.Log;

import com.example.projectkfudemo.parametrclasses.GlobalSearchParams;
import com.example.projectkfudemo.parametrclasses.User;
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarerList;
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList;
import com.example.projectkfudemo.parametrclasses.forjson.WorkCategory;
import com.example.projectkfudemo.parametrclasses.forjson.WorkCategoryList;

import com.example.projectkfudemo.parametrclasses.requests.RequestList;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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

    RequestList requestListFromServer;  //список-ответ на глобальный поиск. наблюдать

    RequestList requestListStates;      //список-ответ на запрос по Current и MyTask, допустимо
    //потому что на каждый VM создается свой экземпляр класса
    //модели. наблюдать

    ModelsByRequestToServer modelsByRequestToServer;

    //списки-ответы на запрос о списках сотрудниках и заявителях

    //массив для Заявку зарегистрировал
    SearchDeclarerList searchDeclarers; //наблюдать

    //массив для фио исполнителя
    SearchWorkersList searchWorkers;    //наблюдать

    private WorkCategoryList workCategoryList;

    //Параметры для GlobalSearch
    GlobalSearchParams globalSearchParams = new GlobalSearchParams();

    String textAnswerFromServerByAssignOnOneself;

    public void setTextAnswerFromServerByAssignOnOneself(String textAnswerFromServerByAssignOnOneself) {
        this.textAnswerFromServerByAssignOnOneself = textAnswerFromServerByAssignOnOneself;
        modelsByRequestToServer.setData();
    }

    int position;

    public void setParamsForRequestOnGlobalSearchToVariables(String declarerFIO, Integer cod, String date1,
                                                             String date2, Integer regType, Integer statusId,
                                                             Integer regUserId, Integer workerId, String text,
                                                             Integer techGroup, String office, String address,
                                                             String roomNum) {
        globalSearchParams.setDeclarerFIO(declarerFIO);
        globalSearchParams.setCod(cod);
        globalSearchParams.setDate1(date1);
        globalSearchParams.setDate2(date2);
        globalSearchParams.setRegType(regType);
        globalSearchParams.setStatusId(statusId);
        globalSearchParams.setRegUserId(regUserId);
        globalSearchParams.setWorkerId(workerId);
        globalSearchParams.setText(text);
        globalSearchParams.setTechGroup(techGroup);
        globalSearchParams.setOffice(office);
        globalSearchParams.setAddress(address);
        globalSearchParams.setRoomNum(roomNum);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public WorkCategoryList getWorkCategoryList() {
        return workCategoryList;
    }

    public void setWorkCategoryList(WorkCategoryList workCategoryList) {
        this.workCategoryList = workCategoryList;
        modelsByRequestToServer.setData();
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
                        requestListStates = requestList;
                        if (requestList != null) {
                            FirebaseCrashlytics.getInstance().log("Пришел пустой массив на вывод! В текущих заявках. Class CurrentTaskFragment метод getRequestListView");
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

    public void setRequestListByMyTask() {
        NetworkServiceRequests.getInstance().getJSONUserRequestApi().getRequestWithLoginPassword(user.getUserId(), user.getP2(), position-1)
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestList requestList) {
                        requestListStates = requestList;
                        if (requestList != null) {
                            FirebaseCrashlytics.getInstance().log("Пришел пустой массив на вывод! В текущих заявках. Class MyTaskFragment метод getRequestListView");
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
        Log.i(TAG, "!Отправляемые данные: " + user.getUserId() + " | " + user.getP2() + " | " + globalSearchParams.getDeclarerFIO()+ " | " +
                globalSearchParams.getCod()+" | " + globalSearchParams.getDate1()+ " | " +globalSearchParams.getDate2()+" | " +
                globalSearchParams.getRegType()+ " | " +globalSearchParams.getStatusId()+" | " +
                globalSearchParams.getRegUserId()+" | " + globalSearchParams.getWorkerId()+ " | " + globalSearchParams.getText() + " | " +null+ " | " +null +"!");
        NetworkServiceRequests.getInstance().getJSONApiGlobalSearch().
                getRequestListForSearch(user.getUserId(), user.getP2(), globalSearchParams.getDeclarerFIO(),
                        globalSearchParams.getCod(), globalSearchParams.getDate1(), globalSearchParams.getDate2(),
                        globalSearchParams.getRegType(), globalSearchParams.getStatusId(),
                        globalSearchParams.getRegUserId(), globalSearchParams.getWorkerId(), globalSearchParams.getText(), null, null,
                        globalSearchParams.getTechGroup(), globalSearchParams.getOffice(), globalSearchParams.getAddress(), globalSearchParams.getRoomNum())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestList requestList) {
                        requestListFromServer = requestList;
                        if(requestListFromServer != null) {
                            Log.i(TAG, "!Массив requestList пришел!");
                            if(requestListFromServer.getRequests()!=null) {
                                Log.i(TAG, "!Размер states: " + requestListFromServer.getRequests().size());
                            } else{
                                Log.i(TAG, "!requestListFromServer.getRequests пустой");
                            }
                        } else {
                            Log.i(TAG, "!Массив requestList пришел пустой!");
                        }
                        modelsByRequestToServer.setData();
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

    public void setWorkCategoryListByRetrofit() {
        NetworkServiceRequests.getInstance().getJSONWorkCategoryList().getSearchWorkCategory(user.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkCategoryList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WorkCategoryList workCategoryList) {
                        Log.i(TAG, "!Массив Категория Работ пришла");
                        setWorkCategoryList(workCategoryList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setAssignOnOneselfRequestToServer() {
        setTextAnswerFromServerByAssignOnOneself("В данный момент запрос на сервер отсутствует");
    }

    public RequestList getRequestListFromServer() {
        return requestListFromServer;
    }
}

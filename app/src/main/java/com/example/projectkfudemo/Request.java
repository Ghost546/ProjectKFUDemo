package com.example.projectkfudemo;

import com.example.projectkfudemo.forjson.Building;
import com.example.projectkfudemo.forjson.Comments;
import com.example.projectkfudemo.forjson.Log;
import com.example.projectkfudemo.forjson.Offices;
import com.example.projectkfudemo.forjson.Status;
import com.example.projectkfudemo.forjson.Type;
import com.example.projectkfudemo.forjson.Workers;
import com.example.projectkfudemo.forjson.Works;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//что с этой библиотекой не так аоаоаоаоаооа
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

public class Request {
    //
    private boolean MyRequest = false;

    private boolean CurrentRequest = false;

    @SerializedName("id")
    @Expose
    private int requestId;//id заявки

    @SerializedName("code")
    @Expose
    private int code = 0;

    @SerializedName("date_of_registration")
    @Expose
    private String requestRegistrationDateString="";//дата регистрации (предварительно)

    //позже разобраться установкой даты
    private LocalDate requestRegistrationDate;//дата регистрации (предварительно)

    @SerializedName("date_of_realization")
    @Expose
    private String periodOfExecutionString;//срок выполнения(предварительно)

    private LocalDate periodOfExecution;//срок выполнения(предварительно)

    //@SerializedName("_id")
    //@Expose
    private String acceptedTheRequest =""; //принял заявку

    @SerializedName("declarant_id")
    @Expose
    private int declarantId;

    @SerializedName("declarant_fullname")
    @Expose
    private String declarer ="";

    @SerializedName("declarant_post")
    @Expose
    private String declarantPost ="";//подразделение

    @SerializedName("declarant_phone")
    @Expose
    private String declarantPhone = "";

    @SerializedName("room_number")
    @Expose
    private String cabine = "0";

    @SerializedName("contact_fullname")
    @Expose
    private String contactFullName ="";//данные о заявителе

//    @SerializedName("_id")
//    @Expose
//    private String textOfRequest ="";

    //        @SerializedName("_id")
    //        @Expose
    private String responsibleForTheExecutionOfTheRequest ="";

    @SerializedName("logs")
    @Expose
    private List<Log> actionsOverRequest;//действия по заявке

    @SerializedName("building")
    @Expose
    private Building building;

    //отсюда можно взять текст описания заявки и другую информацию менее значительную на данном этапе разработки
    //16.03.2020, процесс настройки вывода общей информации по заявкам
    @SerializedName("works")
    @Expose
    private List<Works> worksList;

    @SerializedName("comments")
    @Expose
    private List<Comments> commentsList;

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("type")
    @Expose
    private Type type;


    @SerializedName("offices")
    @Expose
    private List<Offices> subdivisionList;


    @SerializedName("workers")
    @Expose
    private List<Workers> workersList;


    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDate getRequestRegistrationDate() {
        if(!requestRegistrationDateString.equals("")) {
            requestRegistrationDateConvertStringToLocaleDate();
        }
        return requestRegistrationDate;
    }

    //это нужно только для парса при создании объекта Request
    public void requestRegistrationDateConvertStringToLocaleDate() {
        requestRegistrationDate = DateTimeFormat.forPattern("dd.MM.yy").parseLocalDate(requestRegistrationDateString);
    }

    //берет полученное значение String и парсит в LocalDate
    public void setRequestRegistrationDateFromString(String requestRegistrationDateString) {
        this.requestRegistrationDate= DateTimeFormat.forPattern("dd.MM.yy").parseLocalDate(requestRegistrationDateString);
    }

    public LocalDate getPeriodOfExecution() {
        if(!periodOfExecutionString.equals("")) {
            periodOfExecutionConvertStringToLocaleDate();
        }
        return periodOfExecution;
    }

    //берет полученное значение String и парсит в LocalDate
    public void setPeriodOfExecutionFromString(String periodOfExecutionString) {
        this.periodOfExecution = DateTimeFormat.forPattern("dd.MM.yy").parseLocalDate(periodOfExecutionString);
    }

    //это нужно только для парса при создании объекта Request
    public void periodOfExecutionConvertStringToLocaleDate() {
        this.periodOfExecution = DateTimeFormat.forPattern("dd.MM.yy").parseLocalDate(periodOfExecutionString);
    }

    public String getAcceptedTheRequest() {
        return acceptedTheRequest;
    }

    public void setAcceptedTheRequest(String acceptedTheRequest) {
        this.acceptedTheRequest = acceptedTheRequest;
    }

    public String getDeclarer() {
        return declarer;
    }

    public void setDeclarer(String declarer) {
        this.declarer = declarer;
    }

    public List<Offices> getSubdivisionList() {
        return subdivisionList;
    }

    public void setSubdivisionList(List<Offices> subdivisionList) {
        this.subdivisionList = subdivisionList;
    }

    public String getContactFullName() {
        return contactFullName;
    }

    public void setContactFullName(String contactFullName) {
        this.contactFullName = contactFullName;
    }

    public String getTextOfRequest() {
        return worksList.get(0).getDescription();
    }
//
//    public void setTextOfRequest(String textOfRequest) {
//        this.textOfRequest = textOfRequest;
//    }

    public List<Log> getActionsOverRequest() {
        return actionsOverRequest;
    }

    public void setActionsOverRequest(List<Log> actionsOverRequest) {
        this.actionsOverRequest = actionsOverRequest;
    }

    public String getStatusOfRequest() {
        String status = actionsOverRequest.get(actionsOverRequest.size()-1).getStatusName();
        return status;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Works> getWorksList() {
        return worksList;
    }

    public void setWorksList(List<Works> worksList) {
        this.worksList = worksList;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Workers> getWorkersList() {
        return workersList;
    }

    public void setWorkersList(List<Workers> workersList) {
        this.workersList = workersList;
    }

    public void setThatIsCurrentRequest() {
        this.CurrentRequest = true;
    }

    public void setThatIsMyRequest() {
        this.MyRequest = true;
    }

    public boolean getThatIsMyRequest() {
        return MyRequest;
    }

    public boolean getThatIsCurrentRequest() {
        return CurrentRequest;
    }

    public String getResponsibleForTheExecutionOfTheRequest() {
        return responsibleForTheExecutionOfTheRequest;
    }

    public void setResponsibleForTheExecutionOfTheRequest(String responsibleForTheExecutionOfTheRequest) {
        this.responsibleForTheExecutionOfTheRequest = responsibleForTheExecutionOfTheRequest;
    }

    public String getDescriptionOnUse() {
        return getWorksList().get(0).getDescription();
    }

    public String getDescriptionOnPrint() {
        char[] charArr = new char[93];
        if (getDescriptionOnUse().length() < 89) {
            getDescriptionOnUse().getChars(0, getDescriptionOnUse().length() - 1, charArr, 0);
        } else {
            getDescriptionOnUse().getChars(0, 89, charArr, 0);
            for(int i = charArr.length-1; i >= charArr.length-3; i--) {
                charArr[i] = '.';
            }
        }

        return String.valueOf(charArr);
    }

    public Request getRequest() {
        Request request = new Request();
        return request;
    }

}


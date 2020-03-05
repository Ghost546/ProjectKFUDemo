package com.example.projectkfudemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

public class Request {
    private boolean MyRequest = false;

    private boolean CurrentRequest = false;

    @SerializedName("id")
    @Expose
    private int requestId = 0;//номер заявки
    @SerializedName("request_date")
    @Expose
    private LocalDate requestRegistrationDate = new LocalDate();//дата регистрации (предварительно)
    //        @SerializedName("_id")
    //        @Expose
    private LocalDate periodOfExecution = new LocalDate();//срок выполнения(предварительно)
    //        @SerializedName("_id")
    //        @Expose
    private String statusOfRequest ="";
    //        @SerializedName("_id")
    //        @Expose
    private String acceptedTheRequest =""; //принял заявку
    @SerializedName("declarant_fullname")
    @Expose
    private String declarer ="";
    //        @SerializedName("_id")
    //        @Expose
    private String subdivision ="";//подразделение
    //        @SerializedName("_id")
    //        @Expose
    private String dataAboutDeclarer ="";//данные о заявителе
    //        @SerializedName("_id")
    //        @Expose
    private String textOfRequest ="";
    //        @SerializedName("_id")
    //        @Expose
    private String responsibleForTheExecutionOfTheRequest ="";
    //        @SerializedName("_id")
    //        @Expose
    private String actionsOverRequest ="";//действия по заявке

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public LocalDate getRequestRegistrationDate() {
        return requestRegistrationDate;
    }

    public void setRequestRegistrationDate(String requestRegistrationDate) {
        this.requestRegistrationDate = LocalDate.parse(requestRegistrationDate);
    }

    public LocalDate getDateOfRegistrationInDateFormat() {                                          //used joda-time
        LocalDate thisDate = new LocalDate(requestRegistrationDate);
        return thisDate;
    }

    public LocalDate getPeriodOfExecution() {
        return periodOfExecution;
    }


    public void setPeriodOfExecution(String periodOfExecution) {
        this.periodOfExecution = LocalDate.parse(periodOfExecution);
    }

    public LocalDate getPeriodOfExecutionInDateFormat() {                                           //used joda-time
        LocalDate thisDate = new LocalDate(periodOfExecution);
        return thisDate;
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

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getDataAboutDeclarer() {
        return dataAboutDeclarer;
    }

    public void setDataAboutDeclarer(String dataAboutDeclarer) {
        this.dataAboutDeclarer = dataAboutDeclarer;
    }

    public String getTextOfRequest() {
        return textOfRequest;
    }

    public void setTextOfRequest(String textOfRequest) {
        this.textOfRequest = textOfRequest;
    }

    public String getActionsOverRequest() {
        return actionsOverRequest;
    }

    public void setActionsOverRequest(String actionsOverRequest) {
        this.actionsOverRequest = actionsOverRequest;
    }

    public String getStatusOfRequest() {
        return statusOfRequest;
    }

    public void setStatusOfRequest(String statusOfRequest) {
        this.statusOfRequest = statusOfRequest;
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
    public Request getRequest() {
        Request request = new Request();
        return request;
    }
}


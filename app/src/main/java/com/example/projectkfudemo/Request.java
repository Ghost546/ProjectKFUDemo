package com.example.projectkfudemo;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import java.util.Collection;
import java.util.Collections;

public class Request {
    private boolean MyRequest = false;

    private boolean CurrentRequest = false;

    private int requestId = 0; //номер заявки

    private String requestRegistrationDate; //дата регистрации (предварительно)

    private String periodOfExecution; //срок выполнения(предварительно)

    private String statusOfRequest;

    private String acceptedTheRequest; //принял заявку

    private String declarer;

    private String subdivision; //подразделение

    private String dataAboutDeclarer; //данные о заявителе

    private String textOfRequest;

    private String responsibleForTheExecutionOfTheRequest;

    private String actionsOverRequest; //действия по заявке

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestRegistrationDate() {
        return requestRegistrationDate;
    }

    public void setRequestRegistrationDate(String requestRegistrationDate) {
        this.requestRegistrationDate = requestRegistrationDate;
    }

    public LocalDate getDateOfRegistrationInDateFormat() {                                          //used joda-time
        LocalDate thisDate = new LocalDate(requestRegistrationDate);
        return thisDate;
    }

    public String getPeriodOfExecution() {
        return periodOfExecution;
    }


    public void setPeriodOfExecution(String periodOfExecution) {
        this.periodOfExecution = periodOfExecution;
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
        this.MyRequest = true;
    }

    public void setThatIsMyRequest() {
        this.CurrentRequest = true;
    }

    public boolean getThatIsMyRequest() {
        return MyRequest;
    }

    public boolean getThatIsCurrentRequest() {
        return CurrentRequest;
    }
}

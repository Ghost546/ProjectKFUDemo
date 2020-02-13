package com.example.projectkfudemo;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import java.util.Collection;
import java.util.Collections;

public class Request {
    private int requestId; //номер заявки

    private String requestRegistrationDate; //дата регистрации (предварительно)

    private String periodOfExecution; //срок выполнения(предварительно)

    private String statusOfRequest;

    private String acceptedTheApplication;

    private String declarer;

    private String subdivision; //подразделение

    private String dataOfDeclarer; //данные о заявителе

    private String textOfRequest;

    private String responsibleForTheExecutionOfTheApplication;

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

    public String getAcceptedTheApplication() {
        return acceptedTheApplication;
    }

    public void setAcceptedTheApplication(String acceptedTheApplication) {
        this.acceptedTheApplication = acceptedTheApplication;
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

    public String getDataOfDeclarer() {
        return dataOfDeclarer;
    }

    public void setDataOfDeclarer(String dataOfDeclarer) {
        this.dataOfDeclarer = dataOfDeclarer;
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


}

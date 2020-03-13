package com.example.projectkfudemo;

import com.example.projectkfudemo.forjson.Log;
import com.example.projectkfudemo.forjson.LogsList;
import com.google.android.gms.config.proto.Logs;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class MyRequest {
    List<Request> requests = new ArrayList<>();;
    Request request;

    public MyRequest(Request request) {
        this.request = request;
    }

    public int getRequestId() {
        return request.getRequestId();
    }

    public void setRequestId(int requestId){
        request.setRequestId(requestId);
    }

    public LocalDate getRequestRegistrationDate() {
        return request.getRequestRegistrationDate();
    }

    public void setPeriodOfExecutionFromString(String requestRegistrationDateString) {
        request.setPeriodOfExecutionFromString(requestRegistrationDateString);
    }

    public LocalDate getPeriodOfExecution() {//used joda-time
        return request.getPeriodOfExecution();
    }

    public String getAcceptedTheRequest() {
        return request.getAcceptedTheRequest();
    }

    public void setAcceptedTheRequest(String acceptedTheRequest) {
        request.setAcceptedTheRequest(acceptedTheRequest);
    }

    public String getDeclarer() {
        return request.getDeclarer();
    }

    public void setDeclarer(String declarer) {
        request.setDeclarer(declarer);
    }

    public String getSubdivision() {
        return request.getSubdivision();
    }

    public void setSubdivision(String subdivision) {
        request.setSubdivision(subdivision);
    }

    public String getContactFullName() {
        return request.getContactFullName();
    }

    public void setContactFullName(String contactFullName) {
        request.setContactFullName(contactFullName);
    }

    public String getTextOfRequest() {
        return request.getTextOfRequest();
    }

    public void setTextOfRequest(String textOfRequest) {
        request.setTextOfRequest(textOfRequest);
    }

    public List<Log> getActionsOverRequest() {
        return request.getActionsOverRequest();
    }

    public void setActionsOverRequest(List<Log> actionsOverRequest) {
        request.setActionsOverRequest(actionsOverRequest);
    }

    public String getStatusOfRequest() {
        return request.getStatusOfRequest();
    }

    public void setStatusOfRequest(String statusOfRequest) {
        request.setStatusOfRequest(statusOfRequest);
    }

    public String getResponsibleForTheExecutionOfTheRequest() {
        return request.getResponsibleForTheExecutionOfTheRequest();
    }

    public void setResponsibleForTheExecutionOfTheRequest(String responsibleForTheExecutionOfTheRequest) {
        request.setResponsibleForTheExecutionOfTheRequest(responsibleForTheExecutionOfTheRequest);
    }



}

package com.example.projectkfudemo;

import com.example.projectkfudemo.forjson.Building;
import com.example.projectkfudemo.forjson.Log;
import com.example.projectkfudemo.forjson.Offices;
import com.example.projectkfudemo.forjson.Status;
import com.example.projectkfudemo.forjson.Works;

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

    public int getCode() {
        return request.getCode();
    }

    public void setCode(int code) {
        request.setCode(code);
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

    public List<Offices> getSubdivisionList() {
        return request.getSubdivisionList();
    }

    public void setSubdivisionList(List<Offices> subdivisionList) {
        request.setSubdivisionList(subdivisionList);
    }

    public String getContactFullName() {
        return request.getContactFullName();
    }

    public void setContactFullName(String contactFullName) {
        request.setContactFullName(contactFullName);
    }

//    public String getTextOfRequest() {
//        return request.getTextOfRequest();
//    }
//
//    public void setTextOfRequest(String textOfRequest) {
//        request.setTextOfRequest(textOfRequest);
//    }

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

    public Building getBuilding() {
        return request.getBuilding();
    }

    public void setBuilding(Building building) {
        request.setBuilding(building);
    }

    public List<Works> getWorksList() {
        return request.getWorksList();
    }

    public void setWorksList(List<Works> worksList) {
        request.setWorksList(worksList);
    }

    public Status getStatus() {
        return request.getStatus();
    }

    public void setStatus(Status status) {
        request.setStatus(status);
    }

}

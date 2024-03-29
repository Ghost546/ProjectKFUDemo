package com.example.projectkfudemo.parametrclasses.requests;

import com.example.projectkfudemo.parametrclasses.forjson.Building;
import com.example.projectkfudemo.parametrclasses.forjson.Comments;
import com.example.projectkfudemo.parametrclasses.forjson.Log;
import com.example.projectkfudemo.parametrclasses.forjson.Offices;
import com.example.projectkfudemo.parametrclasses.forjson.Status;
import com.example.projectkfudemo.parametrclasses.forjson.Works;

import org.joda.time.LocalDate;

import java.util.List;

public class MyRequest implements RequestGeneral {
    Request request;

    @Override
    public Request getRequest(){
        return request;
    }

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

    public String getDeclarantPost() {
        return request.getDeclarantPost();
    }

    public void setDeclarantPost(String declarantPost) {
        request.setDeclarantPost(declarantPost);
    }

    public String getDeclarantPhone() {
        return request.getDeclarantPhone();
    }

    public void setDeclarantPhone(String declarantPhone) {
        request.setDeclarantPhone(declarantPhone);
    }

    public String getCabinet() {
        return request.getCabinet();
    }

    public void setCabinet(String cabinet) {
        request.setCabinet(cabinet);
    }

    public String getContactFullName() {
        return request.getContactFullName();
    }

    public void setContactFullName(String contactFullName) {
        request.setContactFullName(contactFullName);
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

    public List<Comments> getCommentsList() {
        return request.getCommentsList();
    }

    public void setCommentsList(List<Comments> commentsList) {
        request.setCommentsList(commentsList);
    }

    public Status getStatus() {
        return request.getStatus();
    }

    public void setStatus(Status status) {
        request.setStatus(status);
    }

}

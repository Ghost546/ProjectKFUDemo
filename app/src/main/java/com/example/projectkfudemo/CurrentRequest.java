package com.example.projectkfudemo;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectkfudemo.ui.requestgeneralview.RequestGeneralViewFragment;

import org.joda.time.LocalDate;

public class CurrentRequest {
    Request request;
    public CurrentRequest(Request request) {
        this.request = request;
    }


    public int getRequestId() {
        return request.getRequestId();
    }

    public void setRequestId(int requestId){
        request.setRequestId(requestId);
    }

    public String getRequestRegistrationDate() {
        return request.getRequestRegistrationDate();
    }

    public void setRequestRegistrationDate(String requestRegistrationDate) {
        request.setRequestRegistrationDate(requestRegistrationDate);
    }

    public LocalDate getPeriodOfExecutionInDateFormat() {                                           //used joda-time
        return request.getPeriodOfExecutionInDateFormat();
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

    public String getDataAboutDeclarer() {
        return request.getDataAboutDeclarer();
    }

    public void setDataAboutDeclarer(String dataAboutDeclarer) {
        request.setDataAboutDeclarer(dataAboutDeclarer);
    }

    public String getTextOfRequest() {
        return request.getTextOfRequest();
    }

    public void setTextOfRequest(String textOfRequest) {
        request.setTextOfRequest(textOfRequest);
    }

    public String getActionsOverRequest() {
        return request.getActionsOverRequest();
    }

    public void setActionsOverRequest(String actionsOverRequest) {
        request.setActionsOverRequest(actionsOverRequest);
    }

    public String getStatusOfRequest() {
        return request.getStatusOfRequest();
    }

    public void setStatusOfRequest(String statusOfRequest) {
        request.setStatusOfRequest(statusOfRequest);
    }

}

package com.example.projectkfudemo;

import java.util.ArrayList;
import java.util.List;

public class Search { //Вернуться позже
    List<Request> list = new ArrayList<>();

    String[][] stringsTwo;

    Search() {

    }
    int largeSize = 0;


    public void setList(ArrayList<Request> list) {
        this.list = list;
    }

    public String[] objToString(Request request) {
        int i = 0;
        if(request.getCode() !=0) {
            i++;
        }
        if(request.getRequestRegistrationDate() != null) {
            i++;
        }
        if(request.getPeriodOfExecution() != null) {
            i++;
        }
        if(!request.getDeclarer().equals("")) {
            i++;
        }
        if(!request.getDeclarantPost().equals("")) {
            i++;
        }
        if(!request.getDeclarantPhone().equals("")) {
            i++;
        }
        if(!request.getCabinet().equals("")) {
            i++;
        }
        if(!request.getContactFullName().equals("")) {
            i++;
        }
//        if(request.getActionsOverRequest() != null) {
//            i++;
//        }
        if(request.getBuilding() != null) {
            i += 2;
        }
        if(request.getWorksList() != null) {
            i++;
        }
        if(request.getCommentsList()!= null) {
            i += (request.getCommentsList().size()*3);
            for(int j = 0; j < request.getCommentsList().size(); j++) {
                if(request.getCommentsList().get(j) != null) {
                    i+= request.getCommentsList().get(j).getWorksInCommentsList().size();
                }
            }
        }
        if(request.getType() != null) {
            i++;
        }
        if(request.getSubdivisionList()!= null) {
            i += request.getSubdivisionList().size();
        }
        if(request.getWorkersList() != null) {
            i+= request.getWorkersList().size();
        }

        String [] strings = new String[i];
        i = 0;
        
        if(request.getCode() !=0) {
            strings[i] = String.valueOf(request.getCode());
            i++;
        }
        if(request.getRequestRegistrationDate() != null) {
            strings[i] = String.valueOf(request.getRequestRegistrationDate());
            i++;
        }
        if(request.getPeriodOfExecution() != null) {
            strings[i] = String.valueOf(request.getPeriodOfExecution());
            i++;
        }
        if(!request.getDeclarer().equals("")) {
            strings[i] = request.getDeclarer();
            i++;
        }
        if(!request.getDeclarantPost().equals("")) {
            strings[i] = request.getDeclarantPost();
            i++;
        }
        if(!request.getDeclarantPhone().equals("")) {
            strings[i] = request.getDeclarantPhone();
            i++;
        }
        if(!request.getCabinet().equals("")) {
            strings[i] = request.getCabinet();
            i++;
        }
        if(!request.getContactFullName().equals("")) {
            strings[i] = request.getContactFullName();
            i++;
        }
        if(request.getBuilding() != null) {
            strings[i] = request.getBuilding().getAddress();
            i++;
            strings[i] = request.getBuilding().getName();
            i++;
        }
        if(request.getWorksList() != null) {
            strings[i] = request.getWorksList().get(0).getDescription();
            i++;
        }
        if(request.getCommentsList() != null) {
            for(int j = 0; j < request.getCommentsList().size(); j++) {
                if(request.getCommentsList().get(j) != null) {
                    strings[i] = String.valueOf(request.getCommentsList().get(j).getBeginDate());
                    i++;
                }
            }
        }
        if(request.getType() != null) {
            strings[i] = String.valueOf(request.getType().getName());
            i++;
        }
        if(request.getSubdivisionList() != null) {
            for(int j = 0; j < request.getSubdivisionList().size(); j++) {
                strings[i] = request.getSubdivisionList().get(j).getName();
                i++;
            }
        }
        if(request.getWorkersList() != null) {
            for(int j = 0; j < request.getWorkersList().size(); j++) {
                strings[i] = request.getWorkersList().get(j).getFullname();
                i++;
            }
        }

        return strings;
    }

    public void listToArray() {
        stringsTwo = new String[1][list.size()];
    }
}
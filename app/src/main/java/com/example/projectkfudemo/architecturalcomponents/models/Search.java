package com.example.projectkfudemo.architecturalcomponents.models;

import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestList;

import java.util.ArrayList;
import java.util.List;

public class Search { //Вернуться позже
    List<Request> list;//список для поиска

    List<String[]> listArrays = new ArrayList<>(); //преобразованный список в котором будет выполняться поиск

    List<Request> resultList = new ArrayList<>(); //список с найденными заявками

    public Search(String stringFromSearch, List<Request> list) {
        this.list = list;   //в конструкторе задаем список для дальнешего поиска
        allActions(stringFromSearch);
    }

    private void allActions(String stringFromSearch) {
        setListToArray();
        startSearch(stringFromSearch);

    }

    private void setListToArray() {
        for(int i = 0; i < list.size(); i++) {
            listArrays.add(objToString(list.get(i)));
        }
    }

    private String getValueFromArray(String[] stringsArray, int valueForGetString) {
        String stringFromArrayByValue;
        stringFromArrayByValue = stringsArray[valueForGetString];
        return stringFromArrayByValue;
    }

    private int getStringsArraySize(String[] stringsArray) {
        return stringsArray.length;
    }

    private void startSearch(String stringFromSearching) {
        String checking;
        int countResultList = 0;
        for(int i = 0; i < listArrays.size(); i++) {    //в цикле определяется в каких заявках(массивах String[]) существуют данные совпадающие с искомыми данными
            for(int j = 0; j < getStringsArraySize(listArrays.get(i)); j++){
                checking = getValueFromArray(listArrays.get(i), j);     //получает значение из индекса listArrays(лист заявок) i и String[](преобразованные данные заявки) j
                if(stringFromSearching!=null) {     //пропускает сравнение случайно-пустых значений
                    if(checking != null) {
                        if(checking.contains(stringFromSearching)) {    //проверяет на совпадения содержимое из массива с поисковым запросом
                            //ввод формимруемого массива и исключение повторений в этом же массиве
                            if(countResultList == 0) {  //даёт ввод на первый цикл заполнения
                                setResultList(list.get(i));
                                countResultList++;  //счетчик формируемого массива для вывода
                            }
                            //проверяет текущую заявку по основному массиву заявок с заявками формируемого массива
                            if(countResultList != 0 & !getResultList().get(countResultList-1).equals(list.get(i))){
                                setResultList(list.get(i)); //в случае совпадения выписывает заявки в другой лист для отображения результата поиска
                                countResultList++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Найдено " + resultList.size() + " заявок");
    }

    private void setResultList(Request request) {
        resultList.add(request);
    }

    public List<Request> getResultList() {
        return resultList;
    }

    public RequestList getResultListOnView() {
        RequestList requestList = new RequestList();
        requestList.setRequests(resultList);
        return requestList;
    }

    private String[] objToString(Request request) {  //преобразование объекта в массив строк для алгоритма поиска
        int count = 0; //создается счетчик для обозначения массива
        if(request.getCode() !=0) {
            count++;
        }
        if(request.getRequestRegistrationDate() != null) {
            count++;
        }
        if(request.getPeriodOfExecution() != null) {
            count++;
        }
        if(!request.getDeclarer().equals("")) {
            count++;
        }
        if(!request.getDeclarantPost().equals("")) {
            count++;
        }
        if(!request.getDeclarantPhone().equals("")) {
            count++;
        }
        if(!request.getCabinet().equals("")) {
            count++;
        }
        if(!request.getContactFullName().equals("")) {
            count++;
        }
//        if(request.getActionsOverRequest() != null) {
//            i++;
//        }
        if(request.getBuilding() != null) {
            count += 2;
        }
        if(request.getWorksList() != null) {
            count++;
        }
        if(request.getCommentsList()!= null) {
            count += (request.getCommentsList().size()*3);
            for(int j = 0; j < request.getCommentsList().size(); j++) {
                if(request.getCommentsList().get(j) != null) {
                    count+= request.getCommentsList().get(j).getWorksInCommentsList().size();
                }
            }
        }
        if(request.getType() != null) {
            count++;
        }
        if(request.getSubdivisionList()!= null) {
            count += request.getSubdivisionList().size();
        }
        if(request.getWorkersList() != null) {
            count+= request.getWorkersList().size();
        }

        String [] strings = new String[count]; //создается массив по счетчику
        count = 0; //счетчик обнуляется для заполнения массива
        
        if(request.getCode() !=0) {
            strings[count] = String.valueOf(request.getCode());
            count++;
        }
        if(request.getRequestRegistrationDate() != null) {
            strings[count] = String.valueOf(request.getRequestRegistrationDate());
            count++;
        }
        if(request.getPeriodOfExecution() != null) {
            strings[count] = String.valueOf(request.getPeriodOfExecution());
            count++;
        }
        if(!request.getDeclarer().equals("")) {
            strings[count] = request.getDeclarer();
            count++;
        }
        if(!request.getDeclarantPost().equals("")) {
            strings[count] = request.getDeclarantPost();
            count++;
        }
        if(!request.getDeclarantPhone().equals("")) {
            strings[count] = request.getDeclarantPhone();
            count++;
        }
        if(!request.getCabinet().equals("")) {
            strings[count] = request.getCabinet();
            count++;
        }
        if(!request.getContactFullName().equals("")) {
            strings[count] = request.getContactFullName();
            count++;
        }
        if(request.getBuilding() != null) {
            strings[count] = request.getBuilding().getAddress();
            count++;
            strings[count] = request.getBuilding().getName();
            count++;
        }
        if(request.getWorksList() != null) {
            strings[count] = request.getWorksList().get(0).getDescription();
            count++;
        }
        if(request.getCommentsList() != null) {
            for(int j = 0; j < request.getCommentsList().size(); j++) {
                if(request.getCommentsList().get(j) != null) {
                    strings[count] = String.valueOf(request.getCommentsList().get(j).getBeginDate());
                    count++;
                }
            }
        }
        if(request.getType() != null) {
            strings[count] = String.valueOf(request.getType().getName());
            count++;
        }
        if(request.getSubdivisionList() != null) {
            for(int j = 0; j < request.getSubdivisionList().size(); j++) {
                strings[count] = request.getSubdivisionList().get(j).getName();
                count++;
            }
        }
        if(request.getWorkersList() != null) {
            for(int j = 0; j < request.getWorkersList().size(); j++) {
                strings[count] = request.getWorkersList().get(j).getFullname();
                count++;
            }
        }

        return strings;//позвращаем полученный массив
    }
}

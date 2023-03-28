package com.example.tp_api.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListRecord {

    private static ArrayList<Record> listRecord = new ArrayList<>();
    private static ArrayList<Record> listRecordFavorite = new ArrayList<>();

    private static boolean favorite = false;

    private static ListRecord instance;

    private ListRecord(){
        listRecord = new ArrayList<>();
        listRecordFavorite = new ArrayList<>();
    }

    public static ListRecord getInstance(){
        if (instance == null) {
            instance = new ListRecord();
        }

        return instance;
    }

    public ArrayList<Record> getListRecord() {
        return listRecord;
    }
    public ArrayList<Record> getListRecordFavorite() {
        return listRecordFavorite;
    }

    public void push(List<Record> list){
        listRecord.addAll(list);
    }

    public Record getRecord(int i){
        return listRecord.get(i);
    }

    public void addFavorite(Record record){
        listRecordFavorite.add(record);
    }

    public void remooveFavorite(Record record){
        listRecordFavorite.remove(record);
    }

    public Record getRecordFavorite(int i){
        return listRecordFavorite.get(i);
    }

    public boolean isFavorite(Record record){
        return listRecordFavorite.contains(record);
    }

    public void changeFavorite(){
        favorite = !favorite;
    }

    public boolean afficheFavorite(){
        return favorite;
    }
}

package com.donga.examples.boomin.Singleton;

import com.donga.examples.boomin.retrofit.retrofitProfessor.Professor;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by pmk on 17. 2. 18.
 */
public class ProSingleton {
    private static ProSingleton mInstance = null;
    private ArrayList<Professor> professorArrayList;
    private JSONArray professorJSONArray;

    public static ProSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new ProSingleton();
        }
        return mInstance;
    }

    private ProSingleton() {
    }

    public ArrayList<Professor> getProfessorArrayList() {
        return professorArrayList;
    }

    public void setProfessorArrayList(ArrayList<Professor> professorArrayList) {
        this.professorArrayList = professorArrayList;
    }

    public JSONArray getProfessorJSONArray() {
        return professorJSONArray;
    }

    public void setProfessorJSONArray(JSONArray professorJSONArray) {
        this.professorJSONArray = professorJSONArray;
    }
}

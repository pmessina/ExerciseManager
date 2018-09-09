package com.pirateman.exercisemanager.exercise;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;
import android.view.View;

import com.pirateman.exercisemanager.ExerciseInterval;

import java.util.List;

@Entity
public class Exercise
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String muscleGroup;
    private String method;

    public Exercise()
    {

    }

    @Ignore
    public Exercise(String name, String muscleGroup, String method) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.method = method;
    }

    @ForeignKey(entity = ExerciseInterval.class, parentColumns = "id", childColumns = "rowId")
    @Ignore
    private List<Integer> intervalIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getMethod() {
        return method;
    }


    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + method + " " + muscleGroup;
    }

}

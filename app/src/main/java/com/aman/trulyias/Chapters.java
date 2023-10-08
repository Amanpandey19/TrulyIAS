package com.aman.trulyias;

import java.util.ArrayList;

public class Chapters {
    String name;
    ArrayList<Topics> topicsArrayList;

    private boolean isExpandable;

    public Chapters(String name, ArrayList<Topics> topicsArrayList) {
        this.name = name;
        this.topicsArrayList = topicsArrayList;
        isExpandable = false;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Topics> getTopicsArrayList() {
        return topicsArrayList;
    }

    public void setTopicsArrayList(ArrayList<Topics> topicsArrayList) {
        this.topicsArrayList = topicsArrayList;
    }
}

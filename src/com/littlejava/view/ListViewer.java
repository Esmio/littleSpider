package com.littlejava.view;

import com.littlejava.model.Viewable;

import java.util.ArrayList;

public class ListViewer {
    private ArrayList<Viewable> viewableList;

    public ListViewer(ArrayList<Viewable> newsList) {
        this.viewableList = newsList;
    }

    public void display() {
        for (Viewable viewableItem: viewableList) {
            System.out.println("-----------------------------------------");
            viewableItem.display();
        }
    }

}

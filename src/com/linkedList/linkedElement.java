package com.linkedList;

import java.util.Random;

/**
 * Created by baylrock on 12.11.2015.
 */
public class linkedElement {


    static int ClassSize = 0;
    private int ID = 0;
    private linkedElement nextElement;
    private linkedElement prevElement;


    Integer Value = 0;


    linkedElement() {

        this.Value =  new Random().nextInt(100) + 1 ;
        //this.Value = ClassSize;
        this.ID = ClassSize;
        ClassSize++;
    }

    public linkedElement getNextElement() {
        return this.nextElement;
    }

    void setNextElement(linkedElement link) {
        this.nextElement = link;

    }
    void setPrevElement(linkedElement link) {
        this.prevElement = link;

    }

    public linkedElement getPrevElement() {
        return this.prevElement;
    }

    Integer getValue() {
        return this.Value;
    }

    void setValue(Integer Value) {
        this.Value = Value;
    }

    static int getSize() {
        return ClassSize;
    }

    public int getID() {
        return ID;
    }


}

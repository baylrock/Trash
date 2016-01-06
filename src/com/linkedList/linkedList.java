package com.linkedList;

/**
 * Created by baylrock on 12.11.2015.
 */
public class linkedList {


    private linkedElement Currentlink;
    static private linkedElement FirstLink;


    public linkedList(int range) {
        this.Currentlink = new linkedElement();
        this.FirstLink = this.Currentlink;
        for (int i=1; i<range; i++) {
            this.Currentlink.setNextElement(new linkedElement());
            if (linkedElement.getSize()!=0) {
                this.Currentlink.getNextElement().setPrevElement(this.Currentlink);
            }
            this.Currentlink = this.Currentlink.getNextElement();
        }
        this.Currentlink = this.FirstLink;
    }

    public int getValue() {
        if (this.FirstLink == null) {Print("\nEmptyList "); return 0;}
        return this.Currentlink.getValue();
    }

    public void printAllValues() {
        linkedElement buff_link = this.FirstLink;
        while (buff_link!=null) {
            if (buff_link==null) {Print("null | "); }
            else {
                Print(buff_link.getID()+"->"+buff_link.getValue()+" | ");
                buff_link = buff_link.getNextElement();
            }
        }
    }

    public void nextLink() {
        //if (Currentlink.getNextElement()==null) {return false;}
        this.Currentlink = this.Currentlink.getNextElement();
    }

    public void removeCurrentLink() {

        if (this.Currentlink.getNextElement()==null && this.Currentlink.getPrevElement()!=null) {
            Println("\nDeleted with ID ->" + this.Currentlink.getID());
            this.Currentlink = this.Currentlink.getPrevElement();
            this.Currentlink.setNextElement(null);
                    linkedElement.ClassSize--;
            return;}
        else if (this.Currentlink.getPrevElement()==null) {
            if (linkedElement.ClassSize>0) {
                Println("\nDeleted with ID ->" + this.Currentlink.getID());
                this.Currentlink = this.Currentlink.getNextElement();
                FirstLink = this.Currentlink;
                linkedElement.ClassSize--;

            } else {
                FirstLink=null; this.Currentlink=null; linkedElement.ClassSize--;
            }

            return;
        } else {
            Println("\nDeleted with ID -> " + this.Currentlink.getID());
            this.Currentlink.getPrevElement().setNextElement(this.Currentlink.getNextElement());
            this.Currentlink= this.Currentlink.getNextElement();
            linkedElement.ClassSize--;
        }
    }


    public void insertLink(linkedElement newLink) {
            newLink.setNextElement(this.Currentlink.getNextElement());
        this.Currentlink.setNextElement(newLink);
    }

    public void moveToFrontIn (int steps) {
        for(int i=0;i<steps; i++) {
            linkedElement nextLink = this.Currentlink.getNextElement();
            if (nextLink!=null) {this.Currentlink=nextLink;}
            else {
                return;
            }
        }
    }

    public void moveToBackIn (int steps) {
        for(int i=0;i<steps; i++) {
            linkedElement prevLink = this.Currentlink.getPrevElement();
            if (prevLink!=null) {this.Currentlink=prevLink;}
            else {
                return;
            }
        }
    }

    public void printVlaue() {

         if (this.Currentlink==null) {
            this.Currentlink=FirstLink;
        }
        Println("Cuurent ID = " + this.Currentlink.getID()+"  ");
        Println("Value = " + this.Currentlink.getValue()+"  ");
    }

    public void moveToFirstLink () {
        this.Currentlink = FirstLink;
    }

    public linkedElement getCurrentlink() {
        return this.Currentlink;
    }


    public static void Println(String text) {
        System.out.println(text);
    }
    public static void Print(String text) {
        System.out.print(text);
    }



}

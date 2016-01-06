package com.linkedList;

import com.main.Print;

/**
 * Created by baylrock on 23.12.2015.
 */
public class initiator {
    public initiator() {
        linkedList list = new linkedList(10);

        list.printAllValues();
        list.moveToFirstLink();
        // list.getCurrentlink().setValue(1);
        Integer FirstValue = list.getValue() * 2;
        linkedElement buffElement = null;

        while (true) {
            if (list.getCurrentlink() == null) {
                break;
            }
            if (list.getValue() > FirstValue) {
                buffElement = list.getCurrentlink();
            }
            list.nextLink();
        }
        list.moveToFirstLink();

        if (buffElement != null) {
            while (true) {
                if (list.getCurrentlink() == null) {
                    break;
                }

                if (list.getCurrentlink().getID() == buffElement.getID()) {
                    list.removeCurrentLink();
                }
                list.nextLink();
            }
            list.printAllValues();
        } else {
            Print.Println("\nNo suitable values!");
        }

        list.printVlaue();
    }
}

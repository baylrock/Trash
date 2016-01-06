package com.main;

import com.cardGame.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by baylrock on 28.12.2015.
 */
final public class IOFile {

    static public void saveFile(File file, ArrayList<Card> list) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (Card item: list) {
            bufferedWriter.write(item.getQuestion()+"/");
            bufferedWriter.write(item.getAnswer()+"\n");
        }
        bufferedWriter.close();
    }

    static public ArrayList<Card> OpenFile(File file, ArrayList<Card> list) {
        String[] buf;
       try {
           BufferedReader reader = new BufferedReader(new FileReader(file));
           String line = null;
           while((line = reader.readLine())!=null) {
               buf = line.split("/");
               Card card = new Card(buf[0],buf[1]);
               list.add(card);
           }
       } catch (Exception ex) {

       }
        return list;
    }

}

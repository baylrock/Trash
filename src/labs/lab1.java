package labs;

import javax.swing.*;
import java.util.*;

/**
 * Created by baylrock on 07.02.2016.
 */
public class lab1 extends JFrame {

    public static void main (String[] args) {
        lab1 lab = new lab1();
        lab.execute();
    }

    static private <T extends List> List<T> fill(T arg, int size,int levels) {
        List<T> list = null;
        try {
            list = (T)arg.getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        while (i<size) {

            if (levels>1) {
                list.add((T)fill(arg,size,levels-1));
            }
            else {
                Object buf = null;
                try {
                    buf = arg.getClass().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.add((T)buf);
            }
            i++;
        }
        return list;
    }

    private void exe(int size) {
        List<List<List<Integer>>> array = fill(new ArrayList<>(), size, 2);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int z = 0; z < size; z++) {
                    array.get(i).get(j).add(new Random().nextInt(10));
                }
            }
        }
    }

    private void execute() {

        exe(10);
      /*  List<List<List<Integer>>> olol = new ArrayList<>();
        int x = 0;
        while (x<100) {
            olol.add(new ArrayList<>());
            int y=0;
            while (y<100) {
                olol.get(x).add(new ArrayList<>());
                int z=0;
                while (z<100) {
                    olol.get(x).get(y).add(z);
                    z++;
                }
                y++;
            }
            x++;
        }

        for (int i = 0;i<olol.size(); i++) {
            for (int j=0;j<olol.get(i).size();j++) {
                for (int z=0;z<olol.get(i).get(j).size();z++) {
                    System.out.print(olol.get(i).get(j).get(z)+" ");
                }
                System.out.println();
            }
            System.out.println();
        }*/





        LinkedHashSet<int[]> pairs = new LinkedHashSet<>();
        int[] intArray = new int[100];

        for (int i = 0, value, max = 15; i<100; i++) {
            value = new Random().nextInt(17);
            System.out.print(value + "|");
            intArray[i] = value;

            if(value>max) {
                pairs.add(new int[] {value,i});
            }
            if (pairs.size()==10) {
                System.out.println();
                for(int[] pair: pairs) {
                    System.out.println("Value: " + pair[0] + "; ID: " + pair[1]);
                }
                return;
            }
        }
        if (pairs.size()==0) {
            System.out.println("\nТаких значений нет!");
        } else {
            System.out.println();
            for(int[] pair: pairs) {
                System.out.println("Value: " + pair[0] + "; ID: " + pair[1]);
            }
            System.out.println("\nТаких значений больше нет!");
        }
    }
}

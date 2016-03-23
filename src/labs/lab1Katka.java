package labs;

import java.util.Random;

/**
 * Created by baylrock on 14.03.2016.
 */
public class lab1Katka {
    public static void main( String[] args ) {
        //Массив исходных значений и результирующий массив  (матрицы 10х10)
        int[][] vals = new int[10][10];
        int[][] result = new int[10][10];

        Random r = new Random();
        int max = 0;
        int min = Integer.MAX_VALUE;

        //Массив индексов минемального и максемального значинй в диагоналях
        int[] index = new int[3];

        //Наполнение исходного/результирующего массивов
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j<10; j++) {
                vals[i][j] = result[i][j] = r.nextInt(100);
            }
        }

        //Поиск в диагонялях исходного массива максимального и минимального значений
        for (int i = 0, j, nextMain, nextOff; i < 10; i++) {
            j = 9 - i; // указатель для побочной диагонали
            nextMain = vals[i][i];
            nextOff = vals[i][j];
            if (nextMain>max) {
                max = nextMain;
                index[0] = i;  //схранение указателя на максимальное значение
            }
            if (nextOff<min) {
                min=nextOff;
                //сохранение указателей на минимальное значение
                index[1] = i;
                index[2] = j;
            }
        }
        //изменение результирующего массива
        result[index[0]][index[0]] = min;
        result[index[1]][index[2]] = max;


        //Вывод исходного и результирующего массивов
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j<10; j++) {
                // Если число меньше 10 - добавить 0 перед числом
                // Если число не последнее в строке матрицы, добавить запятую после числа
                System.out.print((vals[i][j]<10?("0"+vals[i][j]):vals[i][j]) + (j==9?"":", "));
            }
            System.out.print( "     " );
            for (int j = 0; j < 10; j++) {
                System.out.print((result[i][j]<10?("0"+result[i][j]):result[i][j]) + (j==9?"":", "));
            }
            System.out.println();
        }

        //Вывод максимального и минимального значений с указателями на них
        System.out.println("In Main diag: value - " + max + " on position (x:y): " + index[0] + ":" + index[0] + "\n" +
                           "In Off diag: value - " + min + " on position (x:y): " + index[2] + ":" + index[1]);
    }
}

package labs;

import java.util.*;

/**
 * Created by baylrock on 07.02.2016.
 */
public class lab1 {

    public static void main( String[] args ) {
        LinkedHashSet<int[]> pairs = new LinkedHashSet<>();  //Коллекция для хранения резултатирующих значений (индекс => число)
        int[] intArray = new int[100];  //Массив исходных значений
        int max = 15;  //Верхняя граница условия поиска

        //Наполнения ихсодного массива случайными значениями
        for (int i = 0; i < 100; i++) {
            System.out.print( (intArray[i] = new Random().nextInt( 17 )) + "|" );
        }
        
        //Поиск знаений соответсвующих условию (max)
        for (int i = 0, value; i < 100; i++) {
            value=intArray[i];
            if ( value > max ) {
                pairs.add( new int[] {value, i} ); // Сохранение пары Индекс=>Число в коллекцию
            }

            //Если колличество найденных пар дошло необходимой отметки
            if ( pairs.size() == 10 ) {
                System.out.println();
                //Вывод всех найденных пар (Индекс=>Число) в коллекции на екран
                for (int[] pair : pairs) {
                    System.out.println( "Value: " + pair[0] + "; ID: " + pair[1] );
                }
                return;
            }
        }
        if ( pairs.size() == 0 ) {
            System.out.println( "\nТаких значений нет!" );
        } else {
            System.out.println();
            for (int[] pair : pairs) {
                System.out.println( "Value: " + pair[0] + "; ID: " + pair[1] );
            }
            System.out.println( "\nТаких значений больше нет!" );
        }
    }
}
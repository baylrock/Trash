package labs;

import java.util.Random;

/**
 * Created by baylrock on 20.03.2016.
 */
public class lab1Ulia {

    public static void main( String[] args ) {
        int[][] A = new int[4][5];
        int[][] B = new int[5][7];

        /**
         *  indexA and indexB (data meaning)
         *  0 - val
         *  1 - row
         *  2 - col
         */
        int[] indexA = {0,0,0};
        int[] indexB = {Integer.MAX_VALUE,0,0};
        StringBuilder printA = new StringBuilder(  );
        StringBuilder printB = new StringBuilder(  );
        StringBuilder sout = new StringBuilder(  );
        Random r = new Random( );

        sout.append(" ROWSxCOLS(A)           COLSxROWS(B)\n" +
                           "|______________________________________|\n");

        for (int i=0; i<5; i++) {
            printA.setLength( 0 );
            printB.setLength( 0 );
            for (int j = 0; j<7; j++) {
                if ( j < 4 ) {
                    A[j][i] = r.nextInt( 90 ) + 10;
                    printA.append(A[j][i] + (j == 3 ? "" : ",") );
                    if ( A[j][i] > indexA[0] ) {
                        indexA[0] = A[j][i];
                        indexA[1] = j;
                        indexA[2] = i;
                    }
                }
                B[i][j] = r.nextInt(90)+10;
                printB.append(B[i][j] + (j==6?"":","));
                if (B[i][j] < indexB[0]) {
                    indexB[0] = B[i][j];
                    indexB[1] = i;
                    indexB[2] = j;
                }
            }
            sout.append( "|"+printA + "|     |" + printB+"|\n" );


        }

        sout.append("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\nMax value of array \"A\": " + indexA[0] + "  On row: " + (indexA[1]+1) + "; col: " + (indexA[2]+1) +
                "\nMin value of array \"B\": " + indexB[0] + "  On row: " + (indexB[1]+1) + "; col: " + (indexB[2]+1) +
                "\n\n ROWSxCOLS(A)           COLSxROWS(B)\n|______________________________________|\n");



        for (int i=0; i<5; i++) {
            printA.setLength( 0 );
            printB.setLength( 0 );
            int temp = A[indexA[1]][i];
            A[indexA[1]][i] = B[i][indexB[2]];
            B[i][indexB[2]] = temp;

            for (int j = 0; j<7; j++) {
                if (j<4) {
                    printA.append(A[j][i] + (j == 3 ? "" : ",") );
                }
                printB.append(B[i][j] + (j==6?"":","));
            }
            sout.append("|"+printA + "|     |" + printB+"|\n");

        }
        sout.append("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        System.out.println(sout);


    }

}

package com.example.algorithimproject_1;

public class DriverTesting {

    public static void main(String[] args) {
        int arr1[] = { 11, 7, 13, 12 };
        int n = arr1.length;
        System.out.println("\n\n\n" + optimalStrategyOfGame(arr1, n));
    }


    static int optimalStrategyOfGame(int arr[], int n)
    {
        int table[][] = new int[n][n];
        int gap, i, j, x, y, z;
        for (gap = 0; gap < n; ++gap) {
            for (i = 0, j = gap; j < n; ++i, ++j) {

                if((i + 2) <= j)
                    x = table[i + 2][j];
                 else
                    x = 0;
                 if((i + 1) <= (j - 1))
                     y = table[i + 1][j - 1];
                 else
                     y = 0;
                 if (i <= (j - 2))
                     z = table[i][j - 2];
                 else
                     z = 0;

                table[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));

            }
        }
        System.out.print("          ");
        for (int s = 0; s < arr.length; s++){
            if(arr[s] > 9)
                System.out.print(arr[s] + "| ");
            else
                System.out.print(arr[s] + " | ");
        }
        System.out.println("\n");



        for (int s = 0; s < table.length; s++){
            if(arr[s] > 9)
                System.out.print("| " + arr[s] + " -> | ");
            else
                System.out.print("| " + arr[s] + "  -> | ");
            for (int p = 0; p < table[s].length; p++){
                if(table[s][p] > 9)
                    System.out.print(table[s][p] + "  ");
                else
                    System.out.print(table[s][p] + "   ");
            }
            System.out.print("\n|\n");
        }

        System.out.println("\n\n\n");

        String numbers1 = "";
        int n1=0;
        int n2=0;
        String numbers2 = "";

        for (int o = table.length-1; o-1 >= 0; o-=2){
            numbers1+=table[o][o] + "    ";
            n1+=table[o][o];
        }

        for (int o = 0; o < table.length; o+=2){
            numbers2+=table[o][o] + "    ";
            n2+=table[o][o];
        }

        if(n2>n1)
            System.out.println("Coins to get the optimal solution --->  " + numbers2);
         else
            System.out.println("Coins to get the optimal solution --->  " + numbers1);



        return table[0][n - 1];
    }


}

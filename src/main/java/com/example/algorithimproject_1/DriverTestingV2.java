package com.example.algorithimproject_1;

public class DriverTestingV2 {

    public static void main(String[] args) {

        int[] arr = { 69, 96, 4, 5, 10, 15, 17, 18 };

        findOptimalSolution(arr);

    }

    private static void findOptimalSolution(int[] arr) {

        PlayerSpot[][] table = new PlayerSpot[arr.length][arr.length];

        int gap = 1;

        for (int i = 0; i < table.length; i++){ // initialize bottom half of the table, always if j > i then we are in the bottom half
            for (int j = (i+1); j < table[i].length; j++){
                table[i][j] = new PlayerSpot(0,0);
                System.out.print(table[i][j].getFirst().getSum());
            }
            System.out.println();
        }




        for(int i = 0, j = 0; (i <= table.length - 1 && j >= 0) ; ){ // 4, 15, 3, 7, 8, 9
            if ((i == 0 && j == 0) || (i == arr.length -1 && j == arr.length-1 )){ // this solves the edge cases where i has no previous or i has no bottom number

                table[i][j] = new PlayerSpot(arr[i],0);
                System.out.println(table[i][j].getFirst().getSum()+ ", 0" );

            } else if (table[i-1][j].getFirst().getSum() == 0 && table[i][j+1].getFirst().getSum() == 0){

                table[i][j] = new PlayerSpot(arr[i],0);
                System.out.println(table[i][j].getFirst().getSum() + ", 0" );

            } else {
                if (i-2 < 0){
                    table[i][j] = new PlayerSpot(Math.max(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()),Math.min(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()));
                    System.out.println(table[i][j].getFirst().getSum() + ", " + table[i][j].getSecond().getSum());
                } else if(table[i-2][j].getFirst().getSum() == 0){
                    table[i][j] = new PlayerSpot(Math.max(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()),Math.min(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()));
                    System.out.println(table[i][j].getFirst().getSum() + ", " + table[i][j].getSecond().getSum());
                } else {
                    int playerOne;
                    int playerTwo;
                    if (arr[i] + table[i-1][j].getSecond().getSum() > arr[j] + table[i][j+1].getSecond().getSum()){
                        playerOne = arr[i] + table[i-1][j].getSecond().getSum();
                        playerTwo = table[i-1][j].getFirst().getSum();
                    } else {
                        playerOne = arr[j] + table[i][j+1].getSecond().getSum();
                        playerTwo = table[i][j+1].getFirst().getSum();
                    }
                    table[i][j] = new PlayerSpot(playerOne,playerTwo);
                    System.out.println(playerOne + ", " + playerTwo);
                }
            }

            // powered by nabhan
            //table[i][j] = new PlayerSpot();

            if (i == table.length - 1 ){
                i = gap;
                gap++;
                j = 0;
            } else {
                i++;
                j++;
            }

        }


        for (int s = 0; s < table.length; s++){
            if(arr[s] > 9)
                System.out.print("| " + arr[s] + " -> | ");
            else
                System.out.print("| " + arr[s] + "  -> | ");
            for (int p = 0; p < table[s].length; p++){
                if(table[s][p].getFirst().getSum() > 9)
                    System.out.print(table[s][p].getFirst().getSum() + "  ");
                else
                    System.out.print(table[s][p].getFirst().getSum() + "   ");
            }
            System.out.print("\n|\n");
        }


    }


}

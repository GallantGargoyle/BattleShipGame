import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");

        //Initializing the boards
        char[][] playerOneShips = new char[5][5];
        char[][] playerOneShots = new char[5][5];
        char[][] playerTwoShips = new char[5][5];
        char[][] playerTwoShots = new char[5][5];

        //Setting each array to initial state
        for (int row = 0; row<5; row++) {
            for (int col = 0; col<5; col++) {
                playerOneShips[row][col] = '-';
                playerOneShots[row][col] = '-';
                playerTwoShips[row][col] = '-';
                playerTwoShots[row][col] = '-';
            }
        }

        //Getting user input for the battleships
        Scanner coordinates = new Scanner(System.in);
        for (int playerCount = 1; playerCount<=2; playerCount++) {
            System.out.println("PLAYER " + playerCount + " ENTER YOUR SHIPS' COORDINATES (Row first, then column, separated by a space: \nFor example, 1 1\n");

            //Takes five battleship coordinates as input
            for (int shipCount = 1; shipCount<=5; shipCount++) {
                System.out.println("Enter ship " + shipCount + " location: ");
                int xCoordinate = coordinates.nextInt();
                int yCoordinate = coordinates.nextInt();

                //Placing ships
                if (coordinateChecker(xCoordinate, yCoordinate)) {
                    if (playerCount == 1) {
                        if (playerOneShips[xCoordinate][yCoordinate] != '@') {
                            playerOneShips[xCoordinate][yCoordinate] = '@';
                        }
                        else {
                            System.out.println("You have already placed a ship there. Choose different coordinates.");
                            shipCount--;
                            continue;
                        }
                    }
                    else {
                        if (playerTwoShips[xCoordinate][yCoordinate] != '@') {
                            playerTwoShips[xCoordinate][yCoordinate] = '@';
                        }
                        else {
                            System.out.println("You have already placed a ship there. Choose different coordinates.");
                            shipCount--;
                            continue;
                        }
                    }
                }
                else {
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    shipCount--;
                    continue;
                }

                //Printing out the board after ships are placed
                if (playerCount == 1) {
                    printBattleShip(playerOneShips);
                }
                else
                    printBattleShip(playerTwoShips);
            }

            //Printing 100 lines after each player input
            int i = 0;
            do {
                i++;
                System.out.print("\n");
            }while (i<100);
        }

        //Shots fired!
        int shipsSunkOne = 0, shipsSunkTwo = 0;
        while(shipsSunkOne<5 && shipsSunkTwo<5) {
            for (int playerCount = 1; playerCount<=2; playerCount++) {

                //Getting playerOne's shot
                if (playerCount == 1) {
                    System.out.println("Player one, enter hit row/column: ");
                    int xShot = coordinates.nextInt();
                    int yShot = coordinates.nextInt();

                    //Checking for valid shot
                    if (coordinateChecker(xShot, yShot)) {
                        //Checking if there has already been a shot fired there
                        if(playerOneShots[xShot][yShot] == 'O' || playerOneShots[xShot][yShot] == 'X') {
                            System.out.println("You have already fired there. Please enter different coordinates.");
                            playerCount--;
                            continue;
                        }
                        //Checking if the shot hits a ship or not
                        if(playerTwoShips[xShot][yShot] == '@') {
                            System.out.println("PLAYER 1 HIT PLAYER 2'S SHIP!");
                            playerOneShots[xShot][yShot] = 'X';
                            playerTwoShips[xShot][yShot] = 'X';
                            shipsSunkOne++;
                            printBattleShip(playerOneShots);

                            //Checking if all ships have been sunk
                            if (shipsSunkOne == 5) {
                                System.out.println("PLAYER 1 WINS! YOU HAVE SUNK ALL YOUR OPPONENT'S SHIPS!");
                                System.out.print("Final boards: \n\n");
                                printBattleShip(playerTwoShips);
                                printBattleShip(playerOneShips);
                                playerCount = 4;
                            }
                        }
                        else {
                            System.out.println("PLAYER 1 MISSED!");
                            playerOneShots[xShot][yShot] = 'O';
                            playerTwoShips[xShot][yShot] = 'O';
                            printBattleShip(playerOneShots);
                        }

                    }

                    //Invalid shot
                    else {
                        System.out.println("Invalid coordinates. Please enter different coordinates.");
                        playerCount--;
                    }
                }

                //Getting playerTwo's shot
                else {
                    System.out.println("Player two, enter hit row/column: ");
                    int xShot = coordinates.nextInt();
                    int yShot = coordinates.nextInt();

                    //Checking for valid shot
                    if (coordinateChecker(xShot, yShot)) {
                        //Checking if there has already been a shot fired there
                        if(playerTwoShots[xShot][yShot] == 'O' || playerTwoShots[xShot][yShot] == 'X') {
                            System.out.println("You have already fired there. Please enter different coordinates.");
                            playerCount--;
                            continue;
                        }
                        //Checking if the shot hits a ship or not
                        if(playerOneShips[xShot][yShot] == '@') {
                            System.out.println("PLAYER 2 HIT PLAYER 1'S SHIP!");
                            playerTwoShots[xShot][yShot] = 'X';
                            playerOneShips[xShot][yShot] = 'X';
                            shipsSunkTwo++;
                            printBattleShip(playerTwoShots);

                            //Checking if all ships have been sunk
                            if(shipsSunkTwo == 5) {
                                System.out.println("PLAYER 2 WINS! YOU SUNK ALL YOUR OPPONENT'S SHIPS!");
                                System.out.print("Final boards: \n\n");
                                printBattleShip(playerOneShips);
                                printBattleShip(playerTwoShips);
                                playerCount = 4;
                            }
                        }
                        else {
                            System.out.println("PLAYER 2 MISSED!");
                            playerTwoShots[xShot][yShot] = 'O';
                            playerOneShips[xShot][yShot] = 'O';
                            printBattleShip(playerTwoShots);
                        }

                    }
                    //Invalid shot
                    else {
                        System.out.println("Invalid coordinates. Please enter different coordinates.");
                        playerCount--;
                    }
                }
            }
        }

    }

    //This method checks if entered coordinates are valid
    private static boolean coordinateChecker(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }


    // Use this method to print game boards to the console.
    private static void printBattleShip(char[][] player) {
        System.out.print("  ");
        for (int row = -1; row < 5; row++) {
            if (row > -1) {
                System.out.print(row + " ");
            }
            for (int column = 0; column < 5; column++) {
                if (row == -1) {
                    System.out.print(column + " ");
                } else {
                    System.out.print(player[row][column] + " ");
                }
            }
            System.out.println("");
        }
    }
}

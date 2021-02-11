import java.util.Scanner;


public class Places {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows: \n > ");
        int rows = sc.nextInt();
        System.out.print("Enter the number of seats in each row: \n > ");
        int seats = sc.nextInt();

        char[][] room = createRoom(rows, seats);
        int tickets = 0;
        final int SIZE_ROOM = rows * seats;
        int currentProfit = 0;
        boolean ch = true;

        while (ch) {
            callingMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    setOfSeats(room, rows, seats);
                    break;
                case 2:
                    currentProfit += buyingTicket(room, rows, seats);
                    tickets++;

                    break;
                case 3:
                    callingStatistics(tickets, SIZE_ROOM, currentProfit, maxProfit(rows, seats));
                    break;
                case 0:
                    ch = false;
                    break;
            }
        }
    }

    private static void callingStatistics(int tickets, int sizeRoom, int currentProfit, int maxProfit) {
        System.out.println("Number of purchased tickets: " + tickets);
        System.out.printf("%s %.2f %s %n", "Percentage:", (float) tickets / sizeRoom * 100, "%");
        System.out.println("Current income: $" + currentProfit);
        System.out.println("Total income: $" + maxProfit);
    }

    private static int buyingTicket(char[][] room, int rows, int seats) {
        int currentProfit = 0;
        int cost = 10;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a row number: \n > ");
            int ch_row = sc.nextInt();
            System.out.print("Enter a seat number in that row: \n > ");
            int ch_seat = sc.nextInt();
            if (ch_row > rows || ch_seat > seats) {
                System.out.println("Wrong input!\n");
                continue;
            } else if (room[ch_row - 1][ch_seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!\n");
                continue;
            } else {
                if (rows * seats > 60 && ch_row > rows / 2) {
                    cost = 8;
                }
                System.out.println("Ticket price: $" + cost + "\n");
                room[ch_row - 1][ch_seat - 1] = 'B';
                return currentProfit += cost;
            }
        }


    }

    private static void callingMenu() {
        System.out.println(
                "\n1. Show the seats\n" +
                        "2. Buy a ticket\n" +
                        "3. Statistics\n" +
                        "0. Exit\n");
    }

    public static char[][] createRoom(int rows, int seats) {
        char[][] room = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                room[i][j] = 'S';
            }
        }
        return room;
    }

    public static int maxProfit(int rows, int seats) {
        int mprofit = 0;
        if (rows * seats <= 60) {
            return mprofit = rows * seats * 10;
        } else {
            if (rows % 2 == 0) {
                return mprofit = rows * seats * 9;
            } else {
                return mprofit = (rows / 2 * 10 + (rows - rows / 2) * 8) * seats;
            }
        }

    }

    public static void setOfSeats(char[][] seats, int row, int seat) {
        System.out.print("Cinema: \n  ");
        for (int k = 1; k <= seat; k++) {
            System.out.print(k + " ");
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seat; j++) {
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();

        }
    }
}


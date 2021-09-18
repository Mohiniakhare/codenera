package codatation;
import java.util.*;
public class CoditationAssignment 
{
	public static void main(String[] args) 
	{
        System.out.println("*****Welcome to the game of life .....!!!!*****");
        // 1 represent live and -1 represent dead
        Scanner scanner = new Scanner(System.in);
        boolean condition = true; // Creating a condition for game
        int numberOfRound = 1;// initializing the round
        do
        {
        		int ch = 0;
            	System.out.println("*****Select your choice*******");
            	System.out.println("\n1.Next next Round  !");
            	System.out.println("2.print the board !");
            	System.out.print("\nEnter Your choice here : ");
                ch = scanner.nextInt();
               
            	 System.out.print("\nEnter number of rows:"); 
                 int M =scanner.nextInt();
                 System.out.print("\nEnter number of columns:"); 
                 int N =scanner.nextInt();
                 System.out.println("\nInitializing The board...");
                 int[][] board = new int[M][N];// 1 represent live and -1 represent dead
                 int[][] newInitializedBoard = initializeTheBoard(board);
                 
                 printTheBoard(newInitializedBoard);
            	try {
                //
               
            	} catch (Exception e) {
                System.out.println("oops you entered wrong choice please enter right choice..!!!");
                continue;
            }

            switch (ch) 
            		{
                    
            		case 1: 
            		       {
            					int[][] nextBoard = goToNextRoundAndGenerateBoard(newInitializedBoard);// generating a new board for
                                                                                           // next round
            					newInitializedBoard = nextBoard;// passing this new board to previous board
            					printTheBoard(newInitializedBoard);// printing the board
            					break;
            		       }

            		case 2: 
            				{
            					printTheBoard(newInitializedBoard);
            					break;
            				}

            	  default: 
            	  			{
            	  				System.out.println("Oops Wrong Number Enter Please enter correct option !");
            	  				continue;
            	  			}
            
            		}

           
            	System.out.println("Do you wish to play another round ? Yes/No");
            	String str = scanner.next(); // Taking input whether user will play next round or not
            	if (str.equalsIgnoreCase("No"))
            	{
            		condition = false;
            		System.out.println("Thanks for playing Total rounds! : " + numberOfRound);
            	} 
            	else 
            	{
            		numberOfRound++;// incrementing the total number of rounds
            	}
        	} while (condition);

    }

   
    private static int[][] goToNextRoundAndGenerateBoard(int[][] InitializedBoard) 
    {

        // now the board have previous values stored so we will alter the values of cell
        // as per the below rules
        // Rule 1. Any live cell with fewer than two live neighbors dies, as if by
        // loneliness.
        // Rule 2. Any live cell with more than three live neighbors dies, as if by
        // overcrowding.
        // Rule 3. Any live cell with two or three live neighbors lives, unchanged, to
        // the next generation.
        // Rule 4. Any dead cell with exactly three live neighbors comes to life.

        if (InitializedBoard.length == 0 || InitializedBoard[0].length == 0) 
        {
            try 
            {
                throw new UnavailableRowColException("Oops board Doesn't have row/column");
            } catch (UnavailableRowColException e) 
            {
                e.printStackTrace();
            }
        }

        int newRowForNewBoard = InitializedBoard.length;
        int newColForNewBoard = InitializedBoard[0].length;

        int[][] newInitializedBoard = new int[newRowForNewBoard][newColForNewBoard];

        for (int row = 0; row < newRowForNewBoard; row++) 
        {

            for (int col = 0; col < newColForNewBoard; col++) 
            {
                newInitializedBoard[row][col] = getNewStatusOfCell(InitializedBoard[row][col],
                        getTotalLivingCells(row, col, InitializedBoard));
            }
        }

        return newInitializedBoard;
    }

    private static int getNewStatusOfCell(int i, int totalLivingCells) 
    {
    	
        int changed = i;
        // GETTING NEW STATUS AS PER RULES
        if (totalLivingCells < 2) 
        {
            changed = -1;
           
        } else if (totalLivingCells > 3) 
        {
            changed = -1;
            
        } else if (totalLivingCells == 2 || totalLivingCells == 3) 
        {
            changed = 1;
           
        }
        return changed;
    }

    private static int getTotalLivingCells(int checkingRow, int checkingCol, int[][] initializedBoard) 
    {
        int livingCell = 0;
        for (int r = Math.max(0, checkingRow - 1); r <= Math.min(initializedBoard.length - 1, checkingRow + 1); r++) {
            for (int c = Math.max(0, checkingCol - 1); c <= Math.min(initializedBoard[0].length - 1,
                    checkingRow + 1); c++) 
            {
                if (!(r == checkingRow && c == checkingCol)) 
                {
                    if (initializedBoard[r][c] == 1)
                    {
                        livingCell++;
                    }
                }
            }
        }
        return livingCell;
    }

    private static void printTheBoard(int[][] initializedBoard) 
    {

        int livingCell = 0;
        int deadCell = 0;
        int countRowArrangement = 0;
        int countColArrangement = 0;
        System.out.println(
                "\n******PRINTING BOARD********\n");

        for (int i = 0; i < initializedBoard.length; i++) 
        {
            for (int j = 0; j < initializedBoard[0].length; j++) 
            {
                if (initializedBoard[i][j] == 1) 
                {
                    System.out.print("Live ");
                    livingCell++;
                }
                else 
                {
                    System.out.print("Dead ");
                    deadCell++;
                }
            }
            countRowArrangement++;
            if (countRowArrangement % 1 == 0) 
            {
                System.out.println(" ");
                countColArrangement++;
                if (countColArrangement % 1 == 0)
                {
                    System.out.println(" ");
                }
            }
            else 
            {
                System.out.print("\t");
            }
        }

        System.out.println("\n\t\t\t***Living cell = " + livingCell+ "***\n");
        System.out.println("\t\t\t***Dead cell = " + deadCell + "***\n");
        System.out.println("\t\t\t***Total cell = " + (deadCell + livingCell)+ "***\n");

    }

    private static int[][] initializeTheBoard(int[][] board) 
    {

        for (int i = 0; i < board.length; i++) 
        {
            for (int j = 0; j < board[0].length; j++)
            {
                double tempRandom = Math.random();// taking a random number to initialize Board
                if (tempRandom <= 0.5) {
                    board[i][j] = -1;// assigning dead
                } else {
                    board[i][j] = 1;// assigning live
                }
            }
        }
        return board;
    }
}

/**
 * InnerCoditationAssignment
 */
class UnavailableRowColException extends Exception 
{

    public UnavailableRowColException(String string) 
    {
        super(string);
    }

}

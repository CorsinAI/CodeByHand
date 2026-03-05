import java.util.Scanner;
import java.util.InputMismatchException;

//String Builder is still missing
public class Game{
    public static void main(String[] args) {
        
        // Scannter for input
        Scanner myScanner = new Scanner(System.in);

        // all fields are full no winner 
        boolean gameover = false;
        // A player has won the game
        boolean won = false;
        // The playingfield
        char [] table = new char[9];
        

        // position to check which field the player chooses
        int position = -1;

        // The symbols representing the 2 players
        char player1 = '\0'; 
        char player2 = '\0';
        // current player to do the binary switching (Turns)
        char currentplayer = '\0';

        // Chose Symbols for players
        // They cant choose the same or digits
        while(player1 == '\0' || Character.isDigit(player1)){
            System.out.println("Enter a symbol for player 1");
            String input = myScanner.nextLine();

            if(!input.isEmpty()){
                player1 = Character.toUpperCase(input.charAt(0));
            }
        }

        while(player2 == '\0' || Character.isDigit(player2) || player1 == player2){
            System.out.println("Enter a symbol for player 2");
            String input = myScanner.nextLine();

            if(!input.isEmpty()){
                player2 = Character.toUpperCase(input.charAt(0));
            }
        }

        System.out.println(printGrid(table));
        
        // Game Loop 
        while(true){

            //input (and check) for position
            position = - 1;
            while(position < 1 || position > 9 || table[position - 1] != '\0'){
                System.out.println("Enter a number on the Grid");
                try {
                    position = myScanner.nextInt();
                    myScanner.nextLine();
                } catch (InputMismatchException e) {
                    myScanner.nextLine(); 
                }
            }

            // Update the currentplayer for this turn
            if(currentplayer == '\0' || currentplayer == player2){
                currentplayer = player1;
            }else{
                currentplayer = player2;
            }

            // Change the table value of the position
            table[position - 1] = currentplayer;
            
            // Out put the Grid after the change
            System.out.println(printGrid(table));

            //Game over section
         
            //Win message
            won = checkwins(table);
            if(won){
                System.out.println("Player " + currentplayer + " has won.");
                break;
            }
            
            // check if there is a field that is empty. Otherwise gameover 
            gameover = true;
            for(int i = 0; i < table.length; i++){
                if(table[i] == '\0'){
                    gameover = false;
                }
            }

            //Game over message
            if(gameover){
                System.out.println("Game over. Draw!");
                break;
            }
        }
        myScanner.close();
    }

    public static String printGrid(char[] board){

        String Grid = "";

        for(int k = 0; k < 3; k++){
            for(int i = 0; i < 3; i++){
                Grid += " -";
            }
            Grid += "\n";

            for(int j = 0; j < 3; j++){
                //print the board (if there is something)
                if(board[(k * 3) + (j + 1) - 1] != '\0'){
                    Grid += "|" + (board[k * 3 + j]);
                }else{
                    Grid += "|" + ((k * 3) + (j + 1));
                }
            }
            Grid += "|\n";

        }
        Grid += " - - -";

        return Grid;
    }

    // Checks rows then columns and diagonals for the same sign 
    // Not sure if == works here since I changed it to char from String 
    public static boolean checkwins(char[] table){
        
        //rows
        for(int i = 0; i <= 7; i+= 3){
            if(table[i] == table[i+1] && table[i] == table[i+2]){
                if(table[i] != '\0'){
                    return true;
                }
            }
        }

        //columns
        for(int j = 0; j < 3; j++){
            if(table[j] == (table[j+3]) && table[j] == table[j+6]){
                if(table[j] != '\0'){
                    return true;
                }
            }
        }

        //diagonal left to right
        if(table[0] == table[4] && table[0] == table[8]){
            if(table[0] != '\0'){
                return true;
            }
        }
        //diagonal right to left
        if(table[2] == table[4] && table[2] == table[6]){
            if(table[2] != '\0'){
                return true;
            }
        }

        
        //No wins found
        return false;
    }
}
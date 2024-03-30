package TicTacToeGame;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {



//        Player p1 = new Player("Chayan","ch",Piece.X);
//        Player p2 = new Player("Gaurav","ga",Piece.O);
//
//        System.out.println("Game Started");
//        Game ticTac = new Game(p1,p2);
//
//        *********************** win testcase *************************
//        ticTac.play(0,0);
//        ticTac.play(0,1);
//        ticTac.play(0,2);
//        ticTac.play(1,1);
//        ticTac.play(1,0);
//        ticTac.play(2,1);
//
//

//
//        ****************************** draw test case **********************
//
//        ticTac = new Game(p1,p2);
//        ticTac.play(0,1); // r
//        ticTac.play(0,0); // a
//        ticTac.play(0,2); // r
//        ticTac.play(1,0); // a
//        ticTac.play(1,1); // r
//        ticTac.play(2,1); // a
//        ticTac.play(1,2); // r
//        ticTac.play(2,2); // a
//        ticTac.play(2,0); // r


        Scanner sc = new Scanner(System.in);
        String name1 = sc.next();
        String name2 = sc.next();

        char piece1 = sc.next().charAt(0);
        char piece2 = sc.next().charAt(0);

        piece1 = Character.toUpperCase(piece1);
        piece2 = Character.toUpperCase(piece2);

        Piece pp1 = piece1 == 'X' ? Piece.X : Piece.O;
        Piece pp2 = piece2 == 'O' ? Piece.O : Piece.X;

        Player p1 = new Player(name1, "xxx", pp1);
        Player p2 = new Player(name2, "yyy", pp2);

        System.out.println("Game Started by "+name1);

        Game game = new Game(p1, p2);

        int cnt = 0;
        boolean flag = false;

        while(true){

            int row = sc.nextInt();
            int col = sc.nextInt();

           game.play(row, col);
           cnt++;

           if(game.getGameStatus() == GameStatus.WON){
               flag = true;
               break;
           }

           if(cnt == 9){
               break;
           }
       }

        if(flag == false){
            System.out.println("Game is drawn");
        }
    }

}

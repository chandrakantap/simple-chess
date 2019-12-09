package com.ck.codingchallenge.alice.commandlinechess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class UIInterface {
    private Board board;

    @Autowired
    public UIInterface(Board board) {
        this.board = board;
    }

    public void drawBoard(){
        final Iterator<Piece> deadPieceIterator =  board.getDeadPieces().iterator();
        final Iterator<String> recentMoveIterator = board.getRecentMoves().iterator();


        clearConsole();
        System.out.println("         Simple Chess");
        System.out.println("");
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("  ╔════════════════════════╗    ╔═════════╗");
        printRow(0,deadPieceIterator,recentMoveIterator);
        printRow(1,deadPieceIterator,recentMoveIterator);
        printRow(2,deadPieceIterator,recentMoveIterator);
        printRow(3,deadPieceIterator,recentMoveIterator);
        printRow(4,deadPieceIterator,recentMoveIterator);
        printRow(5,deadPieceIterator,recentMoveIterator);
        printRow(6,deadPieceIterator,recentMoveIterator);
        printRow(7,deadPieceIterator,recentMoveIterator);
        System.out.println("  ╚════════════════════════╝    ╚═════════╝");
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("");
        System.out.println("press 'h' for help");
    }

    private void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printRow(int row,Iterator<Piece> deadPieceIterator,Iterator<String> recentMoveIterator){
        StringBuilder rowStringBuilder = new StringBuilder().append(8-row).append(" ║ ");
        for(int col=0;col<8;col++){
            Piece cellPiece = board.getCellPiece(row,col);
            if(cellPiece==null){
                rowStringBuilder.append(col==7?"… ":"…  ");
            }else {
                rowStringBuilder.append(cellPiece.getAsciiCode());
                rowStringBuilder.append(col==7?" ":"  ");
            }
        }
        rowStringBuilder.append("║ ").append(8-row).append("  ║ ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append("║ ");
        if(recentMoveIterator.hasNext()){
            rowStringBuilder.append(recentMoveIterator.next());
        }
        System.out.println(rowStringBuilder.toString());
    }
}

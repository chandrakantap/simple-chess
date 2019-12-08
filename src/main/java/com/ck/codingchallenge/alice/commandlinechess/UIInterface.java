package com.ck.codingchallenge.alice.commandlinechess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class UIInterface {
    private Board board;
    private BoardCell[][] orderedCells = {
            {BoardCell.A8,BoardCell.B8,BoardCell.C8,BoardCell.D8,BoardCell.E8,BoardCell.F8,BoardCell.G8,BoardCell.H8},
            {BoardCell.A7,BoardCell.B7,BoardCell.C7,BoardCell.D7,BoardCell.E7,BoardCell.F7,BoardCell.G7,BoardCell.H7},
            {BoardCell.A6,BoardCell.B6,BoardCell.C6,BoardCell.D6,BoardCell.E6,BoardCell.F6,BoardCell.G6,BoardCell.H6},
            {BoardCell.A5,BoardCell.B5,BoardCell.C5,BoardCell.D5,BoardCell.E5,BoardCell.F5,BoardCell.G5,BoardCell.H5},
            {BoardCell.A4,BoardCell.B4,BoardCell.C4,BoardCell.D4,BoardCell.E4,BoardCell.F4,BoardCell.G4,BoardCell.H4},
            {BoardCell.A3,BoardCell.B3,BoardCell.C3,BoardCell.D3,BoardCell.E3,BoardCell.F3,BoardCell.G3,BoardCell.H3},
            {BoardCell.A2,BoardCell.B2,BoardCell.C2,BoardCell.D2,BoardCell.E2,BoardCell.F2,BoardCell.G2,BoardCell.H2},
            {BoardCell.A1,BoardCell.B1,BoardCell.C1,BoardCell.D1,BoardCell.E1,BoardCell.F1,BoardCell.G1,BoardCell.H1}
    };


    @Autowired
    public UIInterface(Board board) {
        this.board = board;
    }

    public void drawBoard(){
        final Iterator<Piece> deadPieceIterator =  board.getDeadPieces().iterator();


        clearConsole();
        System.out.println("         Simple Chess");
        System.out.println("");
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("  ╔════════════════════════╗    ╔═════════╗");
        printRow(8,deadPieceIterator);
        printRow(7,deadPieceIterator);
        printRow(6,deadPieceIterator);
        printRow(5,deadPieceIterator);
        printRow(4,deadPieceIterator);
        printRow(3,deadPieceIterator);
        printRow(2,deadPieceIterator);
        printRow(1,deadPieceIterator);
        System.out.println("  ╚════════════════════════╝    ╚═════════╝");
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("");
        System.out.println("press 'h' for help");
    }

    private void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printRow(int rowNum,Iterator<Piece> deadPieceIterator){
        StringBuilder rowStringBuilder = new StringBuilder().append(rowNum).append(" ║ ");
        BoardCell[] row = orderedCells[8-rowNum];
        for(int col=0;col<8;col++){
            Piece cellPiece = board.getCellPiece(row[col]);
            if(cellPiece==null){
                rowStringBuilder.append(col==7?"… ":"…  ");
            }else {
                rowStringBuilder.append(cellPiece.getAsciiCode());
                rowStringBuilder.append(col==7?" ":"  ");
            }
        }
        rowStringBuilder.append("║ ").append(rowNum).append("  ║ ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append(deadPieceIterator.hasNext()?deadPieceIterator.next().getAsciiCode():" ").append(" ");
        rowStringBuilder.append("║");
        System.out.println(rowStringBuilder.toString());
    }
}

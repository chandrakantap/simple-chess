package com.ck.codingchallenge.alice.commandlinechess;


import com.ck.codingchallenge.alice.commandlinechess.movevalidator.PieceMoveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Board{
    private Map<BoardCell,Piece> piecePositions;
    private List<Piece> deadPieces;
    private Player currentPlayer;

    private final PieceMoveValidator moveValidator;

    @Autowired
    public Board(PieceMoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }


    private void initBoard(){
        piecePositions=new HashMap<>();

        piecePositions.put(BoardCell.A1,Piece.WROOK);
        piecePositions.put(BoardCell.B1,Piece.WKNIGHT);
        piecePositions.put(BoardCell.C1,Piece.WBISHOP);
        piecePositions.put(BoardCell.D1,Piece.WQUEEN);
        piecePositions.put(BoardCell.E1,Piece.WKING);
        piecePositions.put(BoardCell.F1,Piece.WBISHOP);
        piecePositions.put(BoardCell.G1,Piece.WKNIGHT);
        piecePositions.put(BoardCell.H1,Piece.WROOK);

        piecePositions.put(BoardCell.A2,Piece.WPAWN);
        piecePositions.put(BoardCell.B2,Piece.WPAWN);
        piecePositions.put(BoardCell.C2,Piece.WPAWN);
        piecePositions.put(BoardCell.D2,Piece.WPAWN);
        piecePositions.put(BoardCell.E2,Piece.WPAWN);
        piecePositions.put(BoardCell.F2,Piece.WPAWN);
        piecePositions.put(BoardCell.G2,Piece.WPAWN);
        piecePositions.put(BoardCell.H2,Piece.WPAWN);

        piecePositions.put(BoardCell.A8,Piece.BROOK);
        piecePositions.put(BoardCell.B8,Piece.BKNIGHT);
        piecePositions.put(BoardCell.C8,Piece.BBISHOP);
        piecePositions.put(BoardCell.D8,Piece.BQUEEN);
        piecePositions.put(BoardCell.E8,Piece.BKING);
        piecePositions.put(BoardCell.F8,Piece.BBISHOP);
        piecePositions.put(BoardCell.G8,Piece.BKNIGHT);
        piecePositions.put(BoardCell.H8,Piece.BROOK);

        piecePositions.put(BoardCell.A7,Piece.BPAWN);
        piecePositions.put(BoardCell.B7,Piece.BPAWN);
        piecePositions.put(BoardCell.C7,Piece.BPAWN);
        piecePositions.put(BoardCell.D7,Piece.BPAWN);
        piecePositions.put(BoardCell.E7,Piece.BPAWN);
        piecePositions.put(BoardCell.F7,Piece.BPAWN);
        piecePositions.put(BoardCell.G7,Piece.BPAWN);
        piecePositions.put(BoardCell.H7,Piece.BPAWN);

        deadPieces=new ArrayList<>();

    }

    public void startNewGame(){
        initBoard();
        currentPlayer = Player.WHITE;
    }

    public MoveResult move(BoardCell fromCell, BoardCell toCell){
        final MoveResult moveResult = moveValidator.validateMove(fromCell,toCell,this);

        if(moveResult.isValid()) {
            if (piecePositions.get(toCell) != null) {
                deadPieces.add(piecePositions.get(toCell));
            }
            piecePositions.put(toCell, piecePositions.get(fromCell));
            piecePositions.remove(fromCell);

            switchPlayer();
        }
        return moveResult;
    }

    private void switchPlayer() {
        if(currentPlayer.equals(Player.BLACK)){
            currentPlayer = Player.WHITE;
        }else{
            currentPlayer = Player.BLACK;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Piece getCellPiece(BoardCell cell){
        return piecePositions.get(cell);
    }

    public List<Piece> getDeadPieces() {
        return deadPieces;
    }
}

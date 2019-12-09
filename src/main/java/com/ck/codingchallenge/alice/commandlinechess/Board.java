package com.ck.codingchallenge.alice.commandlinechess;


import com.ck.codingchallenge.alice.commandlinechess.movevalidator.PieceMoveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class Board{
    private BoardCell[][] boardCells = {
            {BoardCell.A8,BoardCell.B8,BoardCell.C8,BoardCell.D8,BoardCell.E8,BoardCell.F8,BoardCell.G8,BoardCell.H8},
            {BoardCell.A7,BoardCell.B7,BoardCell.C7,BoardCell.D7,BoardCell.E7,BoardCell.F7,BoardCell.G7,BoardCell.H7},
            {BoardCell.A6,BoardCell.B6,BoardCell.C6,BoardCell.D6,BoardCell.E6,BoardCell.F6,BoardCell.G6,BoardCell.H6},
            {BoardCell.A5,BoardCell.B5,BoardCell.C5,BoardCell.D5,BoardCell.E5,BoardCell.F5,BoardCell.G5,BoardCell.H5},
            {BoardCell.A4,BoardCell.B4,BoardCell.C4,BoardCell.D4,BoardCell.E4,BoardCell.F4,BoardCell.G4,BoardCell.H4},
            {BoardCell.A3,BoardCell.B3,BoardCell.C3,BoardCell.D3,BoardCell.E3,BoardCell.F3,BoardCell.G3,BoardCell.H3},
            {BoardCell.A2,BoardCell.B2,BoardCell.C2,BoardCell.D2,BoardCell.E2,BoardCell.F2,BoardCell.G2,BoardCell.H2},
            {BoardCell.A1,BoardCell.B1,BoardCell.C1,BoardCell.D1,BoardCell.E1,BoardCell.F1,BoardCell.G1,BoardCell.H1}
    };
    private Map<BoardCell,Piece> piecePositions;
    private List<Piece> deadPieces;
    private Queue<String> recentMoves;
    private Player currentPlayer;
    private int moveNo;

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
        recentMoves=new LinkedList<>();
        moveNo=1;

    }

    public void startNewGame(){
        initBoard();
        currentPlayer = Player.WHITE;
    }

    /**
     * Validates the move, updates piece positions and switch Current Player
     * @param fromCell
     * @param toCell
     * @return
     */
    public MoveResult move(BoardCell fromCell, BoardCell toCell){
        final MoveResult moveResult = moveValidator.validateMove(fromCell,toCell,this);

        if(moveResult.isValid()) {
            if (piecePositions.get(toCell) != null) {
                deadPieces.add(piecePositions.get(toCell));
            }
            piecePositions.put(toCell, piecePositions.get(fromCell));
            piecePositions.remove(fromCell);

            switchPlayer();
            if(recentMoves.size() > 7){
                recentMoves.poll();
            }
            recentMoves.add(moveNo+". "+fromCell.name()+" - "+toCell.name());
            moveNo++;
        }
        return moveResult;
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

    public Queue<String> getRecentMoves() {
        return recentMoves;
    }

    public Piece getCellPiece(int row, int col){
        if(row > 7 || col > 7){
            return null;
        }
        return getCellPiece(boardCells[row][col]);
    }

    private void switchPlayer() {
        if(currentPlayer.equals(Player.BLACK)){
            currentPlayer = Player.WHITE;
        }else{
            currentPlayer = Player.BLACK;
        }
    }

    public Piece getNearestTopPiece(BoardCell fromCell, BoardCell toCell){
        final int col = fromCell.getColumn();
        final int topRow = toCell==null?-1:toCell.getRow();

        for(int row=fromCell.getRow()-1;row>topRow;row--){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

    public Piece getNearestBottomPiece(BoardCell fromCell, BoardCell toCell){
        final int col = fromCell.getColumn();
        final int bottomRow = toCell==null?8:toCell.getRow();

        for(int row=fromCell.getRow()+1;row<bottomRow;row++){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

    public Piece getNearestLeftPiece(BoardCell fromCell, BoardCell toCell){
        final int row = fromCell.getRow();
        final int leftColumn = toCell==null?-1:toCell.getColumn();

        for(int col=fromCell.getColumn()-1;col>leftColumn;col--){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

    public Piece getNearestRightPiece(BoardCell fromCell, BoardCell toCell){
        final int row = fromCell.getRow();
        final int rightColumn = toCell==null?8:toCell.getColumn();

        for(int col=fromCell.getColumn()+1;col>rightColumn;col++){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

    public Piece getNearestRightTopPiece(BoardCell fromCell, BoardCell toCell){
        int rightColumn = toCell==null?8:toCell.getColumn();
        int topRow = toCell==null?-1:toCell.getRow();

        for(int col=fromCell.getColumn()+1,row=fromCell.getRow()-1;col<rightColumn && row>topRow;col++,row--){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

    public Piece getNearestLeftTopPiece(BoardCell fromCell, BoardCell toCell){
        int leftColumn = toCell==null?-1:toCell.getColumn();
        int topRow = toCell==null?-1:toCell.getRow();

        for(int col=fromCell.getColumn()-1,row=fromCell.getRow()-1;col>leftColumn && row>topRow;col--,row--){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

    public Piece getNearestLeftBottomPiece(BoardCell fromCell, BoardCell toCell){
        int leftColumn = toCell==null?-1:toCell.getColumn();
        int bottomRow = toCell==null?8:toCell.getRow();

        for(int col=fromCell.getColumn()-1,row=fromCell.getRow()+1;col>leftColumn && row<bottomRow;col--,row++){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

    public Piece getNearestRightBottomPiece(BoardCell fromCell, BoardCell toCell){
        int rightColumn = toCell==null?8:toCell.getColumn();
        int bottomRow = toCell==null?8:toCell.getRow();

        for(int col=fromCell.getColumn()+1,row=fromCell.getRow()+1;col<rightColumn && row<bottomRow;col++,row++){
            final Piece piece = getCellPiece(row,col);
            if(piece!=null){
                return piece;
            }
        }
        return null;
    }

}

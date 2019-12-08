package com.ck.codingchallenge.alice.commandlinechess.movevalidator;

import com.ck.codingchallenge.alice.commandlinechess.Board;
import com.ck.codingchallenge.alice.commandlinechess.BoardCell;
import com.ck.codingchallenge.alice.commandlinechess.MoveResult;
import com.ck.codingchallenge.alice.commandlinechess.Piece;
import org.springframework.stereotype.Component;

@Component
class PawnMoveValidator {

    MoveResult validateBlackPawnMove(BoardCell fromCell, BoardCell toCell, Board board) {
        final Piece pieceOnToCell = board.getCellPiece(toCell);

        if(
                fromCell.getRow()==7 && fromCell.getRow()- toCell.getRow() <=2 && pieceOnToCell==null ||
                fromCell.getRow()- toCell.getRow() == 1 && fromCell.getColumn()==toCell.getColumn() && pieceOnToCell==null ||
                fromCell.getRow()- toCell.getRow() == 1 && Math.abs(fromCell.getColumn()-toCell.getColumn())==1 && pieceOnToCell!=null && !pieceOnToCell.getPlayer().equals(board.getCurrentPlayer())
        ){
            return MoveResult.validMove();
        }

        return MoveResult.invalidMove();
    }

    public MoveResult validateWhitePawnMove(BoardCell fromCell, BoardCell toCell, Board board) {
        final Piece pieceOnToCell = board.getCellPiece(toCell);

        if(fromCell.getRow()==2 && toCell.getRow()- fromCell.getRow() <=2 && pieceOnToCell==null ||
                        toCell.getRow()- fromCell.getRow() == 1 && fromCell.getColumn()==toCell.getColumn() && pieceOnToCell==null ||
                        toCell.getRow()- fromCell.getRow() == 1 && Math.abs(fromCell.getColumn()-toCell.getColumn())==1 && pieceOnToCell!=null && !pieceOnToCell.getPlayer().equals(board.getCurrentPlayer())
        ){
            return MoveResult.validMove();
        }

        return MoveResult.invalidMove();
    }
}

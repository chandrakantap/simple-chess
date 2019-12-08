package com.ck.codingchallenge.alice.commandlinechess.movevalidator;

import com.ck.codingchallenge.alice.commandlinechess.Board;
import com.ck.codingchallenge.alice.commandlinechess.BoardCell;
import com.ck.codingchallenge.alice.commandlinechess.MoveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PieceMoveValidator {

    private final PawnMoveValidator pawnMoveValidator;

    @Autowired
    public PieceMoveValidator(PawnMoveValidator pawnMoveValidator) {
        this.pawnMoveValidator = pawnMoveValidator;
    }

    public MoveResult validateMove(final BoardCell fromCell, final BoardCell toCell, final Board board){
        if(board.getCellPiece(fromCell)==null){
            return MoveResult.invalidMove("No piece in "+fromCell.name());
        }
        if(!board.getCellPiece(fromCell).getPlayer().equals(board.getCurrentPlayer())){
            return MoveResult.invalidMove(board.getCurrentPlayer().name()+" player's turn.");
        }
        switch (board.getCellPiece(fromCell)){
            case BPAWN:
                return pawnMoveValidator.validateBlackPawnMove(fromCell,toCell,board);
            case WPAWN:
                return pawnMoveValidator.validateWhitePawnMove(fromCell,toCell,board);
        }
        return MoveResult.validMove();
    }
}

package com.ck.codingchallenge.alice.commandlinechess.movevalidator;

import com.ck.codingchallenge.alice.commandlinechess.Board;
import com.ck.codingchallenge.alice.commandlinechess.BoardCell;
import com.ck.codingchallenge.alice.commandlinechess.MoveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PieceMoveValidator {

    private final PawnMoveValidator pawnMoveValidator;
    private final RookMoveValidator rookMoveValidator;
    private final BishopMoveValidator bishopMoveValidator;
    private final QueenMoveValidator queenMoveValidator;

    @Autowired
    public PieceMoveValidator(PawnMoveValidator pawnMoveValidator, RookMoveValidator rookMoveValidator,
                              BishopMoveValidator bishopMoveValidator, QueenMoveValidator queenMoveValidator) {
        this.pawnMoveValidator = pawnMoveValidator;
        this.rookMoveValidator = rookMoveValidator;
        this.bishopMoveValidator = bishopMoveValidator;
        this.queenMoveValidator = queenMoveValidator;
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
            case WROOK:
            case BROOK:
                return rookMoveValidator.validate(fromCell,toCell,board);
            case WBISHOP:
            case BBISHOP:
                return bishopMoveValidator.validate(fromCell,toCell,board);
            case WQUEEN:
            case BQUEEN:
                return queenMoveValidator.validate(fromCell,toCell,board);
            case WKNIGHT:
            case BKNIGHT:
                return validateKnightMove(fromCell,toCell,board);

        }
        return MoveResult.validMove();
    }

    private MoveResult validateKnightMove(BoardCell fromCell, BoardCell toCell, Board board){
        if(toCell.isOnGallopPosition(fromCell) && (board.getCellPiece(toCell)==null ||
                !board.getCellPiece(toCell).getPlayer().equals(board.getCurrentPlayer()))){
            return MoveResult.validMove();
        }
        return MoveResult.invalidMove();
    }
}

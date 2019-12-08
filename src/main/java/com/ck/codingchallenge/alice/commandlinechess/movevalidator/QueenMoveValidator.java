package com.ck.codingchallenge.alice.commandlinechess.movevalidator;

import com.ck.codingchallenge.alice.commandlinechess.Board;
import com.ck.codingchallenge.alice.commandlinechess.BoardCell;
import com.ck.codingchallenge.alice.commandlinechess.MoveResult;
import org.springframework.stereotype.Component;

@Component
public class QueenMoveValidator extends CommonMoveValidator {

    MoveResult validate(BoardCell fromCell, BoardCell toCell, Board board) {
        if(isValidLeftDiagonalMove(fromCell,toCell,board) ||
                isValidRightDiagonalMove(fromCell,toCell,board) ||
                isValidHorizontalMove(fromCell,toCell,board) ||
                isValidVerticalMove(fromCell,toCell,board)
        ){
            return MoveResult.validMove();
        }else{
            return MoveResult.invalidMove();
        }
    }
}

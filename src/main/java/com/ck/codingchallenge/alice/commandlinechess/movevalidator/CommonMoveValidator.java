package com.ck.codingchallenge.alice.commandlinechess.movevalidator;

import com.ck.codingchallenge.alice.commandlinechess.Board;
import com.ck.codingchallenge.alice.commandlinechess.BoardCell;

public class CommonMoveValidator {

    boolean isValidVerticalMove(BoardCell fromCell, BoardCell toCell, Board board){
        return (toCell.isOnTopOf(fromCell) && board.getNearestTopPiece(fromCell,toCell)==null
            || toCell.isAtBottomOf(fromCell) && board.getNearestBottomPiece(fromCell,toCell) ==null)
            && (board.getCellPiece(toCell)==null || !board.getCellPiece(toCell).getPlayer().equals(board.getCurrentPlayer()));
    }

    boolean isValidHorizontalMove(BoardCell fromCell, BoardCell toCell, Board board){
        return (toCell.isOnLeftOf(fromCell) && board.getNearestLeftPiece(fromCell,toCell)==null
                || toCell.isOnRightOf(fromCell) && board.getNearestRightPiece(fromCell,toCell) ==null)
                && (board.getCellPiece(toCell)==null || !board.getCellPiece(toCell).getPlayer().equals(board.getCurrentPlayer()));
    }

    boolean isValidLeftDiagonalMove(BoardCell fromCell, BoardCell toCell, Board board){
        return (toCell.isOnLeftTop(fromCell) && board.getNearestLeftTopPiece(fromCell,toCell)==null
                || toCell.isOnRightBottom(fromCell) && board.getNearestRightBottomPiece(fromCell,toCell) ==null)
                && (board.getCellPiece(toCell)==null || !board.getCellPiece(toCell).getPlayer().equals(board.getCurrentPlayer()));
    }

    boolean isValidRightDiagonalMove(BoardCell fromCell, BoardCell toCell, Board board){
        return (toCell.isOnLeftBottom(fromCell) && board.getNearestLeftBottomPiece(fromCell,toCell)==null
                || toCell.isOnRightTop(fromCell) && board.getNearestRightTopPiece(fromCell,toCell) ==null)
                && (board.getCellPiece(toCell)==null || !board.getCellPiece(toCell).getPlayer().equals(board.getCurrentPlayer()));
    }
}

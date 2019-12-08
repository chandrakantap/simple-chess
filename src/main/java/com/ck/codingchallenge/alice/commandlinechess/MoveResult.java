package com.ck.codingchallenge.alice.commandlinechess;

public class MoveResult {
    private MoveResult.STATUS status;
    private String errorMessage;

    public STATUS getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public boolean isValid(){
        return !status.equals(STATUS.INVALID);
    }

    private MoveResult(MoveResult.STATUS status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    private MoveResult(MoveResult.STATUS status) {
        this.status = status;
    }

    public static MoveResult  validMove(){
        return new MoveResult(STATUS.VALID);
    }

    public static MoveResult  invalidMove(){
        return new MoveResult(STATUS.INVALID,"Invalid Move");
    }

    public static MoveResult  invalidMove(String errorMessage){
        return new MoveResult(STATUS.INVALID,errorMessage);
    }

    public enum STATUS{
        VALID,INVALID
    }
}

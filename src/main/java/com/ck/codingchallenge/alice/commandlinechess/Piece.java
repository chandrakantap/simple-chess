package com.ck.codingchallenge.alice.commandlinechess;

public enum Piece {
    BKING("♔",Player.BLACK),
    BQUEEN("♕",Player.BLACK),
    BROOK("♖",Player.BLACK),
    BBISHOP("♗",Player.BLACK),
    BKNIGHT("♘",Player.BLACK),
    BPAWN("♙",Player.BLACK),

    WKING("♚",Player.WHITE),
    WQUEEN("♛",Player.WHITE),
    WROOK("♜",Player.WHITE),
    WBISHOP("♝",Player.WHITE),
    WKNIGHT("♞",Player.WHITE),
    WPAWN("♟",Player.WHITE);

    private String asciiCode;
    private Player player;

    Piece(String asciiCode,Player player) {
        this.asciiCode = asciiCode;this.player=player;
    }

    public String getAsciiCode() {
        return asciiCode;
    }

    public Player getPlayer() {
        return player;
    }
}

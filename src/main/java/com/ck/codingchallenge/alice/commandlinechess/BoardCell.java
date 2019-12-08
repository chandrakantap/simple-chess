package com.ck.codingchallenge.alice.commandlinechess;

public enum BoardCell {
    A1(1,1),	B1(1,2),	C1(1,3),	D1(1,4),	E1(1,5),	F1(1,6),	G1(1,7),	H1(1,8),
    A2(2,1),	B2(2,2),	C2(2,3),	D2(2,4),	E2(2,5),	F2(2,6),	G2(2,7),	H2(2,8),
    A3(3,1),	B3(3,2),	C3(3,3),	D3(3,4),	E3(3,5),	F3(3,6),	G3(3,7),	H3(3,8),
    A4(4,1),	B4(4,2),	C4(4,3),	D4(4,4),	E4(4,5),	F4(4,6),	G4(4,7),	H4(4,8),
    A5(5,1),	B5(5,2),	C5(5,3),	D5(5,4),	E5(5,5),	F5(5,6),	G5(5,7),	H5(5,8),
    A6(6,1),	B6(6,2),	C6(6,3),	D6(6,4),	E6(6,5),	F6(6,6),	G6(6,7),	H6(6,8),
    A7(7,1),	B7(7,2),	C7(7,3),	D7(7,4),	E7(7,5),	F7(7,6),	G7(7,7),	H7(7,8),
    A8(8,1),	B8(8,2),	C8(8,3),	D8(8,4),	E8(8,5),	F8(8,6),	G8(8,7),	H8(8,8);

    private int row;
    private int column;

    BoardCell(int row,int column){
        this.row=row;
        this.column=column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

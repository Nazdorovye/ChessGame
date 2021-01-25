package models;

import service.Colour;
import service.Move;

public class Bishop extends Piece {
    public Bishop(byte visualIdx, Colour colour, byte col, byte row) {
        super(visualIdx, colour, col, row);
    }

    @Override
    public void calcAvalableCells(Board brd) {
        moves.clear();
        if (!status.free())
            return;
        boolean pieceFound = false;
        Piece potPinnedPiece = null;

        // find out all available cells right below
        for (int i = 1; i <= 7; i++) {
            if((col+i) <= 7 && (row+i) <= 7){
            if (pieceFound == true) {
                if (brd.getCells()[col+i][row+i].getPiece() == null) {
                    continue;
                } 
                else {
                    if (brd.getCells()[col+i][row+i].getPiece().getClass() == King.class
                            && !brd.getCells()[col+i][row+i].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } 
            else {
                if (brd.getCells()[col+i][row+i].getPiece() == null && (col+i) <= 7 && (row+i) <= 7) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, (byte)(col+i), (byte)(row+i)));
                }

                else {
                    if (brd.getCells()[col+i][row+i].getPiece() != null
                            && !brd.getCells()[col+i][row+i].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, (byte)(col+i), (byte)(row+i)));
                        potPinnedPiece = brd.getCells()[col+i][row+i].getPiece();
                    }
                    pieceFound = true;
                }
            }
           }
    }

        // find out all available cells above Rook starting position

        pieceFound = false;
        for (int k = 1; k <= col; k++) {
            if((col-k) >= 0 && (row+k) <= 7){
            if (pieceFound == true) {
                if (brd.getCells()[col-k][row+k].getPiece() == null) {
                    continue;
                } else {
                    if (brd.getCells()[col-k][row+k].getPiece().getClass() == King.class
                            && !brd.getCells()[col-k][row+k].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } else {
                if (brd.getCells()[col-k][row+k].getPiece() == null && (col-k) <= 7 && (row+k) <= 7) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, (byte)(col-k), (byte)(row+k)));
                }

                else {
                    if (brd.getCells()[col-k][row+k].getPiece() != null
                            && !brd.getCells()[col-k][row+k].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, (byte)(col-k), (byte)(row+k)));
                        potPinnedPiece = brd.getCells()[col-k][row+k].getPiece();
                    }
                    pieceFound = true;
                }
            }
        }
    }

        // find out available cells on the left side from Rook starting position
        pieceFound = false;
        for (int l = 1; l <= row; l++) {
            if((col-l) >= 0 && (row-l) >= 0){
            if (pieceFound == true) {
                if (brd.getCells()[col-l][row-l].getPiece() == null) {
                    continue;
                } else {
                    if (brd.getCells()[col-l][row-l].getPiece().getClass() == King.class
                            && !brd.getCells()[col-l][row-l].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } else {
                if (brd.getCells()[col-l][row-l].getPiece() == null && (col-l) <= 7 && (row-l) <= 7) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, (byte)(col-l),(byte)(row-l)));
                }

                else {
                    if (brd.getCells()[col-l][row-l].getPiece() != null
                            && !brd.getCells()[col-l][row-l].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, (byte)(col-l),(byte)(row-l)));
                        potPinnedPiece = brd.getCells()[col-l][row-l].getPiece();
                    }
                    pieceFound = true;
                }
            }
        }
    }

        // find out available cells on the right side from Rook starting position
        pieceFound = false;
        for (int m = 1; m <= 7; m++) {
            if((col+m) <= 7 && (row-m) > 0){
            if (pieceFound == true) {
                if (brd.getCells()[col+m][row-m].getPiece() == null) {
                    continue;
                } else {
                    if (brd.getCells()[col+m][row-m].getPiece().getClass() == King.class
                            && !brd.getCells()[col+m][row-m].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } else {
                if (brd.getCells()[col+m][row-m].getPiece() == null) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, (byte)(col+m), (byte)(row-m)));
                }

                else {
                    if (brd.getCells()[col+m][row-m].getPiece() != null
                            && !brd.getCells()[col+m][row-m].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, (byte)(col+m), (byte)(row-m)));
                        potPinnedPiece = brd.getCells()[col+m][row-m].getPiece();
                    }
                    pieceFound = true;
                }
            }
        }

    }
    }
}
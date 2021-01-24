package models;

import service.Colour;
import service.Move;

public class Rook extends Piece {
    public Rook(byte visualIdx, Colour colour, byte col, byte row) {
        super(visualIdx, colour, col, row);
    }

    @Override
    public void calcAvalableCells(Board brd) {
        moves.clear();
        if (!status.free())
            return;
        boolean pieceFound = false;
        Piece potPinnedPiece = null;

        // find out all available cells below Rook starting position
        for (int i = row + 1; i <= 7; i++) {
            if (pieceFound == true) {
                if (brd.getCells()[col][i].getPiece() == null) {
                    continue;
                } else {
                    if (brd.getCells()[col][i].getPiece().getClass() == King.class
                            && !brd.getCells()[col][i].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } else {
                if (brd.getCells()[col][i].getPiece() == null) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, col, (byte) (i)));
                }

                else {
                    if (brd.getCells()[col][i].getPiece() != null
                            && !brd.getCells()[col][i].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, col, (byte) (i)));
                        potPinnedPiece = brd.getCells()[col][i].getPiece();
                    }
                    pieceFound = true;
                }
            }
        }

        // find out all available cells above Rook starting position

        pieceFound = false;
        for (int k = 1; k <= row; k++) {
            if (pieceFound == true) {
                if (brd.getCells()[col][(byte)(row-k)].getPiece() == null) {
                    continue;
                } else {
                    if (brd.getCells()[col][(byte)(row-k)].getPiece().getClass() == King.class
                            && !brd.getCells()[col][(byte)(row-k)].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } else {
                if (brd.getCells()[col][(byte)(row-k)].getPiece() == null) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, col, (byte)(row-k)));
                }

                else {
                    if (brd.getCells()[col][(byte)(row-k)].getPiece() != null
                            && !brd.getCells()[col][(byte)(row-k)].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, col, (byte) (row - k)));
                        potPinnedPiece = brd.getCells()[col][(byte)(row-k)].getPiece();
                    }
                    pieceFound = true;
                }
            }
        }

        // find out available cells on the left side from Rook starting position
        pieceFound = false;
        for (int l = 1; l <= col; l++) {
            if (pieceFound == true) {
                if (brd.getCells()[col - l][row].getPiece() == null) {
                    continue;
                } else {
                    if (brd.getCells()[col - l][row].getPiece().getClass() == King.class
                            && !brd.getCells()[col - l][row].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } else {
                if (brd.getCells()[col - l][row].getPiece() == null) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, (byte) (col - l), row));
                }

                else {
                    if (brd.getCells()[col - l][row].getPiece() != null
                            && !brd.getCells()[col - l][row].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, (byte) (col - l), row));
                        potPinnedPiece = brd.getCells()[col - l][row].getPiece();
                    }
                    pieceFound = true;
                }
            }
        }

        // find out available cells on the right side from Rook starting position
        pieceFound = false;
        for (int m = col + 1; m <= 7; m++) {
            if (pieceFound == true) {
                if (brd.getCells()[m][row].getPiece() == null) {
                    continue;
                } else {
                    if (brd.getCells()[m][row].getPiece().getClass() == King.class
                            && !brd.getCells()[m][row].getPiece().colour.equals(colour)) {
                        potPinnedPiece.setStatus(Status.PINNED);
                    }
                }
            } else {
                if (brd.getCells()[m][row].getPiece() == null) {
                    moves.add(new Move(Move.Type.TRANSLATE, col, row, (byte) (m), row));
                }

                else {
                    if (brd.getCells()[m][row].getPiece() != null
                            && !brd.getCells()[m][row].getPiece().colour.equals(colour)) {
                        moves.add(new Move(Move.Type.TAKE, col, row, (byte) (m), row));
                        potPinnedPiece = brd.getCells()[m][row].getPiece();
                    }
                    pieceFound = true;
                }
            }
        }

    }

}

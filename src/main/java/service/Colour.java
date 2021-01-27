package service;

public enum Colour {
  WHITES, BLACKS;

  public byte direction() { return this.equals(Colour.WHITES) ? (byte)-1 : 1; }
  public boolean white() { return this.equals(Colour.WHITES) ? true : false; }
  public boolean black() { return this.equals(Colour.BLACKS) ? true : false; }
  public String toStringRev() { return this.equals(Colour.BLACKS) ? WHITES.toString() : BLACKS.toString(); }
}

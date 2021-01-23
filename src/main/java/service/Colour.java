package service;

public enum Colour {
  WHITES, BLACKS;

  public int direction() { return this.equals(Colour.WHITES) ? -1 : 1; }
  public boolean white() { return this.equals(Colour.WHITES) ? true : false; }
  public boolean black() { return this.equals(Colour.BLACKS) ? true : false; }
}

import java.io.*;

public class Village implements Serializable {
  transient Initiation root;
  int food;
  int wood;
  int stone;
  int crop;
  int iron;
  boolean verbose;
  Village(Initiation root) {
    this.root=root;
    food=20;
    wood=0;
    stone=0;
    crop=0;
    iron=0;
    verbose=false; //I would always forget to change this and it would annoy me
  }
  public void load(Initiation root) {
    this.root=root;
  }
}
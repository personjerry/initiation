import java.io.*;

public class Village implements Serializable {
  transient Initiation root;
  int food;
  int wood;
  int stone;
  Village(Initiation root) {
    this.root=root;
    food=0;
    wood=0;
    stone=0;
  }
  public void load(Initiation root) {
    this.root=root;
  }
}
import java.util.*;

public class Display {
  Scanner s;
  public Display() { 
    s=new Scanner(System.in);
  }
  public void err(String err) {
    System.out.println("Error: "+err);
  }
  public void out(String line) {
    System.out.println(line);
  }
  public void out(int line) {
    System.out.println(line);
  }
  public String nextLine() {
    return s.nextLine();
  }
}

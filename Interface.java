import java.util.*;

public class Interface {
  Scanner s;
  public Interface() { 
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
    System.out.print("-->");
    return s.nextLine();
  }
}
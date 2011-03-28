import java.util.*;

public class Interface {
  //MARQUEE SETTING
  boolean MARQUEE=false;
  
  Scanner s;
  public Interface() { 
    s=new Scanner(System.in);
  }
  public void err(String err) {
    System.out.println("Error: "+err);
  }
  public void out(String line) {
    if(!MARQUEE) {
      System.out.println(line);
    } else {
      String[] s=line.split("");
      for(int i=0;i<s.length;i++) {
        System.out.print(s[i]);
        wait(25);
      }
      System.out.println();
    }
  }
  public void out(int line) {
    out(line+"");
  }
  public String nextLine() {
    System.out.print("-->");
    return s.nextLine();
  }
  public String pressEnter() {
    return s.nextLine();
  }
  
  private void wait(int milli) {
    long start=System.currentTimeMillis();
    long end=start+milli;
    while(System.currentTimeMillis()<end) {}
    return;
  }
}
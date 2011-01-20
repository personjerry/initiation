import java.util.*;
import java.io.*;

public class Initiation {
  Display d;
  MT rand;
  SaveFile s;
  
   //File Handling
  FileOutputStream fos;
  ObjectOutputStream out;
  FileInputStream fis;
  ObjectInputStream in;
  
  public static void main(String[] args) {
    new Initiation();
  }
  Initiation() {
    d=new Display();
    rand=new MT();
    String user_input=d.nextLine();
    if(user_input.equals("new")) {
      create();
      save("save.dat");
      d.out("File successfully created.");
    } else {
      if(load("save.dat")) {
        d.out("Load successful.");
      } else {
        d.out("Make a save file first.");
      }
    }
    
    d.out("Welcome to Initiation.");
    d.out("//insert storyline here");
    for(int i=0;i<s.people.size();i++) {
      Person p=(Person)s.people.get(i);
      p.details();
      if(i!=s.people.size()-1)
        d.out("-----------------------");
    }
  }
  void create() {
    s=new SaveFile(this);
  }
  boolean load(String fn) {
    try {
      fis = new FileInputStream(fn);
      in = new ObjectInputStream(fis);
      s = (SaveFile)in.readObject();
      in.close();
      
      //call load functions of loaded classes (because root isn't stored)
      for(int i=0;i<s.people.size();i++) {
        Person p=(Person)s.people.get(i);
        p.load(this);
      }
      s.village.load(this);
      
      return true;
    } catch(IOException ex) {
      //ex.printStackTrace();
      return false;
    } catch (ClassNotFoundException ex) {
      //ex.printStackTrace();
      return false;
    }
  }
  void save(String fn) {
    try {
      fos=new FileOutputStream(fn);
      out=new ObjectOutputStream(fos);
      out.writeObject(s);
      out.close();
    } catch(IOException ex) {
      ex.printStackTrace();
    }
  }
}
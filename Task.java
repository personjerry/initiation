import java.io.*;

public class Task implements Serializable {
  String task;
  
  Task(String taskname) {
    taskname=taskname.toLowerCase();
    if(isValidTask(taskname)) {
      task=taskname;
    } else {
      task="nothing";
    }
  }
  public String getDisplayName() {
    if(task.equals("nothing")) {
      return "Nothing";
    }
    if(task.equals("farm")) {
      return "Farming";
    }
    if(task.equals("build")) {
      return "Building";
    }
    if(task.equals("rest")) {
      return "Resting";
    }
    if(task.equals("mine")) {
      return "Mining";
    }
    return "Nothing";
  }
  public static boolean isValidTask(String taskname) {
    taskname=taskname.toLowerCase();
    if(taskname.equals("nothing")||
       taskname.equals("farm")||
       taskname.equals("build")||
       taskname.equals("rest")||
       taskname.equals("mine")) {
      return true;
    }
    return false;
  }
  public void execute(Person doer) {
	  
    boolean ableToWork=true; //no debilitating conditions yet
    if(ableToWork) {
      String tn=task;
      if(tn.equals("farm")) {
        int crop=(doer.attr_int-doer.root.rand.random()%10>3)?(doer.attr_str-doer.root.rand.random()%10+2):0;
        if(crop>0) { //success?
          if(crop>1) {
            doer.root.io.out(doer.name+" farmed, growing "+crop+" crops.");
          } else {
            doer.root.io.out(doer.name+" farmed, growing 1 crop.");
          }
          doer.root.s.village.crop+=crop;
        } else {
          doer.root.io.out(doer.name+" farmed, but couldn't grow any crops.");
        }
      }
    }
    }
}
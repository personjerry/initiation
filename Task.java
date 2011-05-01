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
      if(task.equals("farm")) {
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
      if(task.equals("mine")) {
        int stone=(doer.attr_str-doer.root.rand.random()%10>3)?(doer.attr_str-doer.root.rand.random()%10+2):0;
        if(stone>0) { //success?
          if(stone>1) {
            doer.root.io.out(doer.name+" mined, getting "+stone+" units of stone.");
          } else {
            doer.root.io.out(doer.name+" mined, getting 1 unit of stone.");
          }
          doer.root.s.village.stone+=stone;
          int iron=0;
          for(int i=0;i<stone;i++) {
            if(doer.root.rand.random()%10==0) {
              iron++;
            }
          }
          if(iron>0) {
            if(iron>1) {
              doer.root.io.out("While mining, "+doer.name+" also found "+iron+" units of iron.");
            } else {
              doer.root.io.out("While mining, "+doer.name+" also found 1 unit of iron.");
            }
            doer.root.s.village.iron+=iron;
          }
        } else {
          if(doer.root.rand.random()%25!=0) {
            doer.root.io.out(doer.name+" mined, but didn't get any stone.");
          } else {
            doer.root.io.out(doer.name+" drank instead of mining, but didn't get stoned."); //EASTER EGG!!!!11oneone1
          }
        }
      }
      if(task.equals("trainint")) {
        if(doer.root.rand.random()%10>3) {
          doer.root.io.out(doer.name+" got one additional intelligence point.");
          doer.attr_int+=1;
        } else {
          doer.root.io.out(doer.name+" trained intelligence, but didn't get any smarter.");
        }
      }
      if(task.equals("trainstr")) {
        if(doer.root.rand.random()%10>3) {
          doer.root.io.out(doer.name+" got one additional strength point.");
          doer.attr_str+=1;
        } else {
          doer.root.io.out(doer.name+" worked out, but didn't get any stronger.");
        }
      }
    }
  }
}
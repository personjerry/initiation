import java.io.*;

public class Task implements Serializable {
  String task;
  String secondary; //for instance, "train int"="train">task, "int">secondary
  
  Task(String taskname) {
    this(taskname,"");
  }
  Task(String taskname,String secondary_task) {
    taskname=taskname.toLowerCase();
    secondary_task=secondary_task.toLowerCase();
    if(isValidTask(taskname,secondary_task)) {
      task=taskname;
      secondary=secondary_task;
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
    if(task.equals("train")) {
      return "Training";
    }
    if(task.equals("rest")) {
      return "Resting";
    }
    if(task.equals("mine")) {
      return "Mining";
    }
    return "Nothing";
  }
  public static boolean isValidTask(String taskname,String secondaryname) {
    taskname=taskname.toLowerCase();
    if(taskname.equals("nothing")||
       taskname.equals("farm")||
       taskname.equals("rest")||
       taskname.equals("mine")) {
      return true;
    } else if((taskname.equals("build")&&(secondaryname.equals("wall")||secondaryname.equals("storage")))||
              (taskname.equals("train")&&(secondaryname.equals("int")||secondaryname.equals("str")))) {
      return true;
    }
    return false;
  }
  public void execute(Person doer) {
    
    
    boolean ableToWork=true; //no debilitating conditions yet
    if(ableToWork) {
      if(task.equals("farm")) {
        int crop=(doer.attr_int-doer.root.rand.random()%10>3)?(doer.attr_str-doer.root.rand.random()%10+2):0; //this is complicated; make it make sense please
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
        int stone=(doer.attr_str-doer.root.rand.random()%10>3)?(doer.attr_str-doer.root.rand.random()%10+2):0; //this is complicated; make it make sense please
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
      if(task.equals("train")) {
        if(secondary.equals("int")) {
          if(doer.root.rand.random()%10>3) {
            doer.root.io.out(doer.name+" got one additional intelligence point.");
            doer.attr_int+=1;
          } else {
            doer.root.io.out(doer.name+" trained intelligence, but didn't get any smarter.");
          }
        } else if(secondary.equals("str")) {
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
}
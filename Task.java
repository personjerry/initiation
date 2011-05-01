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
    
    //defaults
    if(taskname.equals("build")&&secondary_task.equals("")) {
      secondary_task="storage";
    }
    if(taskname.equals("train")&&secondary_task.equals("")) {
      secondary_task="int";
    }
   
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
      if(secondary.equals("storage")) {
        return "Building Storage";
      } else {
        return "Building Wall";
      }
    }
    if(task.equals("train")) {
      if(secondary.equals("int")) {
        return "Training Intelligence";
      } else {
        return "Training Strength";
      }
    }
    if(task.equals("rest")) {
      return "Resting";
    }
    if(task.equals("mine")) {
      return "Mining";
    }
    if(task.equals("scout")) {
      return "Scouting";
    }
    return "Nothing";
  }
  public static boolean isValidTask(String taskname,String secondaryname) {
    taskname=taskname.toLowerCase();
    if(taskname.equals("nothing")||
       taskname.equals("farm")||
       taskname.equals("rest")||
       taskname.equals("mine")||
       taskname.equals("scout")) {
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
      if(task.equals("build")) {
        if(secondary.equals("storage")) {
          if(doer.root.s.village.wood>0) {
            doer.root.s.village.wood--;
            doer.root.s.village.addBuilding(new Building(0,0,1));
            doer.root.io.out(doer.name+" contructed a storage building using his wood.");
          } else {
            doer.root.io.out(doer.name+" tried to build a storage building, but didn't have enough wood.");
          }
        } else if(secondary.equals("wall")) {
          if(doer.root.s.village.wood>0) {
            doer.root.s.village.wood--;
           doer.root.s.village.addBuilding(new Building(1,0,1));
            doer.root.io.out(doer.name+" contructed a wall using his wood.");
          } else {
            doer.root.io.out(doer.name+" tried to build a wall, but didn't have enough wood.");
          }
        }
      }
      if(task.equals("scout")) {
        int roll = doer.root.rand.random()%100;
        if(roll < 20)
        {        
          doer.root.io.out(doer.name + " found a small patch of wheat!");
          int yield = (doer.root.rand.random() % (doer.root.s.village.people.size() * 15))+(doer.root.s.village.people.size());
          yield = Math.min(yield, doer.attr_str * 3); //can only carry 3 times strength in food
          doer.root.io.out(doer.name + " managed to carry " + yield + " units of food back to town.");
          doer.root.s.village.food += yield;
        }
        else if (roll < 30)
        {
          int storyroll = doer.root.rand.random()%3;
          if(storyroll == 0)
          {
            doer.root.io.out(doer.name + " found a tree severed by lightning!");
          }
          else if (storyroll == 1)
          {
            doer.root.io.out(doer.name + " found a log floating in a pond!");
          }
          else
          {
            doer.root.io.out(doer.name + " found a broken fence!");
          }
          if((doer.root.rand.random()%(doer.attr_str+5))>(doer.root.rand.random()%(doer.attr_str)))
          {
            doer.root.io.out(doer.name + " managed to carry the wood home. The village now has 1 more piece of wood.");
            doer.root.s.village.wood++;
          }
          else
          {
            doer.root.io.out(doer.name + " was unfortunately not able to lift it home.");
          }
        }
        else
        {        
          doer.root.io.out(doer.name + " returned home safely.");          
        }
      }
    }
  }
}

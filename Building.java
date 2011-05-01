import java.io.*;

public class Building implements Serializable {
  public int type; //0=storage; 1=wall; >2=??????
  public int material; //0=wood; 1=stone; 2=iron
  public int size; //does nothing for now
  Building(int type,int material,int size) {
    this.type=type;
    this.material=material;
    this.size=size;
  }
  public String getTypeName() {
    if(type==0) {
      return "Storage";
    } else if(type==1) {
      return "Wall";
    }
    return "wtf";
  }
  public String getMaterialName() {
    return getMaterialName(false);
  }
  public String getMaterialName(boolean adjective) {
    if(!adjective) {
      if(type==0) {
        return "Wood";
      } else if(type==1) {
        return "Stone";
      } else if(type==2) {
        return "Iron";
      }
      return "ffffuuuu";
    } else {
      if(type==0) {
        return "Wooden";
      } else if(type==1) {
        return "Stone";
      } else if(type==2) {
        return "Iron";
      }
      return "ffffuuuu";
    }
  }
}
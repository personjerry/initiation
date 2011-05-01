import java.util.*;
import java.io.*;

public class SaveFile implements Serializable {
  Village village;
  SaveFile(Initiation root) {    
    //make the village
    village=new Village(root);
  }
}
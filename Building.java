public class Building {
  int type; //0=storage; 1=wall; >2=??????
  int size; //does nothing for now
  int material; //0=wood; 1=stone; 2=iron
  Building(int type,int size,int material) {
    this.type=type;
    this.size=size;
    this.material=material;
  }
}
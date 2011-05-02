public class PerlinNoise {
  public int octaves;
  public double persistance;
  public int[][] primes;
  public int seed;
  
  static final int X_NOISE_GEN = 1619; //random prime
  static final int Y_NOISE_GEN = 31337; //random prime
  static final int OCTAVE_NOISE_GEN = 3463; //random prime
  static final int SEED_NOISE_GEN = 13397; //random prime (orig. 1013)
  
  public static void main(String[] args) {
    PerlinNoise p=new PerlinNoise(10,.4,"molly".hashCode());
    double zoom=0.25; //lower, the more zoomed in; .5 seems good
    for(double i=0;i<20*zoom;i+=zoom) {
      for(double j=0;j<50*zoom;j+=zoom) {
        double pn=p.perlin_noise(i,j);
        if(pn>0) {
          System.out.print("#");
        } else {
          System.out.print(".");
        }
        //System.out.println(p.perlin_noise(i,j));
      }
      System.out.println();
    }
    //System.out.println("out:"+p.perlin_noise(0,0));
  }
  PerlinNoise(int octaves,double persistance,int seed) {
    this.octaves=octaves;
    this.persistance=persistance;
    this.seed=seed;
  }
  public double noise(int x, int y, int octave) {
      int n = (X_NOISE_GEN * x + Y_NOISE_GEN * y + OCTAVE_NOISE_GEN * octave + SEED_NOISE_GEN * seed) & 0x7fffffff;
      
      n = (n >> 13) ^ n;
  
      return 1.0 - ( (n * (n * n * 60493 + 19990303) + 1376312589) & 0x7fffffff ) / 1073741824.0;
   }
  static double interpolate(double a,double b,double x) {
    /*double ft=x*Math.PI;
    double f=(Math.cos(ft)+1)*.5;
    return f*(a+b)+b;*/
    double ft = x * Math.PI;
    double f = (1 - Math.cos(ft)) * .5;
    return  a*(1-f) + b*f;
  }
  double interpolated_noise(double x,double y,int octave) {
    int int_x=(int)Math.floor(x);
    double frac_x=x-(int_x+0.0);
    
    int int_y=(int)Math.floor(y);
    double frac_y=y-(int_y+0.0);
    
    double v1=noise(int_x,int_y,octave);
    double v2=noise(int_x+1,int_y,octave);
    double v3=noise(int_x,int_y+1,octave);
    double v4=noise(int_x+1,int_y+1,octave);
    
    
    double i1=interpolate(v1,v2,frac_x);
    double i2=interpolate(v3,v4,frac_x);
    
    return interpolate(i1,i2,frac_y);
  }
  double perlin_noise(double x,double y) {
    double total=0;
    
    for(int i=0;i<octaves;i++) {
      double frequency=Math.pow(2,i);
      double amplitude=Math.pow(persistance,i);
      total=total+interpolated_noise(x*frequency,y*frequency,i)*amplitude;
    }
    
    return total;
  }
}
/* Use this to generate a random word
 * Words are generated by syllables
 * Each syllable is either: cvc,cv, or vc
 */

//Please update the random functions of WordGen to use MT.java

public class WordGen {
 static int C=1;
 static int V=2;
 
 WordGen() {
  
 }
 
 public String s() {
  String[] ss=new String[] {c()+v(),v()+c(),c()+v()+c()};
  int r=rand(0,ss.length-1);
  return ss[r];
 }
 
 public String ss(int num) {
  String rval="";
  if(num==1) {
   rval=c()+v()+c();
  } else {
   for(int i=0;i<num;i++) {
    rval=rval+s();
   }
  }
  return format(rval);
 }
 
 public String c() {
  //Various character banks (for consonants only); commented out arrays are unused banks
  //String[] cs=new String[] {"d","g","h","k","l","n","r","s","t","y","z"};
  //String[] cs=new String[] {"f","h","l","m","n","r","s","v","z"};
  String[] cs=new String[] {"t","n","s","h","r","d","l","c","m"};
  int r=rand(0,cs.length-1);
  return cs[r];
 }
 
 public String v() {
  String[] vs=new String[] {"a","e","i","o","u"};
  int r=rand(0,vs.length-1);
  return vs[r];
 }
 
 public int rand(int min,int max) {
  return (int)Math.floor(Math.random()*(max-min+1))+min;
 }
 
 public String format(String s) {
  for(int i=0;i<s.length()-1;i++) {
   //check for doubles
   if(s.substring(i,i+1).equals(s.substring(i+1,i+2))) {
    if(type(s.substring(i,i+1))==V) {
     //add new syllable
     s=s+s();
    }
    //remove current char
    String na=s.substring(0,i);
    String nb=s.substring(i+1);
    s=na+nb;
   }
   
   //check for h followed by consanant
   if(s.substring(i,i+1).equals("h")&&type(s.substring(i+1,i+2))==C) {
    //remove h
    String na=s.substring(0,i);
    String nb=s.substring(i+1);
    s=na+nb;
   }
  }
  //if the last letter is h
  if(s.substring(s.length()-1).equals("h")) {
   //remove h
   s=s.substring(0,s.length()-1);
  }
  //if the last letter is e
  if(s.substring(s.length()-1).equals("e")) {
   //remove e
   s=s.substring(0,s.length()-1);
   //add new sylable
   s=s+s();
   //format
   s=format(s);
  }
  return s;
 }
 
 public int type(String s) {
  s=s.substring(0,1);
  if(s.equals("a")||s.equals("e")||s.equals("i")||s.equals("o")||s.equals("u")) {
   return V;
  } else {
   return C;
  }
 }
 public String fCap(String s) {
  String fLetter=s.substring(0,1);
  String oLetters=s.substring(1);
  s=fLetter.toUpperCase()+oLetters.toLowerCase();
  return s;
 }
}

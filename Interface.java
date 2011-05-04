import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Interface extends JFrame implements ActionListener {
  String next;
  
  //Elements
  JTextField input_field;
  JTextArea output_area;
  JScrollPane scroll_area;
  public Interface() {
    next="";
    
    //JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Initiation");
    
    //JPanel
    /*JPanel panel1=new JPanel();
    input_field=new JTextField(50);
    input_field.addActionListener(this);
    panel1.add(input_field);
    output_area=new JTextArea(10,50);
    output_area.setEditable(false);
    output_area.setLineWrap(true);
    output_area.setWrapStyleWord(true);
    scroll_area= new JScrollPane(output_area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel1.add(scroll_area);
    //panel1.add(scroll);
    this.add(panel1);*/
    Box box1=Box.createVerticalBox();
    output_area=new JTextArea(25,50);
    output_area.setEditable(false);
    output_area.setLineWrap(true);
    output_area.setWrapStyleWord(true);
    scroll_area= new JScrollPane(output_area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    box1.add(scroll_area);
    input_field=new JTextField(50);
    input_field.addActionListener(this);
    box1.add(input_field);
    this.add(box1);
    
    //Pack and Set Visible
    this.pack();
    this.setVisible(true);
    
    //Focus on input_field
    input_field.requestFocus();
  }
  public void err(String err) {
    System.out.println("Error: "+err);
  }
  public void out(String line) {
    output_area.append(line+"\n");
    output_area.setCaretPosition(output_area.getDocument().getLength());
  }
  public void out(int line) {
    out(line+"");
  }
  public String nextLine() {
    while(next.equals("")) { }
    String rval=next;
    next="";
    this.out("\n");
    return rval;
  }
  public String pressEnter() {
    return nextLine();
  }
  public void close() {
    System.exit(0);
  }
  
  //Event Handling
  public void actionPerformed(ActionEvent e) {
    if(e.getSource()==input_field) {
      if(!input_field.getText().equals("")) {
        next=input_field.getText();
        input_field.setText("");
      }
    }
  }
}
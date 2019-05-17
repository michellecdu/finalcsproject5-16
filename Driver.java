import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/******************************************************************
* The Driver class contains the main method and runs the final game.
* @authors Michelle Du, Shreya Singh
*******************************************************************/
public class Driver
  {
  /*****************************
  * Main method (runs the game).
  ******************************/
  public static void main(String[] args) throws Exception
   {
      long start = System.currentTimeMillis();
      JFrame frame = new JFrame("Frozen Dance Dance Revolution");
      frame.setSize(615, 800);
      frame.setLocation(0, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Scoreboard scoreboard = new Scoreboard();
      frame.setContentPane(scoreboard);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b=new JButton("START");  
      frame.add(b, BorderLayout.NORTH);
      Display m = new Display();
      frame.add(m); 
      frame.setVisible(true);
      
      b.addActionListener(new ActionListener()
      {
        /***********************************
        * Plays song when button is pressed.
        ************************************/
        public void actionPerformed(ActionEvent e)
        {
            Sound.play("Frozen - Do You Want to Build a Snowman.wav");
            setMusicStarted(true);
        }
      });    
      b.setFocusable(false);
      boolean first = true;
      while (!musicStarted)
      {
         TimeUnit.MILLISECONDS.sleep(2000);
      }
      
      while (System.currentTimeMillis() - start < 95000) {
         long startTime = System.currentTimeMillis();
         TimeUnit.MILLISECONDS.sleep(20000);
         scoreboard.update(m, scoreboard.getScoreLabel(), first);
         first = false;
         m.setY(800);
         m.changeArrow();
         m.timer.restart();
      } 
      System.exit(0);   
   }
      /***********************************
        * musicStarted returns whether or
        * not the music has started.
        ************************************/
      private static boolean musicStarted = false;
      public static void setMusicStarted(boolean started)
      {
         musicStarted = started;
      } 
}
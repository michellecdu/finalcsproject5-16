import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;
/******************************************************************
* The Display class is a JPanel that listens for key presses and
* displays and manipulates the content within the JPanel.
* @authors Michelle Du, Shreya Singh
*******************************************************************/  
public class Display extends JPanel implements ActionListener {
 
    int x, y,dY=3;
    long startTime = System.currentTimeMillis();
    Timer timer;
    long difference;
    boolean pressed = false;
    Random r = new Random(); 
    int rand = r.nextInt(4);
    Display() {
        x = 0;
        y = getHeight()+25;
        timer = new Timer(2, this);
    }
    /**********************************************************************
    * setY is a modifier that allows other classes to change the value of y
    * (the vertical component of the moving arrows).
    * @param a   desired value of y
    **********************************************************************/
    public void setY(int a) {
      y = a;
    }
    /***********************************************************************
    * getDifference is an accessor that allows other classes to retrieve the
    * value of difference (the amount of time elapsed from when the arrow
    * started moving to the current time).
    * @return difference
    ************************************************************************/
    public long getDifference() {
      long currentDiff = difference;
      System.out.println("getDifference is: " + difference);
      difference = 0;
      return currentDiff;
    }
    /*************************************************************************
    * changeArrow changes the value of rand, which is later used to change the
    * direction of the arrow.
    **************************************************************************/
    public void changeArrow() {
      startTime = System.currentTimeMillis();
      pressed = false;
      rand = r.nextInt(4);
    }
    /**************************************************************************
    * actionPerformed changes the y value of the arrow when the timer sends the
    * signal to do so.
    ***************************************************************************/ 
    public void actionPerformed(ActionEvent e) {
        y-=dY;
        repaint();
    }
    /*****************************************************************************
    * paintComponent displays the images on the panel and listens for key presses.
    ******************************************************************************/  
    public void paintComponent(Graphics g) {
     g.setColor(Color.WHITE);
     g.fillRect(0, 0, 1000, 1000);
     ImageIcon snow = new ImageIcon("images/fallingsnow.gif");
     g.drawImage(snow.getImage(), 0, 0, null);
     ImageIcon background = new ImageIcon("images/olaf.gif");
     g.drawImage(background.getImage(), 50, 200, null);
     ImageIcon arrow = new ImageIcon("images/arrowkeys.jpg");
     g.drawImage(arrow.getImage(), 175, 0, null);
     
     
     ImageIcon[] movingArrows = {
         new ImageIcon("images/leftarrow.png"),
         new ImageIcon("images/downarrow.png"),
         new ImageIcon("images/uparrow.png"),
         new ImageIcon("images/rightarrow.png") 
     }; 
     // ADDED NEXT TWO LINES
     addKeyListener(new Key());
     setFocusable(true);
     ImageIcon currArrow = movingArrows[rand];
     if (rand == 0) {
      g.drawImage(currArrow.getImage(), 180, y, 50, 50, null); 
     }
     else if (rand == 1) {
      g.drawImage(currArrow.getImage(), 240, y, 50, 50, null); 
     }
     else if (rand == 2) {
      g.drawImage(currArrow.getImage(), 300, y, 50, 50, null); 
     }
     else {
      g.drawImage(currArrow.getImage(), 360, y, 50, 50, null); 
     }
   //System.out.println(getWidth()+","+getHeight());

 }
/******************************************************************
* The Key class tells the computer what to do when a certain key is
* pressed.
* @authors Michelle Du, Shreya Singh
*******************************************************************/ 
 private class Key extends KeyAdapter {
   public void keyPressed (KeyEvent e) {
      if (!pressed) {
         if (rand == 0 && e.getKeyCode() == KeyEvent.VK_LEFT)
            difference = difference(startTime, System.currentTimeMillis());
         else if (rand == 1 && e.getKeyCode() == KeyEvent.VK_DOWN)
            difference = difference(startTime, System.currentTimeMillis());
         else if (rand == 2 && e.getKeyCode() == KeyEvent.VK_UP)
            difference = difference(startTime, System.currentTimeMillis());
         else if (rand == 3 && e.getKeyCode() == KeyEvent.VK_RIGHT)
            difference = difference(startTime, System.currentTimeMillis());
      }
   }
 }
/************************************************************
 * difference returns the difference between the current time
 * and the time at which the current arrow started to move.
 * @param start   time when arrow started to move
 * @param end     current time
 * @return difference
 *************************************************************/ 
public long difference(long start, long end) {
   return end - start;
}

 
}

    


// Painter.java
// Andrew Davison, August 2007, ad@fivedots.coe.psu.ac.th

/* Let the user draw black dots into a panel by dragging
   the mouse.
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Painter extends JFrame 
{
  public Painter()
  {
    super( "A Simple Paint Program" );

    Container c = getContentPane();
    c.add( new PaintPanel(), BorderLayout.CENTER);
    c.add( new Label("Drag the mouse to draw"), BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 300);  

    // center this window
    Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension winDim = this.getSize();
    this.setLocation( (screenDim.width-winDim.width)/2,
                      (screenDim.height-winDim.height)/2);

    setResizable(false);    // fixed size display
    setVisible(true);   
  } // end of Painter()

   // -----------------------------------------------

  public static void main(String args[])
  { new Painter(); }
} // end of Painter


// ----------------------PaintPanel class ----------------------


class PaintPanel extends JPanel
/* A panel that catches mouse drag events and stores
   the cursor points in the points[] array.
   These are drawn onto the panel at each repaint.
*/
{
  private static final int MAXPOINTS = 5000;

  private Point[] points = new Point[MAXPOINTS];
        // for storing the paintable points
  private int nPoints = 0;


  public PaintPanel()
  {
    setBackground(Color.white);
    addMouseMotionListener( new MouseMotionAdapter() {
	  public void mouseDragged( MouseEvent e)
      // record the current cursor position, then request a repaint
	  {  if (nPoints < MAXPOINTS)
           points[nPoints++] = new Point(e.getX(), e.getY());
 		 repaint();   // the repaint will call paintComponent()
	  }
	});
  } // end of PaintPanel() constructor


  public void paintComponent(Graphics g)
  // repaint the panel by redrawing all the stored points
  { 
    super.paintComponent(g);   // repaint standard stuff first
    for (int i = 0; i < nPoints; i++)
      g.fillOval( points[i].x, points[i].y, 4, 4);   // the pen is a 4x4 black circle
  }

} // end of PaintPanel


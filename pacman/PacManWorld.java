import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
/**
 * PacMan world that allows adding arrow key listener
 * 
 * @author Emily Zhou
 * @version 5-8-2018
 */
public class PacManWorld extends ActorWorld
{
    private ArrowListener listener = null;
    /**
     * Constructor for objects of class PacManWorld
     */
    public PacManWorld()
    {
        super();
    }

    /**
     * Constructor for objects of class PacManWorld
     * 
     * @param gr the Grid
     */
    public PacManWorld(Grid gr)
    {
        super(gr);
    }

    /**
     * Add an arrow key listener
     * 
     * @param l the listener
     */
    public void addArrowListener(ArrowListener l)
    {
        listener = l;
    }

    /**
     * Process key pressed event
     * 
     * @param description the information on the key pressed event
     * @param loc the location in the grid where the key pressed
     * 
     * @return true if the World consumes the event
     */
    @Override
    public boolean keyPressed(String description, Location loc)
    {
        if (listener == null) return false;

        KeyStroke stroke = KeyStroke.getKeyStroke(description);
        int keyCode = stroke.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) 
        {
            listener.upPressed();
            return true;
        }
        if (keyCode == KeyEvent.VK_DOWN)
        {
            listener.downPressed();
            return true;
        }
        if (keyCode == KeyEvent.VK_LEFT) 
        {
            listener.leftPressed();
            return true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) 
        {
            listener.rightPressed();
            return true;
        }
        return false;
    }

}
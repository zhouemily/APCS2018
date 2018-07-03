import info.gridworld.actor.Bug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;
/**
 * The PacMan that eats Food while moving forward and turns
 * on arrow key events.  It gets enpowered for a short period
 * of time after eats a special Food. While it is enpowered
 * it can eat Ghost.  If Ghost is eatan, a new Ghost is born.
 * 
 * When a PacMan is eaten by a Ghost, a new PacMan is created.
 * Each PacMan instance created is called a PacMan's life and
 * PacMan has total 3 lifes - that is, only 3 PacMan instances
 * can be created.
 * 
 * @author Emily Zhou
 * @version 5/21/18
 */
public class PacMan extends Bug implements ArrowListener
{
    private static int totalLifes = 3;
    private static int currentLife = 0;

    private long poweredTime = 0;
    private int powerLastPeriod = 10*1000; //10sec
    /**
     * Constructs a PacMan
     */
    public PacMan()
    {
        super();
        currentLife++;
    }

    /**
     * @return true if PacMan has more lifes otherwise false
     */
    public static boolean hasMoreLife()
    {
        return totalLifes > currentLife;
    }

    /**
     * Moves if can
     */
    @Override
    public void act()
    {
        if (canMove()) move();
    }

    /**
     * Moves to the direction of pressed button without leaving a flower.
     */
    @Override
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Actor neighbor = gr.get(next);
        if (canMove()) 
        {
            moveTo(next);
        }
    }

    /**
     * @return true if the PacMan is enpowered - that is, can eat ghost
     */
    public boolean isPowered() 
    {
        return (System.currentTimeMillis() - poweredTime) < powerLastPeriod;
    }

    /**
     * Move to a new location and become enpowered if a special
     * Food is eaten as result of the move.  If it is enpowered
     * and if the new location was occupied by a Ghost, it eats
     * the Ghost.
     * 
     * @param newLocation the new location to move to
     */
    public void moveTo(Location newLocation)
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Actor other = gr.get(newLocation);
        if (other instanceof SpecialFood) 
        {

            poweredTime = System.currentTimeMillis();
        }

        super.moveTo(newLocation);
        if (other instanceof Ghost) 
        {
            Ghost eatenGhost = (Ghost)other;
            Ghost newGhost = new Ghost(eatenGhost.getNormalColor(), eatenGhost.getBrightColor());
            newGhost.setColor(eatenGhost.getColor());
            newGhost.putSelfInGrid(gr, eatenGhost.getChamberLocation());
        }
    }

    /**
     * Check if it can move forward.
     * 
     * @return true if it can move otherwise false
     */
    @Override
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Actor neighbor = gr.get(next);
        if(!gr.isValid(next) || neighbor instanceof Border)
            return false;

        if (isPowered() && (neighbor instanceof Ghost))
            return true;
        return (neighbor == null) || (neighbor instanceof Flower);
    }

    /**
     * This is a no-op method as PacMan only responses to arrow key events to turn
     */
    @Override
    public void turn()
    {
        //no-op
    }

    /**
     * Responses to the up arrow.
     */
    public void upPressed()
    {
        setDirection(Location.NORTH);
    }

    /**
     * Responses to the down arrow.
     */
    public void downPressed()
    {
        setDirection(Location.SOUTH);
    }

    /**
     * Responses to the left arrow.
     */
    public void leftPressed()
    {
        setDirection(Location.WEST); 
    }

    /**
     * Responses to the right arrow.
     */
    public void rightPressed()
    {
        setDirection(Location.EAST); 
    }   
}
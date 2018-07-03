import java.awt.Color;

import java.util.ArrayList;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
/**
 * The Ghost moves in the maze and turns if can not move.
 * It eats PacMan and walk over Food and Ghost. Its color
 * will turn to bright color while PacMan is enpowered.
 * An enpowered PacMan can eat Ghost.
 * 
 * @author Emily Zhou
 * @version 4-15-2018
 */
public class Ghost extends Bug
{

    //Tracks whether this Ghost is on top of a Food
    private Actor overActor = null;

    private Color normalColor;
    private Color brightColor;

    //Tracks Ghost movement to prevent from entering chamber again
    private Location doorfront = new Location(1, 9);
    private Location door = new Location(2, 9);
    private Location chamber = new Location(3, 9);
    /**
     * Constructor for objects of class Ghost
     * 
     * @param color the normal color of the Ghost to be created
     * @param bright the bright color of the Ghost to be created
     */
    public Ghost(Color color, Color bright)
    {
        super(color);
        normalColor = color;
        brightColor = bright;
    }

    /**
     * @return the Ghost's normal color
     */
    public Color getNormalColor()
    {
        return normalColor;
    }

    /**
     * @return the Ghost's bright color
     */
    public Color getBrightColor()
    {
        return brightColor;
    }

    /**
     * @return the Ghost chamber location
     */
    public Location getChamberLocation()
    {
        return chamber;
    }

    /**
     * Moves or turns and avoid returning to the chamber
     */
    @Override
    public void act() 
    {
        if (PacManRunner.isPacManPowered()) 
        {
            setColor(brightColor);
        }
        else 
        {
            setColor(normalColor);
        }

        if (!canMove()) 
        {
            turn();
            return;
        }
        //if in chamber try to get out
        Location loc = getLocation();
        if (loc.equals(chamber)) 
        {
            Location next = loc.getAdjacentLocation(getDirection()+Location.RIGHT);
            if (canMove(next)) 
            {
                setDirection(getDirection() + Location.RIGHT);
                move();
                return;  
            }
            next = loc.getAdjacentLocation(getDirection()+Location.LEFT);
            if (canMove(next)) 
            {
                setDirection(getDirection() + Location.LEFT);
                move();
                return;  
            }
        }
        //random move or turn if can
        int r = (int) (Math.random() * 2);
        if (r == 0) 
        {
            move();
        }
        else 
        {
            Location next = loc.getAdjacentLocation(getDirection()+Location.RIGHT);
            if (canMove(next)) 
            {
                turn();
                return;
            }
            move();
        }

    }

    /**
     * Moves forward and it was on top of a Food, it leaves the Food behind
     */
    @Override
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;

        Location myloc = getLocation();
        Location next = myloc.getAdjacentLocation(getDirection());
        Actor oldOverActor = overActor;

        if (gr.isValid(next)) 
        {
            Actor a = gr.get(next);
            if ((a instanceof Food) || (a instanceof Ghost)) 
            {
                overActor = a;
            } 
            else 
            {
                overActor = null;

            }
            moveTo(next);
            if (a instanceof PacMan) 
            {
                PacManRunner.createNewPacManIfCan();
            }
        }
        else 
        {
            removeSelfFromGrid();
            overActor = null;
        }
        if (oldOverActor != null) 
        {
            oldOverActor.putSelfInGrid(gr, myloc);
        }
    }

    /**
     * Turn in its right
     */
    @Override
    public void turn() 
    {
        setDirection(getDirection() + Location.RIGHT);
    }

    /**
     * Tests whether this Ghost can move forward into a location
     * that is empty or contains Food or PacMan that is not powered.
     * 
     * @return true if this Ghost can move
     */
    @Override
    public boolean canMove()
    {

        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        return canMove(next);

    }

    /**
     * @param loc location to check if can move to
     */
    private boolean canMove(Location  loc) 
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) return false;

        if (!gr.isValid(loc))
            return false;

        //no return back to chamber
        if (loc.equals(door) && getLocation().equals(doorfront)) 
        {
            return false;
        }
        if (loc.equals(chamber) && getLocation().equals(door)) 
        {
            return false;
        }
        Actor neighbor = gr.get(loc);
        return (neighbor == null) ||
            (neighbor instanceof Food) ||
            ((neighbor instanceof PacMan) && !((PacMan)neighbor).isPowered()) ||
            (neighbor instanceof Ghost);

    }
}
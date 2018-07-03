import info.gridworld.actor.Flower;
import java.awt.Color;
/**
 * Food for pacman to eat
 * 
 * @author Emily Zhou 
 * @version 4-15-2018
 */
public class Food extends Flower
{
    /**
     * Constructor for objects of class Food
     */
    public Food()
    {
        super();
    } 

    /**
     * Constructor for objects of class Food
     * 
     * @param c the color of the Food to be created
     */
    public Food(Color c)
    {
        super(c);
        setColor(c);
    }

    /**
     * Food does not act
     */
    public void act() 
    {
        //no-op
    }
}
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;

/**
 * The PacManRunner lays out maze, spread food for PacMan on paths and
 * place initial positions for Ghosts and PacMan
 * 
 * @author Emily Zhou
 * 
 * @version 4/9/18
 */
public class PacManRunner
{
    private static PacManWorld world;
    private static PacMan packy;

    /**
     * Creates a PacMan world and populates the world with PacMan, Ghost and Food objects.
     * 
     * @param args  information from the commond line
     */
    public static void main(String[] args)
    {
        world = new PacManWorld(new BoundedGrid<Actor>(11,20));

        //layout maze

        //left wall 
        world.add(new Location (0,0), new Border());
        world.add(new Location (1,0), new Border());
        world.add(new Location (2,0), new Border());
        world.add(new Location (3,0), new Border());
        world.add(new Location (4,0), new Border());
        world.add(new Location (5,0), new Border());
        world.add(new Location (6,0), new Border());
        world.add(new Location (7,0), new Border());
        world.add(new Location (8,0), new Border());
        world.add(new Location (9,0), new Border());
        world.add(new Location (10,0), new Border());

        //right wall 
        world.add(new Location (0,18), new Border());
        world.add(new Location (1,18), new Border());
        world.add(new Location (2,18), new Border());
        world.add(new Location (3,18), new Border());
        world.add(new Location (4,18), new Border());
        world.add(new Location (5,18), new Border());
        world.add(new Location (6,18), new Border());
        world.add(new Location (7,18), new Border());
        world.add(new Location (8,18), new Border());
        world.add(new Location (9,18), new Border());
        world.add(new Location (10,18), new Border());

        //top wall 
        world.add(new Location (0,0), new Border());
        world.add(new Location (0,1), new Border());
        world.add(new Location (0,2), new Border());
        world.add(new Location (0,3), new Border());
        world.add(new Location (0,4), new Border());
        world.add(new Location (0,5), new Border());
        world.add(new Location (0,6), new Border());
        world.add(new Location (0,7), new Border());
        world.add(new Location (0,8), new Border());
        world.add(new Location (0,9), new Border());
        world.add(new Location (0,10), new Border());
        world.add(new Location (0,11), new Border());
        world.add(new Location (0,12), new Border());
        world.add(new Location (0,13), new Border());
        world.add(new Location (0,14), new Border());
        world.add(new Location (0,15), new Border());
        world.add(new Location (0,16), new Border());
        world.add(new Location (0,17), new Border());

        //bottom wall 
        world.add(new Location (10,0), new Border());
        world.add(new Location (10,1), new Border());
        world.add(new Location (10,2), new Border());
        world.add(new Location (10,3), new Border());
        world.add(new Location (10,4), new Border());
        world.add(new Location (10,5), new Border());
        world.add(new Location (10,6), new Border());
        world.add(new Location (10,7), new Border());
        world.add(new Location (10,8), new Border());
        world.add(new Location (10,9), new Border());
        world.add(new Location (10,10), new Border());
        world.add(new Location (10,11), new Border());
        world.add(new Location (10,12), new Border());
        world.add(new Location (10,13), new Border());
        world.add(new Location (10,14), new Border());
        world.add(new Location (10,15), new Border());
        world.add(new Location (10,16), new Border());
        world.add(new Location (10,17), new Border());

        //inner A vertical wall
        world.add(new Location (2,2), new Border());
        world.add(new Location (3,2), new Border());
        world.add(new Location (4,2), new Border());
        world.add(new Location (6,2), new Border());
        world.add(new Location (7,2), new Border());
        world.add(new Location (8,2), new Border());

        //inner B vertical wall  
        world.add(new Location (2,4), new Border());
        world.add(new Location (3,4), new Border());
        world.add(new Location (4,4), new Border());
        world.add(new Location (6,4), new Border());
        world.add(new Location (7,4), new Border());
        world.add(new Location (8,4), new Border());

        //inner C vertical wall
        world.add(new Location (2,6), new Border());
        world.add(new Location (4,6), new Border());
        world.add(new Location (6,6), new Border());
        world.add(new Location (7,6), new Border());
        world.add(new Location (8,6), new Border());

        //inner D vertical wall
        world.add(new Location (2,8), new Border());
        world.add(new Location (4,8), new Border());
        world.add(new Location (6,8), new Border());
        world.add(new Location (7,8), new Border());
        world.add(new Location (8,8), new Border());

        //inner E vertical wall
        world.add(new Location (2,10), new Border());
        world.add(new Location (4,10), new Border());
        world.add(new Location (6,10), new Border());
        world.add(new Location (7,10), new Border());
        world.add(new Location (8,10), new Border());

        //inner F vertical wall
        world.add(new Location (2,12), new Border());
        world.add(new Location (4,12), new Border());
        world.add(new Location (6,12), new Border());
        world.add(new Location (7,12), new Border());
        world.add(new Location (8,12), new Border());

        //inner G vertical wall
        world.add(new Location (2,14), new Border());
        world.add(new Location (3,14), new Border());
        world.add(new Location (4,14), new Border());
        world.add(new Location (6,14), new Border());
        world.add(new Location (7,14), new Border());
        world.add(new Location (8,14), new Border());

        //inner H vertical wall
        world.add(new Location (2,16), new Border());
        world.add(new Location (3,16), new Border());
        world.add(new Location (4,16), new Border());
        world.add(new Location (6,16), new Border());
        world.add(new Location (7,16), new Border());
        world.add(new Location (8,16), new Border());

        //middle closures 
        world.add(new Location (4,3), new Border());
        world.add(new Location (4,7), new Border());
        world.add(new Location (4,11), new Border());
        world.add(new Location (4,15), new Border());

        //Ghost chamber vertical walls
        world.add(new Location (2,7), new Border());
        world.add(new Location (3,7), new Border());
        world.add(new Location (2,11), new Border());
        world.add(new Location (3,11), new Border());
        //Ghost chamber fill middle bottom
        world.add(new Location (4,9), new Border());

        //layout food for pacman

        //vertical AA 
        world.add(new Location (1,1), new SpecialFood());
        world.add(new Location (2,1), new Food());
        world.add(new Location (3,1), new Food());
        world.add(new Location (4,1), new Food());
        world.add(new Location (5,1), new Food());
        world.add(new Location (6,1), new Food());
        world.add(new Location (7,1), new Food());
        world.add(new Location (8,1), new Food());
        world.add(new Location (9,1), new SpecialFood());

        //vertical BB
        world.add(new Location (1,3), new Food());
        world.add(new Location (2,3), new Food());
        world.add(new Location (3,3), new SpecialFood());
        world.add(new Location (5,3), new Food());
        world.add(new Location (6,3), new Food());
        world.add(new Location (7,3), new Food());
        world.add(new Location (8,3), new Food());

        //vertical CC
        world.add(new Location (1,5), new Food());
        world.add(new Location (2,5), new Food());
        world.add(new Location (3,5), new Food());
        world.add(new Location (4,5), new Food());
        world.add(new Location (5,5), new Food());
        world.add(new Location (6,5), new Food());
        world.add(new Location (7,5), new Food());
        world.add(new Location (8,5), new Food());

        //vertical DD 
        world.add(new Location (1,7), new Food());
        world.add(new Location (5,7), new Food());
        world.add(new Location (6,7), new Food());
        world.add(new Location (7,7), new Food());
        world.add(new Location (8,7), new Food());

        //vertical EE 
        world.add(new Location (1,9), new SpecialFood());
        world.add(new Location (2,9), new Food());
        world.add(new Location (3,9), new Food());
        world.add(new Location (5,9), new Food());
        world.add(new Location (6,9), new Food());
        world.add(new Location (7,9), new Food());
        world.add(new Location (8,9), new Food());
        world.add(new Location (9,9), new SpecialFood());

        //vertical FF
        world.add(new Location (1,11), new Food());
        world.add(new Location (5,11), new Food());
        world.add(new Location (6,11), new Food());
        world.add(new Location (7,11), new Food());
        world.add(new Location (8,11), new Food());

        //vertical GG 
        world.add(new Location (1,13), new Food());
        world.add(new Location (2,13), new Food());
        world.add(new Location (3,13), new Food());
        world.add(new Location (4,13), new Food());
        world.add(new Location (5,13), new Food());
        world.add(new Location (6,13), new Food());
        world.add(new Location (7,13), new Food());
        world.add(new Location (8,13), new Food());
        world.add(new Location (9,13), new Food());

        //vertical HH
        world.add(new Location (1,15), new Food());
        world.add(new Location (2,15), new Food());
        world.add(new Location (3,15), new SpecialFood());
        world.add(new Location (5,15), new Food());
        world.add(new Location (6,15), new Food());
        world.add(new Location (7,15), new Food());
        world.add(new Location (8,15), new Food());

        //vertical JJ
        world.add(new Location (1,17), new SpecialFood());
        world.add(new Location (2,17), new Food());
        world.add(new Location (3,17), new Food());
        world.add(new Location (4,17), new Food());
        world.add(new Location (5,17), new Food());
        world.add(new Location (6,17), new Food());
        world.add(new Location (7,17), new Food());
        world.add(new Location (8,17), new Food());
        world.add(new Location (9,17), new SpecialFood());

        //fill top
        world.add(new Location (1,2), new Food());
        world.add(new Location (1,4), new Food());
        world.add(new Location (1,6), new Food());
        world.add(new Location (1,8), new Food());
        world.add(new Location (1,10), new Food());
        world.add(new Location (1,12), new Food());
        world.add(new Location (1,14), new Food());
        world.add(new Location (1,16), new Food());

        //fill bottom
        world.add(new Location (9,2), new Food());
        world.add(new Location (9,3), new Food());
        world.add(new Location (9,4), new Food());
        world.add(new Location (9,5), new Food());
        world.add(new Location (9,6), new Food());
        world.add(new Location (9,7), new Food());
        world.add(new Location (9,8), new Food());
        world.add(new Location (9,10), new Food());
        world.add(new Location (9,11), new Food());
        world.add(new Location (9,12), new Food());
        world.add(new Location (9,13), new Food());
        world.add(new Location (9,14), new Food());
        world.add(new Location (9,15), new Food());
        world.add(new Location (9,16), new Food());

        //fill middle row
        world.add(new Location (5,2), new Food());
        world.add(new Location (5,4), new Food());
        world.add(new Location (5,6), new Food());
        world.add(new Location (5,8), new Food());
        world.add(new Location (5,10), new Food());
        world.add(new Location (5,12), new Food());
        world.add(new Location (5,14), new Food());
        world.add(new Location (5,16), new Food());

        //next to ghost vertial walls
        world.add(new Location (3,6), new Food());
        world.add(new Location (3,12), new Food());

        packy = new PacMan();
        packy.setColor(Color.YELLOW);
        world.addArrowListener(packy);
        world.add(new Location(9,9), packy);

        //make ghosts

        Ghost ghost1 = new Ghost(Color.RED, Color.BLUE);
        world.add(new Location(2,9), ghost1);

        Ghost ghost2 = new Ghost(Color.PINK, Color.BLUE);
        world.add(new Location (3,9), ghost2);

        Ghost ghost3 = new Ghost(Color.MAGENTA, Color.BLUE);
        world.add(new Location (3,8), ghost3);

        Ghost ghost4 = new Ghost(Color.GRAY, Color.BLUE);
        world.add(new Location (3,10), ghost4);

        world.show();
        packy.move();
    }

    /**
     * Create a new PacMan if PacMan has more life
     */
    public static void  createNewPacManIfCan()
    {
        System.out.println("PacMan lost 1 life!");
        if (!PacMan.hasMoreLife()) 
        {
            System.out.println("GAME OVER");
            return;
        }
        packy = new PacMan();
        packy.setColor(Color.YELLOW);
        world.addArrowListener(packy);
        world.add(new Location(9,9), packy);
    }

    /**
     * Check if PacMan is enpowered
     * 
     * @return true if PacMan is enpowered
     */
    public static boolean isPacManPowered()
    {
        if (packy == null) return false;
        return packy.isPowered();
    }
}
import java.awt.Color;
import java.util.Random;
/**
 * A tetrad has 4 blocks.  A tetrad can have different color and its 4 blocks
 * can have different arrangement related to each other.
 * 
 * @author Emily Zhou
 * @version 1/28/17
 */
public class Tetrad
{
    private MyBoundedGrid<Block> myGrid;
    private Block[] blocks;
    private Location[] myLocs;

    /**
     * Constructor for objects of class Tetrad
     * and initialize blocks with index 0 assigned the certered block.
     * 
     * @param grid the bounded grid
     * 
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        myGrid = grid;
        Random rn = new Random();
        int color = rn.nextInt(7) + 1;
        blocks = new Block[4];
        myLocs = new Location[4];
        for (int i = 0; i < 4; i++)
        {
            blocks[i] = new Block();
            if (color == 1 )
            {
                blocks[i].setColor(Color.RED);
                if (myLocs[i] == null)
                {
                    myLocs[0] = new Location(1,5);
                    myLocs[1] = new Location(0,5);
                    myLocs[2] = new Location(2,5);
                    myLocs[3] = new Location(3,5);
                }
            }
            else if (color == 2 )
            {
                blocks[i].setColor(Color.GRAY);
                if (myLocs[i] == null)
                {
                    myLocs[0] = new Location(0,4);
                    myLocs[1] = new Location(0,3);
                    myLocs[2] = new Location(0,5);
                    myLocs[3] = new Location(1,4);
                }
            } 
            else if (color == 3 )
            {
                blocks[i].setColor(Color.CYAN);
                if (myLocs[i] == null)
                {
                    myLocs[0] = new Location(0,4);
                    myLocs[1] = new Location(0,5);
                    myLocs[2] = new Location(1,4);
                    myLocs[3] = new Location(1,5);
                }
            }
            else if (color == 4 )
            {
                blocks[i].setColor(Color.YELLOW);
                if (myLocs[i] == null)
                {
                    myLocs[0] = new Location(1,4);
                    myLocs[1] = new Location(0,4);
                    myLocs[2] = new Location(2,4);
                    myLocs[3] = new Location(2,5);
                }
            }
            else if (color == 5 )
            {
                blocks[i].setColor(Color.MAGENTA);
                if (myLocs[i] == null)
                {
                    myLocs[0] = new Location(1,4);
                    myLocs[1] = new Location(0,4);
                    myLocs[2] = new Location(2,4);
                    myLocs[3] = new Location(2,3);
                }
            }
            else if (color == 6 )
            {
                blocks[i].setColor(Color.BLUE);
                if (myLocs[i] == null)
                {
                    myLocs[0] = new Location(0,4);
                    myLocs[1] = new Location(1,4);
                    myLocs[2] = new Location(1,3);
                    myLocs[3] = new Location(0,5);
                }
            }
            else if (color == 7 )
            {
                blocks[i].setColor(Color.GREEN);
                if (myLocs[i] == null)
                {
                    myLocs[0] = new Location(0,4);
                    myLocs[1] = new Location(1,4);
                    myLocs[2] = new Location(1,5);
                    myLocs[3] = new Location(0,3);
                }
            }
        }
        addToLocations(myGrid,myLocs);
    }

    /**
     * Places one block in each of the assigned locations.
     * 
     * @precondition the tetrad blocks are not in any grid
     * @param grid the grid
     * @param locs the location array for the tetrad
     */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
        myGrid = grid;
        this.myLocs = locs;
    }

    /**
     * Removes the entire tetrad from the grid and sets its locations to null.
     * 
     * @precondition the tetrad blocks are in the grid
     * @return the location array of the tetrad
     */
    private Location[] removeBlocks()
    {
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i].removeSelfFromGrid();
        }
        Location[] tempLocs = myLocs;
        myLocs = null;
        return tempLocs;
    }

    /**
     * Determines if the specified locations are valid and if they are empty.
     * 
     * @param grid the grid
     * @param locs the location array to check
     * 
     * @return true if the locations are valid and are empty; otherwise,
     *         false
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int i = 0; i < locs.length; i++)
        {
            if (!grid.isValid(locs[i]))
                return false;
        }
        int count = 0;
        for (int i = 0; i < locs.length; i++)
        {
            Block b = grid.get(locs[i]);
            if (b == null)
                count++;
            else
            {
                for (int j = 0; j < blocks.length; j++)
                {
                    if (b == blocks[j])
                        count++;
                }
            }
        }
        if (count == 4)
            return true;
        return false;
    }

    /**
     * Translates the tetrad to a new location, if the location is valid and unoccupied.
     * 
     * @param deltaRow deta row to move to
     * @param deltaCol deta colum to move to
     * @return true if the new location is empty and valid other wise return false
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] tempLocs = new Location[4];
        for (int i = 0; i < 4; i++)
        {
            tempLocs[i] = new Location(myLocs[i].getRow() + 
                                deltaRow, myLocs[i].getCol() + deltaCol);
        }
        if (areEmpty(myGrid,tempLocs))
        {
            removeBlocks();
            addToLocations(myGrid,tempLocs);
            return true;
        }
        return false;
    }

    /**
     * Rotates the tetrad clockwise 90 degrees around the first block of the tetrad,
     * if the location is valid and unoccupied.
     * 
     * @return true if rotated; otherwise false
     *   
     */
    public boolean rotate()
    {
        if (blocks[0].getColor().equals(Color.CYAN))
            return false;
        Location[] tempLocs = new Location[4];
        tempLocs[0] = new Location(myLocs[0].getRow(), myLocs[0].getCol());
        for (int i = 1; i < myLocs.length; i++)
        {
            tempLocs[i] = new Location(myLocs[0].getRow()-myLocs[0].getCol()+myLocs[i].getCol(),
                myLocs[0].getRow()+myLocs[0].getCol()-myLocs[i].getRow());
        }
        if (areEmpty(myGrid,tempLocs))
        {
            removeBlocks();
            addToLocations(myGrid,tempLocs);
            return true;
        }
        return false;
    }
}

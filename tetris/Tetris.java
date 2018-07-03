import java.util.ArrayList;
/**
 * Main operator of Tetris game.
 * 
 * @author Emily Zhou 
 * @version 1/2/18
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad td;

    /**
     * Constructor for objects of class Tetris
     */
    public Tetris() 
    {
        grid = new MyBoundedGrid<Block>(20,10);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Tetris");
        td = new Tetrad(grid);
        display.showBlocks();
    }

    /**
     * Responses to the up arrow.
     */
    public void upPressed()
    {
        //td.translate(-1,0);
        td.rotate();
        display.showBlocks();
    }

    /**
     * Responses to the down arrow.
     */
    public void downPressed()
    {
        td.translate(1,0);
        display.showBlocks();
    }

    /**
     * Responses to the left arrow.
     */
    public void leftPressed()
    {
        td.translate(0,-1);
        display.showBlocks();
    }

    /**
     * Responses to the right arrow.
     */
    public void rightPressed()
    {
        td.translate(0,1);
        display.showBlocks();
    }

    /**
     * Determines if a given row is occupied.
     * 
     * @param row the row to check the occupied locations
     * @precondition  row is in the range of [0, number of rows)
     * @return  true if the row has been filled; otherwise
     *          false
     */
    private boolean isCompletedRow(int row)
    {
        for (int c = 0; c < grid.getNumCols(); c++)
        {
            Location loc = new Location(row,c);
            if (grid.get(loc)==null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes every block in a given row and every block above the row has been moved down one row.
     * 
     * @param row the row to clear blocks from
     * @precondition the row is filled with blocks and row is in the range of [0, number of rows).
     */
    private void clearRow(int row)
    {
        for (int c = 0; c < grid.getNumCols(); c++)
        {
            Location loc = new Location(row,c);
            Block bl = grid.get(loc);
            bl.removeSelfFromGrid();
        }
        for (int r = row-1; r >= 0; r--)
        {
            for (int c = 0; c < grid.getNumCols(); c++)
            {
                Location loc = new Location(r,c);
                if (grid.get(loc)!=null)
                {
                    Location newLoc = new Location(r+1,c);
                    grid.get(loc).moveTo(newLoc);
                }   
            }
        }
    }

    /**
     * Clears all completed rows.
     */
    private void clearCompletedRows()
    {
        for (int r = 0; r < grid.getNumRows(); r++)
        {
            if (isCompletedRow(r))
            {
                clearRow(r);
            }
        }
    }

    /**
     * Pauses for one seccond and translates the tetrad down one unit and redraws display.
     */
    public void play()
    { 
        while (true)
        {
            try 
            {
                //Pause for 1000 milliseconds
                Thread.sleep(1000);
                if (td.translate(1,0))
                {
                    display.showBlocks();
                }
                else
                {
                    clearCompletedRows();
                    td = new Tetrad(grid);
                    display.showBlocks();
                }
            }
            catch(InterruptedException e)
            {
                //ignore
            }
        }
    }

    /**
     * Runs a game of Tetris.
     * 
     * @param args ignored
     */
    public static void main(String[] args) throws Exception
    {
        Tetris ts = new Tetris();
        ts.play();
    }

}

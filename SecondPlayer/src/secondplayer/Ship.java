package secondplayer;

import java.util.*;

/**
 *
 * @author mingleee
 */
class Ship {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    
    Ship(int x, int y, int length, int position) {
        for(int i = 0; i < length; i++) {
            cells.add(
                    new Cell(x + i *((position == 1)? 0 : 1),
                    y + i * ((position == 1) ? 1 : 0)));
        }
    }
    
    boolean isOutOfField(int bottom, int top) {
        for(Cell cell : cells) {
            if(cell.getX() < bottom || cell.getX() > top || 
               cell.getY() > top || cell.getY() < bottom )
                return true;
        }
        return false;
    }
    
    // в методе проверяется не касается ли корабль других кораблей
    boolean isOverlayOrTouch(Ship ctrlShip) { 
        for (Cell cell : cells)
            if (ctrlShip.isOverlayOrTouchCell(cell))
                return true;
        return false;
    }

    // в методе проверяется не касается ли корабль других клеток
    boolean isOverlayOrTouchCell(Cell ctrlCell) {
        for (Cell cell : cells)
            for (int dx = -1; dx < 2; dx++)
                for (int dy = -1; dy < 2; dy++)
                    if (ctrlCell.getX() == cell.getX() + dx &&
                        ctrlCell.getY() == cell.getY() + dy)
                        return true;
        return false;
    }
    
    boolean checkHit(int x, int y) {
        for (Cell cell : cells) {
            if(cell.checkHit(x, y))
                return true;
        }
        return false;
    }
    
    boolean isAlive() {
        for (Cell cell : cells) {
            if(cell.isAlive())
                return true;
        }
        return false;
    }
}

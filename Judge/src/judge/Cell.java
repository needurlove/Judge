package judge;

/**
 *
 * @author mingleee
 */
public class Cell {
    private int x,y;
    boolean alive;
    
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        alive = true;
    }
    
    int getX() {
        return x;
    }
    
    int getY() {
        return y;
    }
    
    boolean isAlive() {
        return alive;
    }
    
    boolean checkHit(int x, int y) {
        if(this.x == x && this.y == y) return true;
        return false;
    }
    
}

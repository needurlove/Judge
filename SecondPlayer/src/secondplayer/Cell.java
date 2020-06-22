package secondplayer;

/**
 *
 * @author mingleee
 */
class Cell {
    private int x,y;
    private String ch;
    
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        ch = "*";
    }
    
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    String getCh() {
        return ch;
    }
    
    boolean checkHit(int x, int y) {
        if(this.x == x && this.y == y) {
            ch = "/";
            return true;
        }
        return false;
    }
    
    boolean isAlive() {
        return ch != "/";
    }
}

package secondplayer;

import java.util.*;

/**
 *
 * @author mingleee
 */
class Ships {
    ArrayList<Ship> ships = new ArrayList<Ship>();
    private final int[] PATTERN = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; 
    private Random random;
    
    Ships(int fieldSize) {
        random = new Random();
        for (int i = 0; i < PATTERN.length; i++) {
            Ship ship;
            do {
                int x = random.nextInt(fieldSize);
                int y = random.nextInt(fieldSize);
                int position = random.nextInt(2);
                ship = new Ship(x, y, PATTERN[i], position);
            } while (ship.isOutOfField(0, fieldSize - 1) || isOverlayOrTouch(ship));
            ships.add(ship);
        }
    }
    
    boolean isOverlayOrTouch(Ship ctrlShip) {
        for(Ship ship : ships) {
            if (ship.isOverlayOrTouch(ctrlShip)) {
                return true;
            }           
        }
         return false;
    }
       
    boolean checkHit(int x, int y) {
        for(Ship ship : ships) {
            ship.checkHit(x, y);
            return true;
        }
        return false;
    }
    
    boolean checkSurvivors() {
        for(Ship ship : ships) {
            if(ship.isAlive());
            return true;
        }
        return false;
    }
}

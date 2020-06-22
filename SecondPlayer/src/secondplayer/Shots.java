package secondplayer;

import java.util.ArrayList;

class Shots {
    private ArrayList<Shot> shots;

    Shots() {
        shots = new ArrayList<Shot>();
    }

    void add(int x, int y, boolean shot, boolean alive) {
        shots.add(new Shot(x, y, shot, alive));
    }

    boolean checkStatus(int x, int y) {
        for(Shot shot : shots) 
            if(shot.isAlive())
                return true;
        return false;
        
    }
    
    boolean hitSamePlace(int x, int y) {
        for (Shot shot : shots)
            if (shot.getX() == x && shot.getY() == y && shot.isShot())
                return true;
        return false;
    }

    Shot getLabel(int x, int y) {
        for (Shot label : shots)
            if (label.getX() == x && label.getY() == y && (!label.isShot()))
                return label;
        return null;
    }
}

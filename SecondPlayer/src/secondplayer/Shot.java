package secondplayer;

class Shot {
    private int x, y;
    private boolean shot, alive;

    Shot(int x, int y, boolean shot, boolean alive) {
        this.x = x;
        this.y = y;
        this.shot = shot;
        this.alive = alive;
    }

    int getX() { return x; }
    int getY() { return y; }
    boolean isShot() { return shot; }
    boolean isAlive() { return alive; }

}

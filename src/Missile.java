public class Missile extends Character {

    private final int BOARD_WIDTH = 800;
    private final int BOARD_HEIGHT = 800;
    private final int MISSILE_SPEED = 10;
    private int dir=0;

    public Missile(int x, int y,int fdir) {
        super(x, y);
        this.dir=fdir;
        initMissile();
    }

    private void initMissile() {

        if(dir%2==0)loadImage("src/resources/missile.png");
        else loadImage("src/resources/missile1.png");
        getImageDimensions();
    }

    public void move() {
        if(dir==0){
            x += MISSILE_SPEED;
            if (x > BOARD_WIDTH)
                visible = false;
        }
        else if(dir==1){
            y -= MISSILE_SPEED;
            if (y < 0)
                visible = false;
        }
        else if(dir==2){
            x -= MISSILE_SPEED;
            if (x <0)
                visible = false;
        }
        else if(dir==3){
            y += MISSILE_SPEED;
            if (y > BOARD_HEIGHT)
                visible = false;
        }
    }
}


import java.util.ArrayList;
import java.util.List;

public class player extends Character {

    private int dx;
    private int dy,f_dir=0;
    private List<Missile> missiles;

    public player(int x, int y) {
        super(x, y);

        initCraft();
    }
    private void initCraft() {

        missiles = new ArrayList<Missile>();
        loadImage("src/resources/spaceship.png");
        getImageDimensions();
    }

    public void checkmove() {

        System.out.println(x);
        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }
        if(x>795)
            x=795;

        if (y < 1) {
            y = 1;
        }
        if (y > 795) {
            y = 795;
        }

    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void fire(int f_dir) {
        if(f_dir==0)missiles.add(new Missile(x + width, y + height / 2,f_dir));
        else if(f_dir==1)missiles.add(new Missile(x + width/2, y,f_dir));
        else if(f_dir==2)missiles.add(new Missile( x , y + height/2 ,f_dir));
        else if(f_dir==3)missiles.add(new Missile(x + width/2, y + height ,f_dir));
    }

}

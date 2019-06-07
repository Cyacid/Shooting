import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/*public class player extends Character {
    private int dx;
    private int dy;
    private List<> missiles;
    public player(int x,int y){
        super(x,y);
        inicraft();
    }
    private void inicraft(){
        missiles = new ArrayList<>();
        loadImage("src/resources/spaceship.png");
        getImageDimensions();
    }
    public List<Missile> getMissiles() {
        return missiles;
    }

}*/


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

    /*public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if(key== KeyEvent.VK_W)
            f_dir=1;
        else if(key== KeyEvent.VK_D)
            f_dir=0;
        else if(key== KeyEvent.VK_A)
            f_dir=2;
        else if(key== KeyEvent.VK_S)
            f_dir=3;

        if (key == KeyEvent.VK_SPACE) {
            fire();
            fired=true;
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -3;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 3;
        }
    }*/

    public void fire(int f_dir) {
        if(f_dir==0)missiles.add(new Missile(x + width, y + height / 2,f_dir));
        else if(f_dir==1)missiles.add(new Missile(x + width/2, y,f_dir));
        else if(f_dir==2)missiles.add(new Missile( x , y + height/2 ,f_dir));
        else if(f_dir==3)missiles.add(new Missile(x + width/2, y + height ,f_dir));
    }

   /* public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }*/
}

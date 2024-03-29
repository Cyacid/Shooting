import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Character {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int hp,mp;
    protected boolean visible;
    protected Image image;


    public  Character(int x,int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }

        protected void getImageDimensions(){

            width = image.getWidth(null);
            height = image.getHeight(null);

        }

        protected void loadImage(String imageName) {

            ImageIcon ii = new ImageIcon(imageName);
            image = ii.getImage();
        }

        public Image getImage() {
            return image;
        }

        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x=x;
        }
        public void setY(int y) {
            this.y=y;
        }
        public int getY() {
            return y;
        }
        public void setHp(int hp)
        {
            this.hp=hp;
        }
        public int getHp()
        {
            return hp;
        }
        public void setMp(int mp)
        {
            this.mp=mp;
        }
        public int getMp()
        {
            return mp;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(Boolean visible) {
            this.visible = visible;
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
    }


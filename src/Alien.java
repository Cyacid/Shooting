
public class Alien extends Character {

    private final int INITIAL_X = 400;
    private int cnt=0,speed=0,dirx,diry,level=-1;
    private boolean finalwave=false;

    public Alien(int x, int y,int level,boolean finalwave) {
        super(x, y);
        this.level=level;
        this.finalwave=finalwave;
        initAlien();
    }

    private void initAlien() {
        loadImage("src/resources/alien.png");

        getImageDimensions();
        if(level==1)
            setHp(1);
        else if(level==2)
            setHp(2);
    }


    public void move() {
        cnt++;
        if(cnt==1000){
            cnt=0;
            speed++;
        }
        if(speed==1){
            speed=0;
        }
        //if(dir % 4==0 ){
            if(x<=0)
            dirx=1;
        else if(x>=800)
            dirx=0;
        if(y<=0)
            diry=1;
        else if(y>=800)
            diry=0;


           if(dirx==1)
               x+=1+speed;
           else
               x-=1+speed;
            if(diry==1)
                y+=1+speed;
            else
                y-=1+speed;


        //}
        /*else if(dir % 4==1){
            if(x<0)
                x=1000;
            else
                x -= (1+speed);
            if(y<0)
                y=850;
            else
                y -=(1+speed);
        }
        else if(dir % 4==2 ){
            if(x>810)
                x=-20;
            else
                x += (1+speed);
            if(y<0 )
                y=850;
            else
                y -=(1+speed);
        }
        else{
            if(x>1000)
                x=-200;
            else
                x += (1+speed);
            if(y>1000)
                y=-200;
            else
                y +=(1+speed);
        }*/

    }
}
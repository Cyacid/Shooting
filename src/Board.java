import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private player spaceship;
    private List<Alien> aliens;
    private boolean ingame,menu=true,fired=false,menu1=false,wingame=false,finalwave=false;
    private boolean x_skill=false,c_skill=false,v_skill=false;
    private final int ICRAFT_X = 200;
    private final int ICRAFT_Y = 200;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 800;
    private final int DELAY = 15;
    private int[][] pos=new int[80][2];
    private int i=0,j=0,f_dir=0,dx,dy,bullet_num=10,bul_cnt=0,level=-1,score=0,cntupdata=0,killnum=0,cntscore=0,c_cnt=300,world_cnt=200,cnt_move=0,x_cnt=200;



    public Board() {

        initMenu();
    }
    private void initMenu() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }
    private int getBullet_num(){return bullet_num;}
    private void initBoard() {
        aliens = new ArrayList<>();

        for( i =0 ;i<80;i++){
            for(j=0 ;j<2;j++){
                if(j%2==1)
                    pos[i][j]=i*10+1;
                else{
                    //pos[i][j]=1000;
                    if(i<=40)
                        pos[i][j]=1000+i*30;
                    else
                        pos[i][j]=3400-i*30;

                }
            }
        }


        createAliens();
        spaceship = new player(ICRAFT_X, ICRAFT_Y);
        if(level==2){
            spaceship.setHp(2);
            spaceship.setMp(10);
            bullet_num=20;
        }else if(level==1){
            spaceship.setHp(6);
            spaceship.setMp(10);
            bullet_num=30;
        }else if(level==0){
            spaceship.setHp(9);
            spaceship.setMp(30);
            bullet_num=20;
        }



        timer = new Timer(DELAY, this);
        timer.setDelay(3);
        System.out.println(timer.getDelay());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setXORMode(Color.black);
        if(menu)
            drawmenu(g);
        else if(menu1)
            drawlevel(g);
        else if (ingame)
            drawObjects(g);
        else if(!ingame && spaceship.getHp()<=0)
            drawGameOver(g);
        if(wingame)
            drawwin(g);



        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),Color.BLACK,
                    this);
        }

        List<Missile> ms = spaceship.getMissiles();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(),
                        missile.getY(), Color.BLACK,this);
            }
        }

        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), Color.BLACK,this);
            }
        }

        g.setColor(Color.RED);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
        g.drawString("Hp left: " + spaceship.getHp(), 5, 785);
        g.drawString("Mp left: " + spaceship.getMp(), 5, 800);
        g.drawString("Bullet left: " +getBullet_num() , 5, 770);
        g.drawString("Current Score: " +score , 5, 25);
        if(c_skill) g.drawString("Iron Body: " +c_cnt , 350, 15);
        if(x_skill) g.drawString("infinity: " +x_cnt , 250, 25);
        if(v_skill) g.drawString("bullet time: " +world_cnt , 450, 25);
    }

    private void drawmenu(Graphics g) {
        String msg = "Shooting";
        Font small = new Font("Helvetica", Font.BOLD, 28);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        msg="Press Z to Play";
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2+B_HEIGHT/4);

    }
    private void drawlevel(Graphics g) {
        String msg = "Select the difficulty";
        Font small = new Font("Helvetica", Font.BOLD, 32);
        Font levelfont = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 5);
        g.setFont(levelfont);
        msg="1.Rookie";
        g.drawString(msg, (B_WIDTH / 3),
                B_HEIGHT*4 / 10);
        msg="2.Normal";
        g.drawString(msg, (B_WIDTH  / 3),
                B_HEIGHT*5 / 10);
        msg="3.Specialist";
        g.drawString(msg, (B_WIDTH / 3),
                B_HEIGHT*6 / 10);
        msg="Press Z to Continue";
        g.drawString(msg, (B_WIDTH / 3),
                B_HEIGHT*9 / 10);

        if(level==0)
            g.drawString("Current difficulty: Rookie", (B_WIDTH) / 3,
                    B_HEIGHT*8 / 10);
        if(level==1)
            g.drawString("Current difficulty: Normal", (B_WIDTH) / 3,
                    B_HEIGHT*8 / 10);
        if(level==2)
            g.drawString("Current difficulty: Specialist", (B_WIDTH) / 3,
                    B_HEIGHT*8 / 10);

    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 32);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        msg = "Press R to Restart";
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2+B_HEIGHT/4);
        g.drawString("You Score:"+score, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2-B_HEIGHT/4);
    }
    private void drawwin(Graphics g) {

        String msg = "You Win!";
        Font small = new Font("Helvetica", Font.BOLD, 32);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        msg = "Press R to Restart";
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2+B_HEIGHT/4);
        g.drawString("You Score:"+score, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2-B_HEIGHT/4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        if(killnum<80) {
            if (aliens.size() < 30)
                createAliens();
        }
        else if(!finalwave)
        {
            finalwave=true;
            for(int i=0 ; i<80;i++){
                if(i<20)
                   aliens.add(new Alien(-200,i*80+10,level,finalwave));
                else if(i<40)
                    aliens.add(new Alien(1000,(i-20)*80+10,level,finalwave));
                else if(i<60)
                    aliens.add(new Alien((i-40)*80+10,-200,level,finalwave));
                else if(i<80)
                    aliens.add(new Alien((i-60)*80+10,1000,level,finalwave));
            }
        }
        cntscore++;
        if(cntscore>=40){
            score++;
            cntscore=0;
        }
        if(v_skill){
            if(world_cnt==0){
                world_cnt=200;
                v_skill=false;
            }
            else{
                world_cnt--;
                if(cnt_move==5){
                    updateAliens();
                    cnt_move=0;
                }
                else
                    cnt_move++;
            }
        }
        else
            updateAliens();

        if(cntupdata==3){
            updateShip();
            updateMissiles();
            cntupdata=0;
            if(x_skill){
                x_cnt--;
                if(x_cnt==0)
                    x_skill=false;
            }
            else if(bullet_num<100){
                bul_cnt++;
                bullet_num=bullet_num+bul_cnt/20;
                bul_cnt=bul_cnt%20;
            }
            if(c_skill)
                if(c_cnt==0){
                    c_skill=false;
                    c_cnt=300;
                }
                else
                    c_cnt--;
            checkCollisions();
        }
        cntupdata++;




        repaint();
    }
    private void createAliens(){

       // for (int[] p ;p) {
           // aliens.add(new Alien(p[0], p[1],level));}

            aliens.add(new Alien(pos[(int)(Math.random()*80)][0], pos[(int)(Math.random()*80)][1],level,finalwave));
            //aliens.add(new Alien(pos[70][0], pos[70][1],level));*/

    }
    private void inGame() {

        if (!ingame || wingame) {
            ingame=false;
            timer.stop();
        }
    }

    private void updateShip() {

        if (spaceship.isVisible()) {
            spaceship.setX(spaceship.getX()+dx);
            spaceship.setY(spaceship.getY()+dy);
            spaceship.checkmove();
        }
    }

    private void updateMissiles() {

        List<Missile> ms = spaceship.getMissiles();


        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAliens() {

        int s_x=spaceship.getX();
        int s_y=spaceship.getY();

        if (aliens.isEmpty() && finalwave) {

            wingame = true;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            //System.out.println(aliens.get(i).getX());


            if (a.isVisible()) {


                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = spaceship.getBounds();
        Rectangle r4=new Rectangle(spaceship.getX()+spaceship.width,spaceship.getY()+spaceship.height,1,1);


        for (Alien alien : aliens) {

            Rectangle r2 = alien.getBounds();
            if(r2.intersects(r4)){
                if(spaceship.getHp()==0){
                    spaceship.setVisible(false);
                    alien.setVisible(false);
                    ingame = false;
                }
                else
                {
                    if(!c_skill)
                        spaceship.setHp(spaceship.getHp()-1);
                    alien.setVisible(false);
                }
            }
            if (r3.intersects(r2)) {
                score+=10;
            }
        }

        List<Missile> ms = spaceship.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Alien alien : aliens) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    if( alien.getHp()==0){
                        alien.setVisible(false);
                        spaceship.setMp(spaceship.getMp()+1);
                        score+=50;
                        killnum++;
                    }
                    else
                        alien.setHp(alien.getHp()-1);
                }
            }
        }
    }
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            int key=e.getKeyCode();
            if(fired)
                fired=false;
            if (key == KeyEvent.VK_LEFT) {
                dx=0;
            }
            if(key==KeyEvent.VK_X && spaceship.getMp()>=30 && !x_skill)
            {
                spaceship.setMp(spaceship.getMp()-30);
                bullet_num=1;
                x_cnt=200;
                x_skill=true;
            }
            if(key==KeyEvent.VK_C && spaceship.getMp()>=40 && !c_skill)
            {
                spaceship.setMp(spaceship.getMp()-40);
                c_cnt=300;
                c_skill=true;
            }
            if(key==KeyEvent.VK_V && spaceship.getMp()>=30 && !v_skill)
            {
                spaceship.setMp(spaceship.getMp()-30);
                world_cnt=200;
                v_skill=true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                dx=0;
            }

            if (key == KeyEvent.VK_UP) {
                dy=0;
            }

            if (key == KeyEvent.VK_DOWN) {
                dy=0;
            }
            if( key == KeyEvent.VK_Z && menu) {
                //initBoard();
                menu=false;
                menu1=true;
                repaint();
            }
            if( key == KeyEvent.VK_Z && menu1&&level>=0) {
                initBoard();
                score=0;
                ingame=true;
                menu1=false;
            }
            if(key == KeyEvent.VK_R && !ingame){
                score=0;
                killnum=0;
                menu=true;
                level=-1;
                finalwave=false;
                wingame=false;
                repaint();
            }
            if(key == KeyEvent.VK_1 && menu1){
                level=0;
                repaint();
            }
            if(key == KeyEvent.VK_2 && menu1){
                level=1;
                repaint();
            }
            if(key == KeyEvent.VK_3 && menu1){
                level=2;
                repaint();
            }

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if(key== KeyEvent.VK_W)
                f_dir=1;
            else if(key== KeyEvent.VK_D)
                f_dir=0;
            else if(key== KeyEvent.VK_A)
                f_dir=2;
            else if(key== KeyEvent.VK_S)
                f_dir=3;

            if (key == KeyEvent.VK_SPACE && bullet_num!=0) {
                spaceship.fire(f_dir);
                if(!x_skill)
                    bullet_num--;
            }

            if (key == KeyEvent.VK_LEFT) {
                dx=-3;
            }
            if (key == KeyEvent.VK_RIGHT) {
                dx=3;
            }
            if (key == KeyEvent.VK_UP) {
                dy=-3;
            }
            if (key == KeyEvent.VK_DOWN) {
                dy=3;
            }
        }
    }
}

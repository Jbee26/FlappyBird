import java.awt.*;

public class Gorillas {


    public Image pic;
    public int dx = 2;
    public int dy = 2;
    public int xpos;
    public int ypos = 0;
    public int width = 150;
    public int height = (int)(Math.random()*300+50 ) ;
    public Rectangle recU; 

    public Gorillas(int paramxpos, int paramypos) {
        xpos = paramxpos;
        ypos = paramypos;

        recU = new Rectangle(xpos,ypos,width,height);

    }

    public void move(){

        xpos = xpos - dx;



        if (ypos < 0) {
            dy=-dy;
        }


        if (ypos > 700 - height) {
            dy=-dy;
        }

        if (xpos < 0) {
            xpos = xpos + 10000;
        }

        recU = new Rectangle(xpos,ypos,width,height);


    }



}






import java.awt.*;

public class Gators {

    public Image apic;
    public int dx = 2;
    public int dy = 2;
    public int xpos;
    public int ypos = 1000;
    public int width = 100;
    public int height = (int)(Math.random()*401 ) ;
    public static Rectangle recD;
    public Image pic;

    public Gators(int paramxpos, int paramypos) {
        xpos = paramxpos;
        ypos = paramypos;
        recD = new Rectangle(xpos,ypos,width,height);

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
            xpos = xpos + 1000;
        }

        recD = new Rectangle(xpos,ypos,width,height);

    }



}








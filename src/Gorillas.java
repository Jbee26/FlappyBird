import java.awt.*;

public class Gorillas {


        public Image pic;
        public int dx = 2;
        public int dy = 2;
        public int xpos;
        public int ypos = 0;
        public int width = 100;
        public int height = (int)(Math.random()*351 ) ;
        public Rectangle rec;

        public Gorillas(int paramxpos, int paramypos) {
            xpos = paramxpos;
            ypos = paramypos;
            rec = new Rectangle(xpos,ypos,width,height);

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








            }



        }






import java.awt.*;

public class Bird {

        public int xpos;
        public int ypos;
        public boolean isAlive;
        public int dx = 2;
        public int dy = 2;
        public int width = 100;
        public int height = 100;
        public String name;
        public Rectangle rec;
        public boolean upIsPressed;
        public boolean downIsPressed;
        public boolean leftIsPressed;
        public boolean rightIsPressed;


        public Bird(String paramName, int paramXpos, int paramypos) {
            name = paramName;
            xpos = paramXpos;
            ypos = paramypos;
            rec = new Rectangle(xpos,ypos,width,height);


        }

        public void move() {

            ypos = ypos + dy;



            if (upIsPressed == true) {
                ypos = ypos - 20;

            } else if (downIsPressed){
                ypos = ypos + dy;

            }if(leftIsPressed == true){
                xpos = xpos - dx;
            } else if(rightIsPressed == true){
                xpos = xpos + dx;
            }




            if (ypos < 0) {
                ypos = 0;
            }


            if (ypos > 700 - height) {
                ypos = 700 - height;
            }

            if (xpos < 0) {
                xpos = 0;
            }

            if (xpos > 1000 - width) {
                xpos = 1000 - width;




            }
            rec = new Rectangle(xpos,ypos,width,height);
        }

    }

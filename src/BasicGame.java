

//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class BasicGame implements Runnable, KeyListener  {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Bird birdo;
    public Image birdoPic;

    public Image Background;

    public Image gorilla;

    public Gorillas gorilla1;

    public Image gpic;


    public Gorillas [] branch;

    public Image Gator;

    public Gators gator1;

    public Image gapic;

    public Gators [] pond;

    public boolean gamePlaying;

    public boolean gameOver;

    public boolean isPaused;

    public SoundFile flying;





    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGame ex = new BasicGame();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGame() { // BasicGameApp constructor

        setUpGraphics();
        canvas.addKeyListener(this);
        birdo = new Bird("Birdo", 100,300);
        Background = Toolkit.getDefaultToolkit().getImage("jungle.jpeg");
        birdoPic = Toolkit.getDefaultToolkit().getImage("bird.png");

        gorilla1 = new Gorillas(50,50);
        gpic = Toolkit.getDefaultToolkit().getImage("gorilla.png");
        branch = new Gorillas[100];
        branch[0] = new Gorillas(50,50);
        gator1 = new Gators(50,800);
        gapic = Toolkit.getDefaultToolkit().getImage("gator.png");
        pond = new Gators[100];
        pond[0] = new Gators(50,700);
        flying = new SoundFile("Chickens in a Barn 01.wav");
        flying.play();



        for (int x=0; x<branch.length; x++) {
            branch[x] = new Gorillas(x*500+500, 0);
            branch[x].pic = Toolkit.getDefaultToolkit().getImage("gorilla.png");
        }


        for (int x=0; x<pond.length; x++) {
            pond[x] = new Gators(x*500+1000, 0);
            pond[x].pic = Toolkit.getDefaultToolkit().getImage("gator.png");
        }







        //variable and objects
        //create (construct) the objects needed for the game

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {

            if (gamePlaying == true && isPaused == false ) {

                moveThings();  //move all the game objects
                collisions();

            }
            render();  // paint the graphics
            pause(10 ); // sleep for 10 ms


        }
    }

    public void moveThings() {
        //call the move() code for each object
        birdo.move();
        for (int x = 0; x < 100; x++) {
            branch[x].move();
        }

        for (int x = 0; x < 100; x++) {
            pond[x].move();
        }

    }

    public void collisions() {
        for (int x = 0; x < pond.length; x++) {


            if (branch[x].recU.intersects(birdo.rec)) {
                birdo.isAliveG = false;
                birdo.ypos = 0;

            }

            if (pond[x].recD.intersects(birdo.rec)) {
                birdo.isAliveA = false;
                birdo.ypos = 700;

            }




        }

    }






    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (gamePlaying == false ) {
            g.setColor(Color.GREEN);
            g.fillRect(0,0,WIDTH, HEIGHT);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Times Roman", Font.PLAIN, 60));
            g.drawString("Press Return to Start!", 300,300);
        }

        else if (gamePlaying == true && gameOver == false) {

            g.drawImage(Background, 0, 0, 1000, 700, null);
            if (birdo.isAliveG) {
                g.drawImage(birdoPic, birdo.xpos, birdo.ypos, birdo.width, birdo.height, null);
            }

            for (int x = 0; x < branch.length; x++) {
                g.drawImage(branch[x].pic, branch[x].xpos, branch[x].ypos, branch[x].width, branch[x].height, null);
            }

            for (int x = 0; x < pond.length; x++) {
                g.drawImage(pond[x].pic, pond[x].xpos, pond[x].ypos, pond[x].width, pond[x].height, null);
                

            }

            if (birdo.isAliveG == false) {
                g.setColor(Color.GREEN);
                g.fillRect(0,0,WIDTH, HEIGHT);
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Times Roman", Font.PLAIN, 60));
                g.drawString("You Went Bannanas!", 300,100);
                g.drawString("Restart to Try Again", 300,500);


            }

            if (birdo.isAliveA == false) {
                g.setColor(Color.GREEN);
                g.fillRect(0,0,WIDTH, HEIGHT);
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Times Roman", Font.PLAIN, 60));
                g.drawString("Gone Fishin'!", 300,100);
                g.drawString("Restart to Try Again", 300,500);


            }


        }






        //draw the images

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.



        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyChar();
        System.out.println("Key pressed: " + key + ", Key code:" + keyCode);



    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyChar();
        System.out.println("Key pressed: " + key + ", Key code:" + keyCode);
        if (keyCode == 32) {
            for (int x = 0; x<100; x++) {

                birdo.ypos = birdo.ypos - 1;
                flying.play();
                pause(2);


            }

        }

        if (gamePlaying == false && keyCode == 10) {
            gamePlaying = true;

        }




    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


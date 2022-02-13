package farms4life2016.dataprocessing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

import farms4life2016.fileio.FileIO;
import farms4life2016.gui.StringDrawer;
import farms4life2016.gui.displays.MenuDisplay;
import farms4life2016.gui.displays.JobUpdateDialogue;

/**
 * 
 */
public class Controller { //TODO make a log-in page

    //I decided to go against my plans and use a linked list instead of an array list
    public static DLinkedList jobList = new DLinkedList();

    //gui things. make static and public so association becomes easier
    public static JFrame window;
    public static JobUpdateDialogue jobUpdater;
    public static MenuDisplay mainMenu;

    //window size
    public static final int WINDOW_W = 1200, WINDOW_H = 720; //50 px for the top bar thingy

    /**
     * I'm not sure we need a fancy constructor.
     * Since there is only 1 Controller in the entire program,
     * all methods/variables can be and should be static.
     */
    private Controller() {}

    /**
     * Start the program
     */
    public static void run() {

        initFromFile();
        initGUI();
        
    }

    /**
     * Exit the program
     */
    public static void terminate() {

        //write init file based on current job list
        saveToFile();

        //TODO ensure that all file IO tasks are done and closed safely before exiting
        System.exit(0);
    }

    private static void initFromFile() {
        FileIO.init(jobList);
    }

    public static void saveToFile() {
        FileIO.exit(jobList);
    }

    /**
     * Creates the GUI. Most of this method is copied from the GUI programs that I wrote in grade 10.
     */
    public static void initGUI() {
        
        //variables
        new StringDrawer();
        
        
        window = new JFrame("Data Porter"); //name it 
        mainMenu = new MenuDisplay(window); //make a new window
        Container c = window.getContentPane(); //make something to add all the things to
        jobUpdater = new JobUpdateDialogue(window, "big shot", true);      

        //set window size, cannot resize
        window.setSize(WINDOW_W, WINDOW_H);
        window.setResizable(false);
        
        //add panel to frame
        c.setLayout(new BorderLayout());
        c.add(mainMenu, BorderLayout.CENTER);
        mainMenu.setBackground(Color.GRAY);

        //why do we have to add this listener here I'm so confused
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        window.addWindowListener(mainMenu);
        
        //final touches to make window visible and usable
        window.setVisible(true);

    }

}
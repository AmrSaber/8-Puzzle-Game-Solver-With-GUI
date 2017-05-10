package pkg8.puzzle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Amr Saber
 * 
 * contains only the main method that starts the GUI, nothing is done here
 */
public class Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        //set the look and feel to system's look and feel, who likes java's native look and feel :"D
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //start the gui, let the magic begin :D
        GUI gui = new GUI();
        gui.setVisible(true);
        
    }
    
}

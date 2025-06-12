import javax.swing.SwingUtilities;

/**
 * SnakeGame - Main entry point for the Snake Game application.
 * 
 * This class contains the main method that initializes and launches the Snake game.
 * It follows the Model-View-Controller (MVC) pattern where this class acts as the
 * controller that creates the main game frame.
 * 
 * The Snake game is a classic arcade-style game where the player controls a snake
 * that grows in length as it consumes food items while avoiding collisions with
 * walls and its own body.
 * 
 * @author Mpho Matseka
 * @version 1.0
 * @since 2025
 */
public class SnakeGame {

    /**
     * Main entry point of the Snake Game application.
     * 
     * This method creates the main game window using the Event Dispatch Thread (EDT)
     * to ensure thread-safe GUI operations. Using SwingUtilities.invokeLater() is
     * the recommended approach for initializing Swing applications.
     * 
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater() to ensure GUI creation happens on EDT
        SwingUtilities.invokeLater(() -> {
            try {
                // Create and display the main game window
                new GameFrame();
            } catch (Exception e) {
                System.err.println("Error initializing Snake Game: " + e.getMessage());

            }
        });
    }
}

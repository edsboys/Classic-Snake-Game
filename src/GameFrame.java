import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * GameFrame class extends JFrame to create the main window for the Snake Game.
 * 
 * This class is responsible for setting up the game window properties,
 * including size, position, title, and behavior. It follows the MVC pattern
 * as the View component that contains the game panel.
 * 
 * The frame is configured to be non-resizable and centered on the screen
 * for optimal gameplay experience.
 * 
 * @author Mpho Matseka
 * @version 1.0
 * @since 2025
 */
public class GameFrame extends JFrame {

    private static final String GAME_TITLE = "Snake Game - Classic Arcade";
    private static final String GAME_VERSION = "v1.0";
    private GamePanel gamePanel;
    
    /**
     * Constructor initializes the game frame with all necessary properties.
     * 
     * Sets up the window configuration including:
     * - Game panel integration
     * - Window title and icon
     * - Size and position settings
     * - Close operation behavior
     * - Visibility and focus settings
     */
    GameFrame() {
        initializeFrame();
        setupGamePanel();
        configureWindow();
        finalizeFrame();
    }
    
    /**
     * Initializes basic frame properties and behavior.
     */
    private void initializeFrame() {
        // Set window title with version info
        this.setTitle(GAME_TITLE + " " + GAME_VERSION);
        
        // Configure close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Prevent window resizing for consistent gameplay
        this.setResizable(false);
    }
    
    /**
     * Sets up and adds the game panel to the frame.
     */
    private void setupGamePanel() {
        try {
            // Create the main game panel
            gamePanel = new GamePanel();
            this.add(gamePanel);
        } catch (Exception e) {
            // Handle potential game panel creation errors
            JOptionPane.showMessageDialog(this, 
                "Error initializing game panel: " + e.getMessage(),
                "Initialization Error", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    /**
     * Configures window size and layout properties.
     */
    private void configureWindow() {
        // Adjust frame size to fit the content perfectly
        this.pack();
        
        // Center the frame on the screen
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Finalizes frame setup and makes it visible.
     */
    private void finalizeFrame() {
        // Make the frame visible
        this.setVisible(true);
        
        // Ensure proper focus for key input
        if (gamePanel != null) {
            gamePanel.requestFocusInWindow();
        }
    }
}

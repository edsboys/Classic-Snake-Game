import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * GamePanel class implements the main game logic for the Snake game.
 * This class extends JPanel to provide custom graphics rendering and
 * implements ActionListener to handle timer events for game updates.
 * 
 * @author Mpho Matseka
 * @version 1.0
 */
public class GamePanel extends JPanel implements ActionListener {

    // Game configuration constants
    static final int SCREEN_WIDTH = 600;           // Width of the game window in pixels
    static final int SCREEN_HEIGHT = 600;          // Height of the game window in pixels
    static final int UNIT_SIZE = 25;               // Size of each game unit (snake segments and apple)
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE; // Total possible units on screen
    static final int INITIAL_DELAY = 100;          // Initial timer delay in milliseconds
    static final int MIN_DELAY = 50;               // Minimum delay (maximum speed)
    
    // Snake position arrays - store coordinates for each body segment
    final int x[] = new int[GAME_UNITS];          // X coordinates of snake segments
    final int y[] = new int[GAME_UNITS];          // Y coordinates of snake segments
    
    // Game state variables
    int bodyParts = 6;                             // Initial number of snake body parts
    int applesEaten;                               // Player's score (number of apples consumed)
    int appleX;                                    // X coordinate of the current apple
    int appleY;                                    // Y coordinate of the current apple
    char direction = 'R';                          // Current movement direction (R=Right, L=Left, U=Up, D=Down)
    boolean running = false;                       // Game state flag
    Timer timer;                                   // Swing timer for game loop
    Random random;                                 // Random number generator for apple placement
    int currentDelay = INITIAL_DELAY;              // Current game speed delay

    /**
     * Constructor initializes the game panel with default settings
     * and starts the game immediately.
     */
    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK); // Changed to black for better contrast
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    /**
     * Initializes and starts a new game session.
     * Sets up the timer, places the first apple, and begins the game loop.
     */
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(currentDelay, this);
        timer.start();
    }

    /**
     * Override paintComponent to provide custom graphics rendering.
     * This method is called automatically by Swing when the panel needs to be redrawn.
     * 
     * @param g Graphics object used for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Handles all graphics rendering for the game.
     * Draws the apple, snake, score, and game over screen as appropriate.
     * 
     * @param g Graphics object for drawing operations
     */
    private void draw(Graphics g) {
        if (running) {
            // Draw grid lines for better visual reference (optional)
            drawGrid(g);
            
            // Draw the apple
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Draw the snake with color change after 50 apples
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    // Snake head - changes color after 50 apples
                    if (applesEaten >= 50) {
                        g.setColor(new Color(0, 100, 0)); // Dark green for head after 50 apples
                    } else {
                        g.setColor(new Color(0, 255, 0)); // Bright green for head initially
                    }
                } else {
                    // Snake body - changes color after 50 apples
                    if (applesEaten >= 50) {
                        g.setColor(new Color(0, 80, 0)); // Darker shade for body after 50 apples
                    } else {
                        g.setColor(new Color(45, 180, 45)); // Original darker green for body
                    }
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Draw current score and speed level
            drawScore(g);
        } else {
            // Game is over - display game over screen
            gameOver(g);
        }
    }

    /**
     * Draws a grid overlay to help visualize game units (optional feature).
     * 
     * @param g Graphics object for drawing
     */
    private void drawGrid(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        // Draw vertical lines
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
        }
        // Draw horizontal lines
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
    }

    /**
     * Draws the current score and speed level on the game screen.
     * 
     * @param g Graphics object for drawing
     */
    private void drawScore(Graphics g) {
        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Inky Free", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, 
                    (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, 
                    g.getFont().getSize());
        
        // Draw speed level indicator
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        FontMetrics speedMetrics = getFontMetrics(g.getFont());
        String speedText = "Speed: " + (INITIAL_DELAY - currentDelay + MIN_DELAY) + "/" + (INITIAL_DELAY - MIN_DELAY + MIN_DELAY);
        g.drawString(speedText, 10, SCREEN_HEIGHT - 10);
        
        // Draw color change indicator
        if (applesEaten >= 50) {
            g.setColor(Color.CYAN);
            String colorText = "DARK MODE ACTIVATED!";
            g.drawString(colorText, SCREEN_WIDTH - speedMetrics.stringWidth(colorText) - 10, SCREEN_HEIGHT - 10);
        }
    }

    /**
     * Updates snake position based on current direction.
     * Moves each body segment to the position of the segment in front of it,
     * then moves the head in the current direction.
     */
    public void move() {
        // Move body segments - each segment takes the position of the one ahead
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Move head based on current direction
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    /**
     * Generates a new apple at a random location on the game grid.
     * Ensures the apple position aligns with the game grid.
     */
    public void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    /**
     * Checks if the snake head has collided with an apple.
     * If collision detected, increases score, grows snake, spawns new apple,
     * and updates game speed every 10 apples.
     */
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
            
            // Increase speed every 10 apples until minimum delay is reached
            if (applesEaten % 10 == 0 && currentDelay > MIN_DELAY) {
                currentDelay -= 5;
                if (currentDelay < MIN_DELAY) {
                    currentDelay = MIN_DELAY;
                }
                // Update timer with new delay
                timer.setDelay(currentDelay);
            }
        }
    }

    /**
     * Checks for collision conditions that end the game:
     * - Snake head hitting its own body
     * - Snake head hitting any wall/border
     */
    public void checkCollision() {
        // Check if head collides with body segments
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        // Check border collisions
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        // Stop the game timer if collision detected
        if (!running) {
            timer.stop();
        }
    }

    /**
     * Renders the game over screen with final score and game over message.
     * 
     * @param g Graphics object for drawing
     */
    public void gameOver(Graphics g) {
        // Display final score
        g.setColor(Color.RED);
        g.setFont(new Font("Inky Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Final Score: " + applesEaten, 
                    (SCREEN_WIDTH - metrics1.stringWidth("Final Score: " + applesEaten)) / 2, 
                    g.getFont().getSize());

        // Display "Game Over" message
        g.setColor(Color.RED);
        g.setFont(new Font("Inky Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", 
                    (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, 
                    SCREEN_HEIGHT / 2);

        // Display restart instruction
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        String restartKey = KeyEvent.getKeyText(KeyEvent.VK_R);
        String restartMessage = "Press " + restartKey + " to restart";
        g.drawString(restartMessage,
                    (SCREEN_WIDTH - metrics3.stringWidth(restartMessage)) / 2,
                    SCREEN_HEIGHT / 2 + 100);
    }

    /**
     * ActionListener implementation - called by the game timer.
     * Updates game state, checks for collisions, and triggers screen refresh.
     * 
     * @param e ActionEvent from the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollision();
        }
        repaint(); // Trigger screen redraw
    }

    /**
     * Inner class to handle keyboard input for game controls.
     * Supports WASD keys for movement and prevents reverse direction moves.
     */
    public class MyKeyAdapter extends KeyAdapter {

        /**
         * Handles key press events for game control.
         * 
         * @param e KeyEvent containing information about the pressed key
         */
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_R:
                    if (!running) {
                        restartGame();
                    }
                    break;
            }
        }
    }

    /**
     * Restarts the game by resetting all game state variables.
     */
    private void restartGame() {
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        currentDelay = INITIAL_DELAY; // Reset speed to initial value
        
        // Reset snake position
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        
        startGame();
    }
}

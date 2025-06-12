# 🐍 Snake Game

A classic Snake game implementation in Java using Swing GUI framework. Guide your snake to eat apples, grow longer, and avoid collisions while the game gets progressively faster!

## 🎮 Game Features

- **Progressive Difficulty**: Game speed increases every 10 apples eaten
- **Visual Rewards**: Snake changes to dark green color after eating 50 apples
- **Score Tracking**: Real-time score display with final score on game over
- **Speed Indicator**: Shows current game speed level
- **Restart Functionality**: Press 'R' to restart after game over
- **Smooth Controls**: Responsive WASD and arrow key controls
- **Grid Visualization**: Optional grid overlay for better gameplay reference

## 🕹️ How to Play

### Controls
- **W / ↑**: Move Up
- **A / ←**: Move Left  
- **S / ↓**: Move Down
- **D / →**: Move Right
- **R**: Restart game (when game over)

### Objective
1. Control the snake to eat red apples
2. Each apple increases your score and snake length
3. Avoid hitting walls or the snake's own body
4. Try to reach 50+ apples to unlock dark mode!

### Game Mechanics
- **Speed Boost**: Every 10 apples eaten increases game speed
- **Color Change**: After 50 apples, snake turns dark green
- **Maximum Speed**: Game caps at maximum difficulty for fair play

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (VS Code, IntelliJ IDEA, Eclipse) or command line

### Installation & Running

#### Option 1: Using VS Code
1. Clone or download this repository
2. Open the project folder in VS Code
3. Ensure Java Extension Pack is installed
4. Run the `SnakeGame.java` file

#### Option 2: Command Line
```bash
# Navigate to the src directory
cd src

# Compile the Java files
javac *.java

# Run the game
java SnakeGame
```

#### Option 3: Using IDE
1. Import the project into your preferred Java IDE
2. Run the `SnakeGame` class

## 📁 Project Structure

```
Snake/
├── src/
│   ├── SnakeGame.java      # Main entry point
│   ├── GameFrame.java      # Game window setup
│   └── GamePanel.java      # Game logic and rendering
├── bin/                    # Compiled output files
├── lib/                    # Dependencies (if any)
└── README.md              # This file
```

## 🛠️ Technical Details

### Architecture
- **MVC Pattern**: Separation of game logic, display, and controls
- **Event-Driven**: Uses Swing Timer for game loop
- **Thread-Safe**: Proper EDT usage for GUI operations

### Key Classes
- `SnakeGame`: Main class and entry point
- `GameFrame`: JFrame setup and window configuration  
- `GamePanel`: Core game logic, rendering, and input handling

### Game Constants
- **Screen Size**: 600x600 pixels
- **Unit Size**: 25 pixels per game unit
- **Initial Speed**: 100ms delay
- **Maximum Speed**: 50ms delay
- **Speed Increase**: Every 10 apples eaten

## 🎯 Gameplay Tips

1. **Plan Your Path**: Think ahead to avoid trapping yourself
2. **Use the Walls**: Navigate close to edges for more space
3. **Speed Management**: Game gets harder - stay focused!
4. **Milestone Goals**: 
   - Reach 10 apples for first speed boost
   - Reach 50 apples for color change
   - Reach 100+ apples for ultimate challenge

## 🏆 High Score Challenge

Can you reach these milestones?
- 🥉 **Bronze**: 25 apples
- 🥈 **Silver**: 50 apples (Dark Mode!)
- 🥇 **Gold**: 100 apples
- 💎 **Diamond**: 150+ apples

## 🐛 Known Issues

- None currently reported

## 🤝 Contributing

Feel free to fork this project and submit pull requests for:
- Bug fixes
- Feature enhancements
- Code optimizations
- Documentation improvements

## 📝 License

This project is created for educational purposes. Feel free to use and modify as needed.

## 👨‍💻 Author

**Mpho Matseka**
- Version: 1.0
- Year: 2025

## 🙏 Acknowledgments

- Classic Snake game concept
- Java Swing framework
- VS Code Java development environment

---

**Enjoy the game and happy coding!** 🎮✨

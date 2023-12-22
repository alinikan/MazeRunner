# Maze Runner Game


## Project Description
MazeRunner is an engaging and interactive game designed to stimulate the player with dynamic mazes. Players navigate
through unique levels with obstacles and enemies, collecting coins and power-ups along the way. The game is designed 
in Java and uses the Model-View-Controller (MVC) architecture.

### Key Features
- **Enemies and Obstacles:** Players must avoid or overcome various enemies to progress.
- **Reward System:** Collect rewards as you navigate the maze, enhancing your score.
- **Leaderboard:** Compete with your friends for the top spot on the localized leaderboard.
- **Intuitive Controls:** Easy-to-use and intuitive controls for a balanced gameplay experience.

### Technologies Used
- Java: For core game development.
- Maven: For project management and build automation.
- JUnit and Mockito: For unit testing and ensuring code quality.

## Prerequisites
- Java JDK 17
- Maven

## Building the Project
To build the project and generate the JAR file, run the following command in the project root directory:
```bash
mvn clean package
```

## Running the Project
To run the project, run the following command in the project root directory:
```bash
java -jar target/MazeRunner-1.0-SNAPSHOT.jar
```

## Testing the Project
To run the tests, run the following command in the project root directory:
```bash
mvn clean test
```
This will execute all of the tests in the project and produce a report of the results.

## Generating the Javadoc
To generate Javadocs, run:

```bash
mvn javadoc:javadoc
```
The generated documentation will be available in the target/site/apidocs directory.



# COMP2042_CW_hfyms6

## Name: Soo Min Hao
## Student ID: 20297525

### This repository is created to refactor and perform version control for Software Maintenance Coursework 
### The coursework's objective is to given incompleted 2048 code, refactor and redesign the game
### You require an IDE (example: Intellij) to compile the code and run this game as these codes are build on Intellij IDE.
### You are also required to have at least a java version of Java 19 (JDK 19) to compile the codes
### Version of game written as V1.x.x
### Stable & Playable Version is the final version of game


[Download Java JDK](https://www.oracle.com/my/java/technologies/downloads/#jdk19-windows)

[Download Intellij](https://www.jetbrains.com/idea/)

------------------------------------------------------------------------------------------------------------

### How to extract and compile the code to produce the 2048 game application.4

1. Go to COMP2042_CW_hfyms6 repository
2. Click on the green button as 'Code'
3. Click on 'Download ZIP' button
4. Navigate to the location the .zip file is downloaded and extract the downloaded .zip file
5. Once file is extracted go to Game_2048\src\main\java\Game and open Main.java in an IDE (preferrably Intellij).
6. Click on the green 'play' arrow key to compile and run the code
7. Enjoy the game!

------------------------------------------------------------------------------------------------------------

### Location of Javadoc
When .zip file is downloaded from github repository,
the .zip file when extracted has a file called javadoc that contains the java documentation

------------------------------------------------------------------------------------------------------------

### Implemented Features that are working properly

1. Button to Return to scenes
2. Colour Theme Changer
3. Difficulties Selector
4. Leaderboard
5. Main Menu (inherits from Main - main is only used for launching the application)
6. Menus (inherits from Main - by seperating menus, easier for all menus to have same UI and colour)
7. Save Feature

------------------------------------------------------------------------------------------------------------

### Implemented Features that are not working properly

-All designed and implemented features are working properly 

------------------------------------------------------------------------------------------------------------

### List of features that are not implemented and explanation

-There is no return or new game button in the game scene and this causes
the players/users to only able to quit the application but force quit,
This is not implemented because implementation requires Scene parameters to be passed from other scenes, thus making the method to call the game scene longer.

-When the player reaches 2048, there is no winning screen that lets players to choose either to continue game or to quit game. Therefore, the game is set to be hanging.
This is not implemented because implementation breaks the winning conditions, and also causing multiple dialog boxes to pop out asking player repetitively should the player clicked on continue game after winning it first time.

------------------------------------------------------------------------------------------------------------

### List of new Java classes introduced 

1. ColourThemeChanger
2. DifficultySelectorMenu 
3. FileData
4. GameSceneLoader (new class that only creates UI for game scene)
5. LeaderboardMenu (subclass)
6. MainMenu (superclass for all of menu scenes)
7. OptionMenu (subclass)
8. ScoreSaver

------------------------------------------------------------------------------------------------------------

### Java classes that is modified 

1. Account -Removed due to unused and unnecessary class
2. Controller -Removed due to unused and unnecessary class as .fxml is not used in this application

------------------------------------------------------------------------------------------------------------
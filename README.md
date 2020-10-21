# ChapsChallenge

## Cloning the Project
1. Start by signing into github using the following commands:
    - `git config --global user.name "Firstname Lastname"`
    - `git config --global user.email "ecsusername@ecs.vuw.ac.nz"`

2. Clone the repository
    - `cd /your/workspace`
    - `git clone https://gitlab.ecs.vuw.ac.nz/course-work/swen225/2020/groupproject/team23/chapschallenge.git`
    - `cd chapschallenge`
    - `git switch master`

Alternatively, this step can be performed by using the "get from version control" function in IntelliJ

3. Import Libraries and Modules for IntelliJ
    Libraries and modules must be added for the program to run.
    - Open the project structure menu and select modules
    - select Program/src as the source directory
    - select Program/src/nz.ac.vuw.ecs.swen225.gp23/render/audio as a resource
    - select Program/src/nz.ac.vuw.ecs.swen225.gp23/render/tile_images as a resource

    - Open the libraries tab
    - Select gson from the lib folder
    - Select json from the lib folder
    - Select guava from the lib folder

    - Open one of the monkey tests and select the option to import JUnit 4

## Running the Game
Start the game by running **nz.ac.vuw.ecs.swen225.gp23.application.Main** from an IDE such as IntelliJ or Eclipse. 

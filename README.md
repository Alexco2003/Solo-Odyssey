# Solo Odyssey

Welcome to "Solo Odyssey", an interactive console game inspired by the anime "Solo Leveling". This unique adventure, written in Java, will embark you on a journey to get stronger, face challenges, and level up your character.

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Dependencies](#dependencies)
5. [Usage](#usage)
6. [Screenshots](#screenshots)
7. [Contribution](#contribution)
8. [License](#license)

## Introduction
Solo Odyssey is an engaging, interactive console game inspired by the anime 'Solo Leveling'. This unique adventure, written in Java, immerses you in a world of adventure and challenges.

## Features
- Level up your character
- Face formidable bosses
- Collect valuable items
- Undertake quests
- Unravel an immersive story
- Discover secrets
- Progress through levels, each bringing new challenges and rewards

## Installation
To get Solo Odyssey up and running on your system, follow these steps:

1. Clone the repository in IntelliJ IDEA: `File -> New -> Project from Version Control -> Git -> Paste the repository URL -> Clone`.
2. Once the project is cloned, IntelliJ IDEA should automatically detect the Maven structure and download the necessary dependencies. If not, you can manually trigger it by clicking on `Maven -> Reload project` on the right side of the IDE.

## Dependencies
The project has several dependencies which are managed with Maven. You can see the full list in the `pom.xml` file. The main dependencies include:

- MySQL Connector/J for database connectivity.
- OpenCSV for handling CSV files.
- JUnit Jupiter API for testing.

Before running the application, make sure to install MySQL or MySQL Workbench and create a database. Then, go to the `config -> DatabaseConnection.java` file and change the `URL`, `USER`, and `PASSWORD` fields accordingly to match your database settings.

In IntelliJ IDEA, you might need to add the dependencies to your project structure. To do this, go to `File -> Project Structure -> Libraries -> + -> From Maven...` and add the dependencies.

## Usage
The game is played in the console and provides a text-based interface.

## Screenshots

### Screenshot 1
![Screenshot 1](/screenshots/screenshot1.png)
_Start + Login/Register Menu_

### Screenshot 2
![Screenshot 2](/screenshots/screenshot2.png)
_Player Menu_

### Screenshot 3
![Screenshot 3](/screenshots/screenshot3.png)
_The "Color Rush" Quest_

### Screenshot 4
![Screenshot 4](/screenshots/screenshot4.png)
_An encounter with an enemy_

### Screenshot 5
![Screenshot 5](/screenshots/screenshot5.png)
_A PVP Battle_

### Screenshot 6
![Screenshot 6](/screenshots/screenshot6.png)
_Achivements_

### Screenshot 7
![Screenshot 7](/screenshots/screenshot7.png)
_Architect Menu_


## Contribution
If you would like to contribute to the project, please fork the repository and submit a pull request.

## License
[MIT License](LICENSE)
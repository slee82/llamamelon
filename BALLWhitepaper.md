
# Basic Athletic Logic Language #
## BALL - An Overview ##


### Introduction ###
BALL (Basic Athletic Logic Language) is a language designed for analyzing and simulating sports games, in particular baseball. It is designed to help the user view and analyze a baseball match or season, through the help of statistical data gathered for the teams and each player in the team and simulations. BALL takes one or more database files that contain information about baseball teams. Each file will have an entry for each player on the team, and statistical data for each player such as OBP (on-base percentage), AVG (batting average), ERA (earned run average), WHIP (walks+hits per inning pitched) and so on. (for those unfamiliar with baseball terminology, we have provided a small glossary in the back of this document) A user can give a command to BALL to format and display statistics for the teams, or run a simulation of a match between the two teams. This document will explain the twelve features of BALL and how each feature makes BALL an effective tool for analyzing and simulating baseball.

BALL: A Simple, portable, scalable, realistic, abstracted, network savvy, high performance, expandable, user friendly, modular, future-proof, focused language.

We use the set of buzzwords above to describe the BALL language, and the following will clarify and explain each one while addressing the problems that BALL seeks to solve.



### Simple ###
BALL's users want to spend most of their time analyzing and predicting the outcome of sports games, they do not want to spend much time with mundane programming tasks such as complicated function and variable declarations, garbage collection, or confusing syntax. As a result, BALL omits many of the features in broad-use programming languages that are unnecessary to program in BALL; yet respecting some common syntax standards that users are comfortable with. When building BALL, we created a system that eliminates error-prone situations that current object-oriented programming languages have. Frequently, a programmer willing to analyze and simulate baseball games with a certain level of realism would most probably choose a general-purpose language such as C++. We built BALL specifically for manipulating sports games so that our users can jump in right away. Our users do not have to worry about programming the simulation engine: The back-end is ready for them to use and/or expand.

### Portable ###
Java is widely known as a robust, highly portable programming language that will be the core foundation of BALL programming language. This means that BALL will be cross-platform from day one. Whatever platform the user is running, as long as Java is available, BALL will run.

### Scalable ###
Sporting events come in all sizes, from informal two-team games to an entire national league and possibly beyond. Baseball is no exception, and BALL is able to handle that. There is no set limit on the database or program size, be it a small school tournament or the entire Major League. BALL can also simulate games from just a single match to a whole season with various teams. In particular, a single simulation based on a database of two teams will take roughly the same amount of time as a game based on the entire thirty teams of Major League Baseball. BALL does this through clever use of its resources such that database items for other teams will not obstruct the two teams currently playing. Therefore, maintainers of database files need not worry about their increasing collection of baseball statistics.

### Realistic ###
Being a simulator, a key requirement for BALL is to be able to play out a “life-like” baseball game entirely inside the computer. A BALL game will be based on the statistics fed to it, which gives the program a very sterile and rough approximation of a player's skill. BALL then plays these statistics with the opposing team's and constructs a scenario that will look as much like a real life game as possible. Thus each game will not be so random that the statistical data has no correlation with the results, but also not purely deterministic that one can determine the winner just by looking at the numbers. A user running a game will see that teams with better averages are more likely to win, but unexpected things can occur, something that made real life baseball the very exciting sport we see today.

### Abstracted ###
From its inception, BALL was designed from the ground up to liberate users from many of the tedious details required to run effective and realistic simulations. Specifically BALL is able to obtain data over the internet easily (see Network Savvy) to maintain its statistical databases current. Also included, are the mathematical functions necessary to calculate the basic baseball sabermetrics that allow users to easily build upon to create the more complex simulations of their choosing.

### Network Savvy ###
BALL has been designed to take advantage of the rich source of data available through the internet. BALL has built-in functionality to connect to internet resources, whether these be from the standard provided or user customized, and automatically update its internal databases to guarantee the most up-to-date realistic simulation possible (see Realistic).

### High Performance ###
A key requirement for a baseball simulation program to be effective, is that it produces quick and reasonable results. With these requirements in mind, several choices were made to enhance the performance of BALL when dealing with data manipulation and computation. The database backend was specifically chosen to allow BALL fast streamlined access to all required statistical data. Similarly, Java was chosen to write the compiler in order to leverage its performance reputation in producing bytecode that is quickly translated at runtime into machine code for any particular CPU. These advantages should allow BALL to meet the expectations of even the most demanding users.

### Expandable ###
One of our main focuses in designing BALL is allowing the user to add statistical data to the various preexisting tables of data as well as defining new statistics as they see fit. This aspect is the key to BALLs expandability. Just as a user can define new stats, he similarly can define new rules or even new games. That ability is what leads to users being able to create modules which can contain any other sport or game (see Modular). In effect, BALL can be expanded to include any sport or game.

### User Friendly ###
The applications in BALL be be mostly shorter one to two line applications and most of the computational “heavy lifting” will be managed and handled by the compiler. In their simplest form BALL programs will look like the following:

```
BALL sim(Dodgers, Yankees, 5)
BALL ERA(Manny Ramirez)
```

It is very easy to see what this application does, it simulates 5 games between the Dodgers and the Yankees. Furthermore, many of the functions provided by BALL will be either known abbreviations of English words or known stats of the actual game. This will allow anyone who is familiar with the sport’s stats to effortlessly access the data and create BALL applications.

### Modular ###
BALL is not designed to be a simulator/language only for baseball, but rather for any sport in which a concrete rule system exists and in which statistical data can be collected. As a result, it is possible that other games could be ported to BALL if modules are rewritten. This also enables users to modify rules of the game being analyzed for purposes of interesting simulations, as well as create new games entirely and use BALL to maintain statistical data for their game.


### Future-proof ###
Thanks to its modular construction, (see Modular) BALL is future-proofed against rule changes, statistical changes, and even entire new games in which statistical data can be collected. Programmers writing BALL scripts can import new rules, create new benchmark statistics, import entirely different games and data, and thus completely change the usage of BALL. As a result, it is possible external (non-athletic) data manipulation could be handled with BALL.

### Focused ###
BALL is focused on athletic applications, specifically on simulating baseball games and maintaining and understanding/interpreting baseball statistics and sabermetrics. Because baseball is a sport with much statistical data, BALL is built for baseball, but can be expanded to other sports (see Future-proof). Nonetheless BALL is focused on athletic applications, and their statistical and logical interpretations.


### Conclusion ###
BALL provides novice users with a minimalistic system that they can use to unleash its power; and provides advanced programmers with all the tools to expand BALL’s capabilities to any sports game, season, or league, and customize aspects of the BALL simulation engine. BALL’s abstracted design allows users to take the engine to any level of complexity, and work with as many different kinds of data as necessary. BALL includes a vast set of default actions and convenient automated features such as network-savvy data fetching, all of which can be overridden at the user’s command. With a core foundation based on the robust and widespread Java language, BALL is just as reliable, quick, and platform-independent.


### Glossary: ###
Here we will give rough definitions of some baseball terms that might be unfamiliar to the audience. More precise definitions can be found in the Web.

AVG (batting average) - the ratio of the number of times a player gets a hit to the number of times he is at bat.

ERA (earned run average) - the mean of the number of times a pitcher allows a run (when a player of the opposing team successfully returns to home plate, scoring a point) per 9 innings pitched.

OBP (on-base percentage) - the measure of how often a player safely reaches base for any reason other than an error on the opposing team or a fielder's choice.

WHIP (walks plus hits per inning pitched) - the average number of batters a pitcher has allowed to first base per inning.
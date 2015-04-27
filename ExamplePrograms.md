# Introduction #

Holler. Here's my example program like we discussed in our meeting.


# Details #

```
/*Definitions of stats and sim function*/
define stat 1b as h-2b-3b-hr.
define stat obp as (h + bb) / (ab + bb).
define stat slg as (1b+(2*2b)+(3*3b)+(4*hr)) / ab.
define stat ops as obp + slg.
define sim func1 as (team1's era / team2's era) * team2's ops.

/*Actions*/
sim 3 games between the Astros and the Dodgers.
sim 3 games between the Astros and the Dodgers using func1.
sim 3 games between the winner of 2 games between the Astros and Pirates and the winner of 4 games between the Dodgers and Mets.
display ops for all players on the Cardinals.
```

Some decisions I made: (which of course could easily be changed. These are just ideas I had. I'm not married to any of them.)
  * _define_ keyword used to define stats or simulation functions.
  * _stat_ keyword used to define stats.
  * _sim_ keyword overloaded. Usages:
    * Primary - to simulate games
    * Secondary - to define simulation functions.
  * _as_ keyword just means the equals (=) sign. Just more friendly.
  * _._ character (Period) used to end commands, like a semi-colon in Java.
  * _func1_ is an example of a token used to name the new sim function.
  * _'s_ is the same as a dot in java or a '->' in C. Thus, team1's era calls team1.era from Java or team1->era from C.
  * _games_ keyword is another one used for friendly language. It just means that the number that precedes it is the number of games to simulate. _game_ would be the same, or it would be implied. (That is, sim 1 game = sim 1 games = sim. Or sim 2 games = sim 2 game.)
  * _between_ keyword lets the compiler know that the first of two teams is coming up next.
  * _the_ keyword is ignored by the compiler as whitespace. Just friendly.
  * _and_ keyword lets the compiler know that the second of two teams is coming up next.
  * _winner of_ keyword lets the compiler know there will be a nested sim function instead of a team.
  * _display_ is the same as the stat function. It just prints out a table of the requested stats.
  * _for all_ is a for-each loop.
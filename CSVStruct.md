# Introduction #

Here's what I had envisioned (Nathan), but obviously this is open to change.


# Details #

Example CSV file:
```
Team Name: Houston Astros,Houston,Astros
Team Stats
74,88
Batting
Ivan Rodriguez,327,41,82,15,2,8,13
Lance Berkman,460,73,126,31,1,25,97
Kazuo Matsui,476,56,119,20,2,9,34
Miguel Tejada,635,83,199,46,1,14,19
Geoff Blum,381,34,94,14,1,10,33
Carlos Lee,610,65,183,35,1,26,41
Michael Bourn,606,97,173,27,12,3,63
Hunter Pence,585,76,165,26,5,25,58
Wandy Rodriguez,63,4,8,3,0,0,1
Roy Oswalt,49,4,6,0,0,0,2
Brian Moehler,42,0,1,0,0,0,1
Mike Hampton,37,6,12,1,0,1,2
Russ Ortiz,28,2,5,0,0,1,1
Felipe Paulino,25,0,1,0,0,0,0
Pitching
Wandy Rodriguez*,205.2,192,69,63,193
Roy Oswalt,181.1,183,83,42,138
Brian Moehler,154.2,187,94,51,91
Mike Hampton*,112.0,128,66,46,74
Felipe Paulino,97.2,126,68,37,93
Russ Ortiz,85.2,95,53,48,65
```

## Explanation ##
  * So the first line of the file contains the team name, and every way to call the team. So then the team could be referred to as Houston Astros, Houston, or Astros. This is comma-delimited.
  * Team stats starts the team stats section and Batting starts the batting section and Pitching starts the pitching section.
  * Only these stats are included in the csv. Everything else would be calculated.
    * For Teams: Wins, Losses
    * For Pitchers: IP (innings pitched), K (strikeouts), H (hits allowed), BB (walks allowed), and ER (earned runs).
    * For Batters: AB (at-bats), R (runs), H (hits) 2B (doubles), 3B (triples), HR (home runs), and BB (walks).
  * The structure is as follows:
    * Team Stats: Wins,Losses
    * Batting: Name,AB,R,H,2B,3B,HR,BB
    * Pitching: Name,IP,H,ER,BB,SO

Potential issue: I put in Batting stats for all the pitchers since the Astros are a NL team and pitchers bat. But obviously there's only 9 batters per game.
my attempt at a HelloWorld program, simulating a single game

# Introduction #

This will simulate a single game, taking into account only the team’s winning percentage.  Taken from
http://www.diamond-mind.com/articles/playoff2002.htm
I’ve tried following as much of the previous examples as possible.  And of course, if it sounds totally whack let me know, I’ll take no offense


# Program #

```
/*  Stats Definitions  */


// loading all the stats for each team

team Yankees  = load(Yankees.cvs);  //not really sure if this is needed
team Phillies = load(Phillies.cvs);

Yankee’s WPct = .636;   //not sure if this would be defined as a stat, or as an attribute
Phillie’s WPct = .574;


/* Simulation Function, using the formula based strictly on team’s winning percentages  */

function helloSimulation (team team1, team team2) is:

team dummy;   // placeholder for team

    if (team1’s WPct < team2’s WPct ) then:   // in case team2’s WPct is greater than team1’s
       dummy = team1;
       team1 = team2;
       team2 = dummy;
    end;

/* assumes that team has a WPct attribute, which should be loaded with the team */
gWPct = team1’s WPct;     // the "good team"

bWPct = team2’s WPct;     // the "bad team"

WinningPct = (gWPct - (gWPct * bWPct)) / (gWPct + bWPct - 2 * gWPct * bWPct);  
// this should just return a winning percentage for the better team gWPct


    if ( randNumber <= WinningPct ) then:     
     /*  randNumber returns a number between 0 and 1 not sure if we should make this a built function? */
       print team1’s name + " win vs " + team2’s name;
       return team1;
    else
       print team2’s name + " win vs " + team1’s name;
       return team2;
    end

end   // end helloSimulation


/* Following from Daniel’s example */

activate helloSimulation;   //activate the simulation

team winner = sim(Yankees, Phillies, 1)
```

# Output #

```
Yankees win vs Phillies    :)
```
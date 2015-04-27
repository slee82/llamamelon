# Introduction #

here's where I write my sample program, and to build up ideas from Nathan's.

# Details #

```

define stat 1b as h-2b-3b-hr.
define stat obp as (h + bb) / (ab + bb).
define stat slg as (1b+(2*2b)+(3*3b)+(4*hr)) / ab.
define stat ops as obp + slg.

/*
 * Because we're going to let users define their own sim functions, I think 
 * it's a good idea to be able to reference players from a team.
 */

define player pitcher1 as "Sabathia" from Yankees. /* inside a string */

/* or, if a user wants to get a random player, */

define player rand1 as any from Yankees.

/* 
 * so what I'm thinking is, let's have a team be a list or set data structure
 * (after it's loaded) from which we can extract a player, using the keyword
 * "from". The users can't make their own teams (therefore sets) though.
 *
 * what "from" does is match whatever is before it with each entry in the list 
 * after it, here means match each player with a string, which we define as
 * matching with the player's name.
 *
 * after getting a player, we can apply stats to it:
 */

print pitcher1's obp. /* outputting stats will add a newline */
print "Random player's name is: ". /* newline implicitly added to string */
print rand1's name.    /* everything printed to stdout by 'print' adds newline */
print "Random player's slg is: ". 
print rand1's slg. 

print "" /* print a newline */
print "Yankees batting practice:".

/*
 * lots of things going on here:
 * 1. "define fcn" - defines a function
 * 2. "batting_practice" - the name of the sim func
 * 3. "between" - arguments list, sort of like what an open bracket does in C
 * 4. "player pitcher" - type, then formal parameter name
 * 5. " and " - two arguments
 * 6. "player batter " - same as no.4 above
 * 7. "as:" - multiple-line commands coming up
 */

define function batting_practice between player pitcher and player batter as:

    /* users can define a number "set" using "between" */
    define range rand_range as between -0.1 and 0.1.

    /* means: pick a random number between -0.1 and 0.1 */
    define number rand_num as any from rand_range.

    /* or, both statements above can be grouped as */
    define number rand_num2 as any from between -0.5 and 0.5.

    /* it's a bit more long-winded compared to regular English, but I think 
       it's easier to implement. */

    define number success as (batter's hits / batter's at_bats) /
                             (pitcher's hits_allowed / 
                                (pitcher's innings_pitched * 3))
                             + rand_num. /* some fake formula */

    /*
     * if <conditional> then: <statements>. else: <statements>
     */
    if success is over 1.0 then:
        return batter.
    else return pitcher. /* can also be else if blah blah blah */
    
end batting_practice. /* same effect as an end curly brace in C */

print batting_practice between pitcher1 and rand1.

/* same as above, this can be shortened to: */
print batting_practice between "Sabathia" from Yankees and any from Yankees.

```

So, to summarize my proposal, and some ideas for discussion:
  * define takes a data type as its first "argument"
  * a new data type, "player"
  * I think using 'as' like Nathan suggests is nice.
  * a baseball team is a set of players.
  * finding an element from a set can be done using the keyword "from" (just like the beginning of the sentence I just made). how "from" matches the matching object to the set depends on the set and the object. (which means it depends on us) For example, matching "Sabathia" to CC Sabathia's name in the team object.
  * "any" keyword matches a random element inside the set. I haven't figured out where to put specific distributions inside the set (like if we want normal distribution instead of the default uniform instead). Of course, all this about distributions only apply when wanting to have random numbers, not players from a team.
  * "print" lets the user output to stdout. newlines explicitly added, though maybe we'll provide a more "expert" function printn that doesn't add newlines.
  * functions other than sim functions. I dunno, do we want this? or can batting\_practice above be integrated to a sim function?
  * functions with multiple statements inside, using "as:" and "end (functionname)"
  * if-else conditionals (see example above). If we decide to add this, let's think about the syntax. how does "if" know that it ends? when an "else" is detected or an "end if"? I used "then:" above to specify that multiple statements need to be executed after an if.
  * I say except for special sim functions, regular functions don't return anything to stdout unless you "print" their output. Is this too unintuitive?
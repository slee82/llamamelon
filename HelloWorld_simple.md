_Modify to your liking!_

```

simfunction helloWorld (team hello, team world) is:
  if (hello’s WPct > world’s WPct ) then:
    return hello;
  else:
    return world;
  end
end

activate helloWorld;

team hello = load(hello.csv);
team world = load(world.csv);
print "Winner: " + sim(hello, world, 1);
```
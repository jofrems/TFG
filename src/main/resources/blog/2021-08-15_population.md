---
writer: drpicox
coder: drpicox
---
# Population

Until this moment cities were a little bit too sad,
but now, we add population.

The people live in the city, and make the city grow!

### UI Design

```                                                                                
     View City:                                           end     View Game
   +---------------------------------------------------+  turn   +---------------------+
   | ©               …topbar… ( End Turn )             | ------> | ©               …top|
   +---------------------------------------------------+         +---------------------+
   | < | Player leo...ard                              |         |  Player leonar...ard|
   +---------------------------------------------------+         +---------------------+
   |  City Capital                                     |         |  Round 1.           |
   |  Population:  #10                                 |         |  ....               |
   :                                                   :         :                     :
   +---------------------------------------------------+         +---------------------+                                                                                              
```                                                                 

## Growing the city

### Let's see the population

Create and start playing one of your games: 

 * Given there is "leonard" playing their game "together".
 <!-- SNAPSHOT status=200 -->  
 * Game round should be 1.
 * "leonard" should have 1 city. 
 * "leonard" should have the "Capital" city.
 * Click on the "leonard" city "Capital"
 * You should be at the screen of a city.
 * The name should be "Capital".

You can see here the population:

 * The population should be 10.

### And others players the same

Others players can also see their populations.

 * Given there is the next player "penny".
 <!-- SNAPSHOT status=200 -->
 * Click on the "penny" city "Capital"
 * The population should be 10.

### New functionality, end round

There is a button to end the turn for everybody.

 * End the round.
 <!-- SNAPSHOT status=200 -->
 * You should be at the game screen.
 * Game round should be 2.

### The population grows

Each turn each city increases in one population:

 * "penny" should be the current player.
 * Click on the "penny" city "Capital"
 * The population should be 11.

For each player:
 
 * Go to Next player.
 <!-- SNAPSHOT status=200 -->
 * You should be at the game screen.
 * "leonard" should be the current player.
 * Click on the "leonard" city "Capital"
 * The population should be 11.

### But with a limit

The maximum population for each city grows:

 * End the round.
 <!-- SNAPSHOT status=200 -->
 * Game round should be 3.
 * Click on the "leonard" city "Capital"
 * The population should be 12.

Until it reaches a maximum of 20:

 * Skip 8 rounds.  
 <!-- SNAPSHOT status=200 -->  
 * Game round should be 11.  
 * Click on the "leonard" city "Capital"  
 * The population should be 20.  

And does not matter how many rounds pass, that it will be always 20.

 * End the round.
 <!-- SNAPSHOT status=200 -->
 * Game round should be 12.
 * Click on the "leonard" city "Capital"
 * The population should be 20.

For every player.

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->
 * You should be at the game screen.
 * Game round should be 12.
 * "penny" should be the current player.
 * Click on the "penny" city "Capital"
 * The population should be 20.






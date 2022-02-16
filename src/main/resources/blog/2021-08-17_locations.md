---
writer: drpicox
coder: drpicox
---
# Locations

Did you imagined the map of your city?
You were close or far away from the other players?
Do you know it?

Now you can see where you are.
At least the location E/W.

### UI Design

```                                                                                
               Entity View   (adds Location)

            ┌──────────────────────────┐    ┌──────────────────────────┐
            │ ...topbar...             │    │ ...topbar...             │
            ├──────────────────────────┤    ├──────────────────────────┤
            │ ...game...               │    │ ...game...               │
            ├──────────────────────────┤    ├──────────────────────────┤
            │ City Capital             │    │ Ship Beagle              │
            │ Location: ...            │    │ Location: ...            │
            │                          │    │                          │
            │ New name: [ .... ]       │    │ New name: [ .... ]       │
            │ ( Change name )          │    │ ( Change name )          │
            │                          │    │                          │
            │ Population: #0           │    │ Population: #0           │
            │                          │    │ Load/Unload: [ .. ] (l/u)│
            │ Ships:                   │    └──────────────────────────┘
            │ - ...                    │    
            │ - ...                    │
            └──────────────────────────┘                                                     13
                                                                                     12                  xxxxx
                                                                               11                xxxxx xxx
                                                                        10            xxxxxxxxx xx
   Artist concept:                           5       6             9          xxxxxxxxx
                                                          7    8        xooo xx
                                    4            xxxxx              xx x ooo
                1     2     3                 xxxx    xxxx    xxx xx     oo
                                           xxooo         xxxxxx
            x xcooo xx x x xxxxxxxxxxxxxxxx  ooo                         Capital
  xxxxxx xx    oooo                          oo                          sheldon
               ooo
                    Capital                   Capital
                    leonard                   penny                                                                                                                                                      
```                                                                 

## The cities

Cities are added automatically,
the first in the position 0, next in 5, next in 10,
and so on.

Lets see.

### First player city

When the player is added, the city is founded in the right position.

 * Given there is "leonard" playing their game "together".
 <!-- SNAPSHOT status=200 -->  
 * Click on the "leonard" city "Capital"
 * You should be at the screen of a city.
 * It should be at the location 0.

### Next player

The next player is 5 units to the west.

 * Given there is the next player "penny".
 <!-- SNAPSHOT status=200 -->
 * Click on the "penny" city "Capital"
 * It should be at the location 5.

### And so on

Each new player its at distance five from the previous one.

 * Given there is the next player "sheldon".
 <!-- SNAPSHOT status=200 -->
 * Click on the "sheldon" city "Capital"
 * It should be at the location 10.

### And ships

Ships are with their cities

 * Click on the "sheldon" ship "Beagle".
 * It should be at the location 10.

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->
 * "leonard" should be the current player.
 * Click on the "leonard" city "Capital"
 * Click on the "leonard" ship "Beagle".
 * It should be at the location 0.
 
 * Go to Next player.
 <!-- SNAPSHOT status=200 -->
 * "penny" should be the current player.
 * Click on the "penny" city "Capital"
 * Click on the "penny" ship "Beagle".
 * It should be at the location 5.



---
writer: drpicox
coder: drpicox
---
# Multiplayer

Now you can play with your friends in your
same device. You can share it!

Let's play together.

### UI Dessign

```                   
                                             /----\  next                          
     View Game:                              v    |
   +---------------------------------------------------+      +-----------------------------+
   | ©               …topbar… ( Multiplayer ) ( Next ) |----->| © ....                      |
   +---------------------------------------------------+ mp   +-----------------------------+
   |  Player leonard plays game1 created by leonard    |      | Add next player.            |
   |                                                   |      |                             |
   |                                                   |      | Next player name: [ penny ] |
   |                                                   |      | Next password:    [       ] |
   |                                                   |      |                             |
   :                                                   :      | ( Join Next ) ( Cancel )    |
   +---------------------------------------------------+      +-----------------------------|
                                                    ^               |
                                                    \---------------/ join next
```                                                                                                      

## Playing game

### We need to create a game and join

Create and start playing one of your games: 

 * Given there are players "leonard", "penny" and "sheldon".
 * And "leonard" is playing their game "together",    
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.  
 * "leonard" should be the current player.
 * Playing game should be "together" created by "leonard".

### Adding a new player

 * Go to Multiplayer.
 * You should be at the add next player screen.  
 * Enter "penny" as next player name.
 * Enter "tbbt12" as next player password.
 * Click the Join Next button.
 <!-- SNAPSHOT status=200 -->  

 * You should be at the game screen.  
 * "penny" should be the current player.
 * Playing game should be "together" created by "leonard".
 
### Returning to your player

You can cycle around joined players.

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.
 * "leonard" should be the current player.
 * Playing game should be "together" created by "leonard".
                                    
### Adding more players
          
Not was everyone in? Well, you are always in time to add new friends.

 * Go to Multiplayer.
 * You should be at the add next player screen.
 * Enter "sheldon" as next player name.
 * Enter "tbbt12" as next player password.
 * Click the Join Next button.
 <!-- SNAPSHOT status=200 -->             
 * You should be at the game screen.
 * "sheldon" should be the current player.
 * Playing game should be "together" created by "leonard".

### The next order

Who plays next? Easy, it is always the joined order.

The first is leonard:

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.
 * "leonard" should be the current player.
 * Playing game should be "together" created by "leonard".

The second is penny:

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.
 * "penny" should be the current player.
 * Playing game should be "together" created by "leonard".
 
The third is sheldon:

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.
 * "sheldon" should be the current player.
 * Playing game should be "together" created by "leonard".

And cycle back to leonard:

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.
 * "leonard" should be the current player.
 * Playing game should be "together" created by "leonard".

### And back

If we do not want to add a new friend,
We just cancel, and return to the player screen.

 * Go to Multiplayer.
 * You should be at the add next player screen.
 * Cancel add next player.
 * You should be at the game screen.
 * "leonard" should be the current player.
 * Playing game should be "together" created by "leonard".

## Safe for humans

We humans can mess and be wrong so we add extra checks,
so you can realize when you are wrong.

 * Given there are players "leonard", "penny" and "sheldon".
 * And "leonard" is playing their game "together",
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.
 * "leonard" should be the current player.

### Trying wrong name
 
Try add a non existing player:

 * Go to Multiplayer.
 * You should be at the add next player screen.
 * Enter "howard" as next player name.
 * Enter "tbbt12" as next player password.
 * Click the Join Next button.
 <!-- SNAPSHOT status=400 -->             
 * You should be at the add next player screen.
 * You should see an error message saying that "The login credentials are wrong".

### Trying wrong password
             
Try with a wrong password:

 * You should be at the add next player screen.
 * Enter "penny" as next player name.
 * Enter "wrongpassw0" as next player password.
 * Click the Join Next button.
 <!-- SNAPSHOT status=400 -->             
 * You should be at the add next player screen.
 * You should see an error message saying that "The login credentials are wrong".
                     
### Trying joining twice
                 
Try join again.

 * You should be at the add next player screen.
 * Enter "leonard" as next player name.
 * Enter "tbbt12" as next player password.
 * Click the Join Next button.
 <!-- SNAPSHOT status=400 -->             
 * You should be at the add next player screen.
 * You should see an error message saying that "You should join games in which are you not joined".
               
### And next...

Is not affected.

 * Cancel add next player.
 * You should be at the game screen.
 * "leonard" should be the current player.
 * Playing game should be "together" created by "leonard".

And cycle back to leonard:

 * Go to Next player.
 <!-- SNAPSHOT status=200 -->  
 * You should be at the game screen.
 * "leonard" should be the current player.
 * Playing game should be "together" created by "leonard".


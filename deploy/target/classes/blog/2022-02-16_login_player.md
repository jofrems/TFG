---
writer: jofrems
coder: jofrems
---
# Login Player

You know how to create your player
to play. Now you need to login.

Login allows to identify yourself
and play with your games.

Welcome inside.

### Disseny gràfic

```                                    
   View Welcome:                         View Login:                          View Player:                             
  +--------------------------+ login   +--------------------------+         +--------------------------+    
  | ©        ( S ) ( Login ) | ------> | ©       ( S ) ( Login )  |         | ©       ( S ) ( Login )  |    
  +--------------------------+         +--------------------------+  succes +--------------------------+    
  |  Welcome!                |         |  Login!                  |   /-->  ! Login  success           |    
  :                          :         |                          |   |     +--------------------------+    
  |                          |         |  player name: [leonard ] |   |     |  Player leonard!         |    
  |                          |         |  password:    [        ] |   |     :                          :    
  +--------------------------+         |  ( signup )              | --/     |                          |    
                ^                      ----------------------------         |                          |    
                |    if failure: show error          |                      +--------------------------+    
                \------------------------------------/                                                      
```                                                                                                              

## Login the Game

### Create a player

But before start, you need to create a player.

 > Go to the signup.  
 > Add your name as player name.  
 > Set your password.  
 > Click the signup button.  
 <!-- SNAPSHOT status=200 -->

At this point,

 > You should be back at the welcome screen.    
 > You should see a message saying that "The leonard player has been added successfully".  

### Login inside

You have now a player, now you can login.

 > Go to the login.  

 > You should be at the login screen.                                        
 > Enter your player name.  
 > Enter your password.  
 > Click the login button.  
 <!-- SNAPSHOT status=200 -->

 > You should be at the player screen.   
 > "leonard" should be the current player.    
 > You should see a message saying that "The leonard player has been logged successfully".  

## Multiplayer

You can have more than one player. So,

 > Given there is your player,  
 > And there is the player "penny" with password "payforyourmeal12".  

Now we have two users, and we can login with each one.

 > You should be at the welcome screen.                                         
 > Go to the login  
 > Enter your player name.  
 > Enter your password.  
 > Click the login button.  
 <!-- SNAPSHOT status=200 -->

 > You should be at the player screen.    
 > "leonard" should be the current player.    
 > You should see a message saying that "The leonard player has been logged successfully".  

But you can also login, with "penny" player.

 > Go to the login.  
 > Enter "penny" as player name.  
 > Enter "payforyourmeal12" as password.  
 > Click the login button.  
 <!-- SNAPSHOT status=200 -->

 > You should be at the player screen.  
 > "penny" should be the current player.   
 > You should see a message saying that "The penny player has been logged successfully".   

## Checking data

We are all human, (are we?), and we can
fail, so we have few failsafe to avoid
errors.

But before, we need a player.

 > Given there is your player.  

### Unkown player

If the player does not exists, you cannot login.

 > Go to the login.  
 > Enter "wrongplayer" as player name.  
 > Enter your password.  
 > Click the login button.  
 <!-- SNAPSHOT status=400 -->
 > You should be at the login screen.      
 > You should see an error message saying that "The login credentials are wrong".    

### Wrong password

 > Go to the login.  
 > Enter your player name.  
 > Enter "wrongpassword" as password.  
 > Click the login button.  
 <!-- SNAPSHOT status=400 -->
 > You should be at the login screen.      
 > You should see an error message saying that "The login credentials are wrong".  

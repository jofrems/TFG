---
writer: jofrems
coder: jofrems
---
# Welcome Player

That is just the beginning.
Welcome to the game.

Do you want to know how it works?
No problem. 
We will publish more blogposts during
the following days with the instructions
and new functionalities.

### Disseny gràfic

```
    View Welcome:                        View Signup:                         View Welcome:
  +--------------------------+         +--------------------------+         +--------------------------+
  | ©          ( Singup )    | ---->   | ©          ( Singup )    |         | ©          ( Singup )    |
  +--------------------------+         +--------------------------+         +--------------------------+
  |  Welcome!                |         |  Signup!                 |   /-->  ! Signup success           |
  :                          :         |                          |   |     +--------------------------+  
  |                          |         |  player name: [        ] |   |     |  Welcome!                |
  |                          |         |  password:    [        ] |   |     :                          :
  +--------------------------+         |  [ signup ]              | --/     |                          |
                                       ----------------------------         |                          |
                                                                            +--------------------------+                                                
```


## Entering the game

### All it's in the browser

You are reading this blogpost, so you already
know where the game is. Yes, in your browser.

### Create a player

But before start, you need to create a player:

 * You should be at the welcome screen.
 * Go to the signup.

 > You should be at the signup screen.  
 > Add your name as player name.  
 > Set your password.  
 > Click the signup button.  
 <!-- SNAPSHOT status=200 -->

At this point,

 > You should be back at the welcome screen.  
 > You should see a message saying that "The leonard player has been added successfully".  

## Checking data

We are all human, (are we?), and we can
fail, so we have few failsafe to avoid
errors.

### No player name

It is necessary the player name.

 > Go to the signup.  
 > Click the signup button.  
 <!-- SNAPSHOT status=400 -->
 > You should be at the signup screen.                                       
 > You should see an error message saying that "Player name should be present".       

### Too short player name

And it should have at least three characters long

 > Add "jo" as player name.              
 > Click the signup button.            
 <!-- SNAPSHOT status=400 -->
 > You should be at the signup screen.                                         
 > You should see an error message saying that "Player name should be at least three characters long".  

### No password

It should have a password.

 > Add your name as player name.     
 > Click the signup button.         
 <!-- SNAPSHOT status=400 -->
 > You should be at the signup screen.                                         
 > You should see an error message saying that "Password should be present".  

### Too weak passwords

It should have at least 6 characters.

 > Set "short" as password.  
 > Click the signup button.  
 <!-- SNAPSHOT status=400 -->
 > You should be at the signup screen.                                         
 > You should see an error message saying that "Player password should be at least six characters long".  

And at least one number.

 > Set "nonumbers" as password.  
 > Click the signup button.  
 <!-- SNAPSHOT status=400 -->
 > You should be at the signup screen.  
 > You should see an error message saying that "Player password should have at least one number".  

And some letters

 > Set "123456" as password.  
 > Click the signup button.  
 <!-- SNAPSHOT status=400 -->
 > You should be at the signup screen.                                          
 > You should see an error message saying that "Player password should have at least one letter".  


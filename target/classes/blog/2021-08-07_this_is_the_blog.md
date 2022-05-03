---
writer: drpicox
coder: drpicox
---
# This is the blog

You can see all the available functionalities in one place: the blog.

If you have any doubt about how something works, just check the blog.
It is our contract from we, the developers, with you, the user.
Everything that the blog says, is true.
We have tested it.

### UI Dessign

```
       ┌───────────────────────────────────┐
       │          return game              │
       │                                   │
       ▼                         blog      │
 ┌──────────────────────────────┐      ┌───┴──────────────────────────┐
 │                              │ ┌─►  │  Game   [ Blog ]      ....   │
 │  Game      Blog        ....  │ │    ├──────────────────────────────┤
 │                              ├─┘    │                              │
 ├──────────────────────────────┤      │  Whats the new...            │
 │                              │      │                              │
 │                              │  ┌───┼─ 2021/09/15 titol post 2     │
 │                              │  │   │  2021/09/13 titol post 1     │
 └──────────────────────────────┘  │   │  2021/09/07 this is the blog │
                                   │   │                              │
            ┌──────────────────────┘   │                              │
            │   click on title         └──────────────────────────────┘
            ▼                                             ▲
       ┌────────────────────────────┐                     │
       │  Game     Blog        .... │                     │
       ├────────────────────────────┤                     │
       │  « back                    ├─────────────────────┘
       │  this is the blog          │    click on back
       │  2021/09/07                │
       │                            │
       │                            │
       │  xxxxxxxxxxxxxx            │
       │  xxxxx                     │
       │                            │
       │  xxxx x  x   x xxxx        │
       └────────────────────────────┘                                             
```


## There is a blog

We keep register of everything we add to the game.

### Listing the blog

You can see a list of everything in the game
as posts in the blog section.

 * Go to the Blog
 <!-- SNAPSHOT status=200 -->

 * You should be at the blog screen.
 * You should see at least 7 posts.
 * You should see the post "This is the blog".
 * The post "This is the blog" should be the last post.

### Back to welcome screen

You can go back to welcome screen.

 * Go to the Welcome screen
 * You should be at the welcome screen.

### Looking at one post

You can read any post that you want,
just click on the title.

 * Go to the Blog
 <!-- SNAPSHOT status=200 -->
 * Click on the post "This is the blog".
 <!-- SNAPSHOT status=200 -->
 * You should see the post title "This is the blog".
 * The post should contain the text "rupeltinsky".

### And back to the list

You can go back to the list at any moment.

 * Click in the go back to the blog.
 * You should be at the blog screen.

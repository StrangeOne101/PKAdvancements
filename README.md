# PKAdvancements

Advancements for ProjectKorra!

## FAQ

### Can I change the layout of the advancements?
Sadly, no. That's just the way minecraft puts them there and I have no control over where they go. However, as new ones are added, they will shift to fit the new ones. 

### Can I make my own advancements?
No, but feel free to suggest some if you think the plugin is missing something! Unlike vanilla advancements, these advancements are triggered by a lot of plugin events and not vanilla, so even if you copied a lot of the advancements and modified, they would never be triggered. So please feel free to give me your suggestions!

### X has an advancement but it doesn't show up on my advancement screen!
Some advancements are hidden and are just easter eggs, or, are advancements that are there but don't show up because they may not be avaliable on some servers (e.g. not all servers may use metalbending)

### Why don't the advancements show progress on kills?
Long story short, it's due to the amount of storage it would take on your server. But, read on for a more in-depth explaination.

Due to the way advancements work, you have to specify a different trigger for each individual bit of progress on the bar. So if you wanted it to show 1/1000, you'd have to have 1000 different triggers. The downside to this is that all advancements must be stored in a file in the world, and thus, take up space. Each criteria added is about 0.138KB, and to have 1 advancement with 1000 criteria means 138KB. That's 0.13MB PER ADVANCEMENT. Although that in itself isn't much, for every player that plays on the server, they have to log the same information to keep track of how much progress they have on it. So if PKAdvancements had a total of around 10000 criteria (thats 10 advancements with 1000 criteria), that's an extra 1.3MB stored per player, per world. Have a server of over 1000 unique players? That's 1.3GB.

So, PKAdvancements stores how much each player has done in a database so it doesn't take up nearly that much space. In comparasion, each player, for every one of their advancements, stores only about 0.160KB (160 bytes). So even a server of 1000 players is only 160KB (0.16MB). So to sum up, it's either 0.13MB per player per advancement per world if you want the progress to show, or 0.16MB for 1000 players.

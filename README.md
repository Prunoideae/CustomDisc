# CustomDisc
A mod to make custom disk

The mod aims at giving music lovers a simple way to add their favourites into the game.
To do so, follow the instructions below:
1. Run the minecraft with the mod, and a customdisc\assets\customdisc\ folder should appears in your .minecraft directory, you can create it manually, either.
2. Specify the disks' names you want to create in custom_disc.cfg in the config folder
3. With the asset folder created, now you can put your audio file (must be .ogg file) into the folder, and modify every resource you want to.
   This setup is rather complex and tricky, so I made an example script in the repository and you can check it out.
   
   
↓Version 1.1 updates

As writing json files is hard, I wrote something to simplify the method, so now all you need is:
1. Run the minecraft with the mod to generate the folders and config, then write all you want into the config.
   IMPORTANT: the cfg name you write should only contains characters of 0-9, a-z (No upper case) and underscore(No spaces).
   Writing a fix for this could be difficult, as it has tricky points of dealing with game register mechanism, so this won't change in the near future.
2. Now, the sounds.json and the models will be generated automatically, so just put your texture files and audio files into right location, and the code will do everything else.

extra. If you want to try something more than the vanilla style texuture, you can also modify the json file in models/item to do what you like to do.

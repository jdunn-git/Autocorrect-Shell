# Autocorrect Shell / Autoshell

This repo was created as a Final Project in my masters course for software design principles. It is a demo of a shell prompt that can utilize different autocorrect strategies. This project was built to demostrate the use of a many different software design pattern, which can be found below. 

### Overview of the Assignment

My final project is a shell program that makes use of an "auto-correct" algorithm to infer which command is being attempted when there is a typo..
Commands that do not have typos will be executed exactly as typed. I built it in Java since it should be the easiest language to show to the class since I know everyone has at least some Java experience.

## Architecture
### Design Consideration + Workflow
To implement this shell, we have a loop querying for user input until either "exit" or "quit" is entered. When a command is received, we will validate whether we can correct it to a known command. If so, then we will construct a new command using the correct factory. At the core level, I am using the Java process builder to construct shell commands, run the commands, and get the output after execution. However, since different types of commands need to be constructed a little bit different in the process builder object, I needed different objects and therefore different factories for these types. Examples include Git commands vs Docker commands vs normal shell commands.
 
Since there are some cases where commands likely shouldn't be executed without being explicitly written (i.e. `$ sudo rm -rf *`), I also added different "modes" for the auto-correct algorithm. Modes include: 'Full' which will always try to correct typos and run any command, 'Safe' which will exclude certain commands corrected and will be more selective with the number of typos per node, and 'None' which will be a normal shell with no auto-correction performed. These were implemented as different strategies for the auto-correction code to take.

For now, every command that is being auto-correct to is pre-configured, and the auto-correction algorithms have a mix of pre-configuration and algorithmic generation. For instance, the 'Full' strategy will algorithmically generate every variant of a particular command that has a single typo, including two letters being swapped, one letter missing, one 'near' letter added next to a given character, and one 'near' letter replacing a given character. However, the 'near' letter set was pre-configured based on the qwerty keyboard layout to be any letter directly adjacent a letter on the keyboard.

Executing commands fit very naturally inside of a command model, since we are generating the shell command object and then calling the "execute" function. While most normal shell commands could be generated as a ShellCommandGeneric object, the `$ cd` command was a little different because it is changing the directory that other processes need to run from. For this reason, I used a singleton to keep track of this directory, and update the singleton from the ShellCommandCD class. This way each process can generate on the fly with the current directory, without needing to pass this state around as a variable to each factory and each visitor instance.

### Patterns Used
|Pattern|Usage|
|--|--|
Factory Pattern|To construct commands based on the command type. 
Command Pattern|To execute the shell command.
Strategy Pattern|To apply the correct "auto-correct" algorithm.
Visitor Pattern|To check each pre-configured command and validate against the "auto-correct" options for that command.
Singleton Pattern|To track of the current working directory across commands as they are executed.

## Running the Autoshell

Since I am using gradle, you can easily run the autoshell program from the root directory of this repo with this command: 
```
$ gradle run --console=plain --args="Full"
```

Note that to run git commands from inside of Java, you may need to run the following command: 
```
$ xcode-select --install
```

# k
[![license](https://img.shields.io/github/license/Project-Cepi/k?style=for-the-badge&color=b2204c)](../LICENSE)
[![wiki](https://img.shields.io/badge/documentation-wiki-74aad6?style=for-the-badge)](https://project-cepi.github.io/)
[![discord-banner](https://img.shields.io/discord/706185253441634317?label=discord&style=for-the-badge&color=7289da)](https://discord.cepi.world/8K8WMGV)

Kotlin debugging evaulator in Minestom.

## Usage

/k is shorthand, /ko is longhand

requires the permission "k.ok" and "k.store" for running scripts and storing them in the server, respectifully

OP overrides these two requirements.

### Shorthand
`/k println("Hello World!")`

This is the equivalent of
`/ko add println("Hello World!")`
`/ko eval`
`/ko dump`

NOTE: This won't actually run `ko` commands, and your current ko session will be safe.

### Longhand

You can use these to build longer test scripts

`/ko import (import)` - import anything, same format as code: * works as well

`/ko add (line)` - append a new line to the script

`/ko insert (index) (line)` - insert a line to the script

`/ko show` - sends the entire script to you

`/ko eval` - evaulates the script

`/ko dump` - erases all stored script, with a copy and paste to copy your script.

`/ko undo` - undo an action

`/ko redo` - redo an action


### Extra Functions / Variables

You can get your current self using `player`.

`println(String)` automatically redirects to your own output.

`mini(String)` also redirects to your own output in colored text using the Adventure MiniMessage format.

`execute(String)` executes a command as you.

## Installation

Download the jar from [Releases](https://github.com/Project-Cepi/k/releases)
OR compile it yourself. Instructions to do so are in Compile header

Drop it into the `/extensions` folder.

## Compile

Create a folder, then
Clone the repository using:

`git clone https://github.com/Project-Cepi/k.git`

Once it is cloned, make sure you have gradle installed, and run

`./gradlew build` on Mac or Linux, and

`gradlew build` on Windows.

This will output the jar to `build/libs` in the project directory.

**Make sure to select the -all jar**. If no shading is necessary, remove the `shadowJar`

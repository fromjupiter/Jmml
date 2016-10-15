# JMML

##Introduction
Jmml is a java toolkit to play with MML(Music Macro Language). You can find an introduction to Jmml written in Chinese [Here](https://zhuanlan.zhihu.com/p/22833739 "Jmml-想用java玩音乐").

Music Macro Language (MML) is a music description language used in sequencing music on computer and video game systems. See more on: [English Wiki](https://en.wikipedia.org/wiki/Music_Macro_Language "Music Macro Language")/[Chinese Wiki](https://zh.wikipedia.org/wiki/Music_Macro_Language "Chinese Wiki").<br/>
Some video games support built-in mml sytem to allow players to write their own music sheets, e.g. Mabinogi(洛奇),Maple Story 2(冒险岛2).<br/>
Before you start playing with jmml toolkit, you need to understand how MML works first. Maple Story 2 official site offers an intuitive [manual](http://mxd2.qq.com/cp/a20160420news/content-2.shtml "manual") about how to write music in MML.

**Author: Kexiang Feng**<br/>
**Email: fkxcole@qq.com**

##Table of Contents
* [Main Features](#main-features)
* [TODO List](#todo-list)
* [Quick Start](#quick-start)
* [Jmml Concepts](#jmml-concepts)
* [Release History](#release-history)

## Main Features
- Support converting mml text to MIDI audio file.
- Choose any instrument to "play" your mml text. You can select instrument by soundbank number and program number.

## TODO List
- Use xml schema to define a mml track text file
- Support converting from MIDI to mml text

## Quick Start
Before we start, please make sure that you have Java runtime Environment 1.8.<br/>
There are a few example programs in package `mml.examples`. Now you can follow the next few steps to try them out.

To begin with, clone Jmml project to your machine.<br/>
`git clone https://github.com/fromjupiter/Jmml.git`<br/>

Now you can either compile&run the examples in terminal, or import the project to Eclipse and do it in Eclipse. I will just walk you through how to run examples in your terminal.

* Change your working directory to Jmml location.<br/>
`cd ./Jmml`
* Compile example programs. *Set the classpath right!*<br/>
`javac -cp ./src/main src/main/mml/examples/*.java`
* Run any example program you are interested in.<br/>
`java -cp ./src/main mml.examples.SingleMmlTrackToMidi`
* Check your working directory. There should be some new midi files. More information can be found in the according java source code.
 * SingleMmlTrackToMidi will generate BraveHeart.mid
 * MultiMmlTrackToMidi will generate ImYours.mid

## Jmml Concepts
Those who invented MML haven't really thought about how we can organize MML texts to represent complex and ensemble music.
##### *Track*
An MML track stands for one instrumental part of a music piece. It's usually composited with many sub-tracks.

It usually corresponds to *track* in MIDI world, or the score for one musician in real world.
##### *Subtrack*
An MML subtrack stands for one MML textual sequence, it's composited with ordered events.

The MML textual sequence allows only one note to be hit at one time. It's like what one finger can do on the piano. 

A track needs more than one MML sequence, that's why we introduced subtrack.

##### *Event*
An event can be either note event or meta event. The concept is very similar to *MIDI Event*.

Note event contains information about how to play the note: the pitch, the velocity, the duration.<br/>
Meta event controls the everything from the track's perspective, e.g. the tempo.

## Release History
- 0.1.0 initial release. support MML to MIDI.

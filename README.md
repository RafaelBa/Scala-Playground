# learningscala

This repository is a mixture of my playground, a how-to-do scala documentation and my own learning progress ;)
It shows how things are done the scala way or how you use some libraries and scala features. Not everything is explained and not everything is the state of the art, just for the sake of explaining the possible ways you can use. 
The project always has an example of the library with an explanation, showing how to use it, and a test ensuring, that an update will not invalidate the way it is used (and of course the correctness of the implementation).
If there are no tests, the example is not ready, it's that simple.

The project is split artificially into subprojects. The seperation may not make sense, but it is just another example how to deal with an sbt multiproject. 

## Base
Here you can find the most basic scala features and extensions by libraries, e.g. Either, Option, Scalaz and implicit usages.

## Network
Everything which goes with network in the broader sense will be placed here like Play, Akka, Json, Http and database connections.

## Testing
Well, here are the testing frameworks based. Showing nice and different things such as specs2, scalatest and scalaCheck. Do not underestimate testing!
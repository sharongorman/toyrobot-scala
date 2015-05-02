package com.sharon

import org.specs2.execute.Pending
import org.specs2.mutable.Specification

class GameSpec extends Specification {
  "before a robot is placed" >> {
    val game = Game(Table(5, 5), None)

    "report returns nothing" in {
      game.report must beNone
    }

    "move does nothing" in {
      game.move === game
    }
    "placing a robot returns a game with a robot" in {
      game.place(CoOrds(0,0), North) === Game(Table(5,5),Some(Robot(CoOrds(0,0), North)))
    }

    "turnLeft does nothing" in {
      game.turn(Left) === game
    }

    "turnRight does nothing" in {
      game.turn(Right) === game
    }
  }

  "once the robot has been placed" >> {
    val game = Game(Table(5,5),Some(Robot(CoOrds(0,0), North)))
    "allows a valid move" in {
      game.move === Game(Table(5,5),Some(Robot(CoOrds(0,1), North)))
    }

    "prevents an invalid move North" in {
      val game = Game(Table(5,5),Some(Robot(CoOrds(0,4), North)))
      game.move === game
    }

    "prevents an invalid move West" in {
      val game = Game(Table(5,5),Some(Robot(CoOrds(0,4), West)))
      game.move === game
    }

    "turns the robot left" in {
      game.turn(Left) === game.copy(maybeRobot=Some(Robot(CoOrds(0,0), West)))
    }

    "turns the robot right" in {
      game.turn(Right) === game.copy(maybeRobot=Some(Robot(CoOrds(0,0), East)))
    }
  }
}

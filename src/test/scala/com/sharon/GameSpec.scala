package com.sharon

import org.specs2.execute.Pending
import org.specs2.mutable.Specification

class GameSpec extends Specification {
  "before a robot is placed" >> {
    val game = Game(Table(5, 5), None)

    "move does nothing" in {game.move === game}
    "turnLeft does nothing" in {game.turn(Left) === game}
    "turnRight does nothing" in {game.turn(Right) === game}
    "report returns nothing" in {game.report must beNone}

    "placing a robot returns a game with a robot" in {
      game.place(CoOrds(0,0), North) === Game(Table(5,5),Some(Robot(CoOrds(0,0), North)))
    }

    "placing a robot at an invalid spot does nothing" in {
      game.place(CoOrds(5,0), North) === game
    }
  }

  "once the robot has been placed" >> {
    val game = Game(Table(5,5),Some(Robot(CoOrds(0,1), North)))
    "allows a valid move" in {
      game.move === Game(Table(5,5),Some(Robot(CoOrds(0,2), North)))
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
      game.turn(Left) === game.copy(maybeRobot=Some(Robot(CoOrds(0,1), West)))
    }

    "turns the robot right" in {
      game.turn(Right) === game.copy(maybeRobot=Some(Robot(CoOrds(0,1), East)))
    }

    "reports the robot's position" in {
      game.report must beSome("0,1,NORTH")
    }
  }
}

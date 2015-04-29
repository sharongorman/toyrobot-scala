package com.sharon

import org.specs2.execute.Pending
import org.specs2.mutable.Specification

/**
 * Created by sharon.holliday on 4/28/15.
 */
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
      Pending("not yet implemented")
    }

    "turnRight does nothing" in {
      pending
    }

  }

  "once the robot has been placed" >> {
    val game = Game(Table(5,5),Some(Robot(CoOrds(0,0), North)))
    "move produces a game with the robot moved" in {
      game.move === Game(Table(5,5),Some(Robot(CoOrds(0,1), North)))
    }

    "moving north off the table prevents the move" in {
      val game = Game(Table(5,5),Some(Robot(CoOrds(0,4), North)))
      game.move === game
    }

    "moving west off the table prevents the move" in {
      val game = Game(Table(5,5),Some(Robot(CoOrds(0,4), West)))
      game.move === game
    }
  }



}

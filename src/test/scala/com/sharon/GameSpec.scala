package com.sharon

import org.specs2.execute.Pending
import org.specs2.matcher.MatchResult
import org.specs2.mutable.Specification

class GameSpec extends Specification {
  "before a robot is placed" >> {
    val game = Game(Table(5, 5), None)

    "move does nothing" in {doesNothing(game, Move)}
    "turnLeft does nothing" in {doesNothing(game, Turn(Left))}
    "turnRight does nothing" in {doesNothing(game, Turn(Right))}
    "report returns nothing" in {doesNothing(game, Turn(Left))}

    "placing a robot returns a game with a robot" in {
      updatesRobot(game, Place(CoOrds(0,0), North), Robot(CoOrds(0,0), North))
    }

    "placing a robot at an invalid spot does nothing" in {
      doesNothing(game, Place(CoOrds(5,0), North))
    }
  }

  "once the robot has been placed" >> {
    val game = Game(Table(5,5),Some(Robot(CoOrds(0,1), North)))
    "allows a valid move" in {
      updatesRobot(game, Move, Robot(CoOrds(0,2), North))
    }

    "prevents an invalid move North" in {
      val game = Game(Table(5,5),Some(Robot(CoOrds(0,4), North)))
      doesNothing(game, Move)
    }

    "prevents an invalid move West" in {
      val game = Game(Table(5,5),Some(Robot(CoOrds(0,4), West)))
      doesNothing(game, Move)
    }

    "turns the robot left" in {
      updatesRobot(game, Turn(Left), Robot(CoOrds(0,1), West))
    }

    "turns the robot right" in {
      updatesRobot(game, Turn(Right), Robot(CoOrds(0,1), East))
    }

    "reports the robot's position" in {
      val CommandResult1(report, _ ) = game.execute(Report)
      report must beSome("0,1,NORTH")
    }
  }

  def givesExpectedResult(game: Game, command: Command, expectedResult: CommandResult1) = {
    game.execute(command) === expectedResult
  }

  def doesNothing(game: Game, command: Command) = {
    givesExpectedResult(game, command, CommandResult1(None, game))
  }

  def updatesRobot(game: Game, command: Command, expectedRobot: Robot) = {
    val CommandResult1(_, newGame) = game.execute(command)
    newGame.maybeRobot must beSome(expectedRobot)
  }
}

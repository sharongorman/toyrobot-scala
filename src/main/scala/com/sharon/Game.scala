package com.sharon

case class Game(table: Table, maybeRobot: Option[Robot]) {

  def execute(command: Command):CommandResult = command match {
    case Place(coOrds: CoOrds, facing: Direction) => CommandResult(None, place(coOrds, facing))
    case Move => CommandResult(None, move)
    case Turn(rotation: Rotation) => CommandResult(None, turn(rotation))
    case Report => CommandResult(report, this)
  }
  
  private def report: Option[String] = {
    maybeRobot map { case Robot(position, direction) =>
        s"${position.xPos},${position.yPos},${direction.toString.toUpperCase}"
    }
  }

  private def place(coOrds: CoOrds, facing: Direction): Game = {
    whenValidPosition(coOrds) {newPos =>
      newGame(Robot(newPos, facing))
    }
  }

  private def move: Game = {
    whenRobotPlaced { case Robot(position, facing) =>
      whenValidPosition(position.move(facing)) {newPos =>
        newGame(Robot(newPos, facing))
      }
    }
  }

  private def turn(rotation: Rotation): Game = {
    whenRobotPlaced { case Robot(position, facing) =>
      newGame(Robot(position, facing.turn(rotation)))
    }
  }

  private def whenRobotPlaced(f: Robot => Game): Game = maybeRobot.fold(this)(f)

  private def whenValidPosition(newPos: CoOrds)( f: CoOrds => Game): Game = {
    if (newPos isOn_: table)  f(newPos) else this
  }

  private def newGame(robot: Robot) = {
    this.copy(maybeRobot = Some(robot))
  }
}

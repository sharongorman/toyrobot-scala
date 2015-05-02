package com.sharon

case class Game(table: Table, maybeRobot: Option[Robot]) {
  
  def report: Option[String] = {
    None
  }

  def place(coOrds: CoOrds, facing: Direction): Game = {
    newGame(Robot(coOrds, facing))
  }

  def move: Game = {
    ifRobotPlaced { case Robot(position, direction) =>
      ifValidPosition(position.move(direction)) {newPos =>
        newGame(Robot(newPos, direction))
      }
    }
  }

  def turn(rotation: Rotation):Game = {
    ifRobotPlaced { case Robot(position, direction) =>
      newGame(Robot(position, direction.turn(rotation)))
    }
  }

  private def ifRobotPlaced(f: Robot => Game): Game = maybeRobot match {
    case None => this
    case Some(robot) => f(robot)
  }

  private def ifValidPosition(newPos: CoOrds)( f: CoOrds => Game): Game = {
    if (newPos isOn_: table)  f(newPos) else this
  }

  private def newGame(robot: Robot) = {
    this.copy(maybeRobot = Some(robot))
  }
}

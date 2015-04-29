package com.sharon

case class Game(table: Table, maybeRobot: Option[Robot]) {
  def report: Option[String] = {
    None
  }

  def move: Game = {
    ifRobotPlaced { case Robot(position, direction) =>
      ifValidNewPosition(position.move(direction)) {newPos =>
        Game(table, Some(Robot(newPos, direction)))
      }
    }
  }



  def place(coOrds: CoOrds, facing: Direction): Game = {
    this.copy(maybeRobot = Some(Robot(coOrds, facing)))
  }

  private def ifRobotPlaced(f: Robot => Game): Game = maybeRobot match {
    case None => this
    case Some(robot) => f(robot)
  }

  private def ifValidNewPosition(newPos: CoOrds)( f: CoOrds => Game): Game = {
    if (newPos isOn_: table)  f(newPos) else this
  }

}

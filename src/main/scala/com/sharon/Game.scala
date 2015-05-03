package com.sharon

trait GameApi [GameType <: GameApi[GameType]]{
  def report: Option[String]
  def place(coOrds: CoOrds, facing: Direction): GameType
  def move: GameType
  def turn(rotation: Rotation): GameType
}

case class Game(table: Table, maybeRobot: Option[Robot]) extends GameApi[Game] {
  
  def report: Option[String] = {
    maybeRobot map { case Robot(position, direction) =>
        s"${position.xPos},${position.yPos},${direction.toString.toUpperCase}"
    }
  }

  def place(coOrds: CoOrds, facing: Direction): Game = {
    ifValidPosition(coOrds) {newPos =>
      newGame(Robot(newPos, facing))
    }
  }

  def move: Game = {
    ifRobotPlaced { case Robot(position, direction) =>
      ifValidPosition(position.move(direction)) {newPos =>
        newGame(Robot(newPos, direction))
      }
    }
  }

  def turn(rotation: Rotation): Game = {
    ifRobotPlaced { case Robot(position, direction) =>
      newGame(Robot(position, direction.turn(rotation)))
    }
  }

  private def ifRobotPlaced(f: Robot => Game): Game = maybeRobot.fold(this)(f)

  private def ifValidPosition(newPos: CoOrds)( f: CoOrds => Game): Game = {
    if (newPos isOn_: table)  f(newPos) else this
  }

  private def newGame(robot: Robot) = {
    this.copy(maybeRobot = Some(robot))
  }
}

package com.sharon

trait GameApi [GameType <: GameApi[GameType]]{
  def report: Option[String]
  def place(coOrds: CoOrds, facing: Direction): GameType
  def move: GameType
  def turn(rotation: Rotation): GameType
}

trait Command
case class Place(coOrds: CoOrds, facing: Direction) extends Command
case object Move extends Command
case class Turn(rotation: Rotation) extends Command
case object Report extends Command

case class CommandResult1(output: Option[String], game: Game)

case class Game(table: Table, maybeRobot: Option[Robot]) extends GameApi[Game] {

  def execute(command: Command):CommandResult1 = command match {
    case Place(coOrds: CoOrds, facing: Direction) => CommandResult1(None, place(coOrds, facing))
    case Move => CommandResult1(None, move)
    case Turn(rotation: Rotation) => CommandResult1(None, turn(rotation))
    case Report => CommandResult1(report, this)
  }
  
  def report: Option[String] = {
    maybeRobot map { case Robot(position, direction) =>
        s"${position.xPos},${position.yPos},${direction.toString.toUpperCase}"
    }
  }

  def place(coOrds: CoOrds, facing: Direction): Game = {
    whenValidPosition(coOrds) {newPos =>
      newGame(Robot(newPos, facing))
    }
  }

  def move: Game = {
    whenRobotPlaced { case Robot(position, facing) =>
      whenValidPosition(position.move(facing)) {newPos =>
        newGame(Robot(newPos, facing))
      }
    }
  }

  def turn(rotation: Rotation): Game = {
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

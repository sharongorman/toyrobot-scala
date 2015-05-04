package com.sharon

import scala.util.Try

object CommandInterpreter {

  def interpret(commandString: String): Option[Command] = commandString match {
    case "REPORT" => Some(Report)
    case "LEFT" => Some(Turn(Left))
    case "RIGHT" => Some(Turn(Right))
    case "MOVE" => Some(Move)
    case placePattern(x,y, facing) => interpretPlace(x, y, facing)
    case _ => None
  }

  private def interpretPlace(x: String, y: String, facing: String): Option[Command] = for {
    xInt <- Try(x.toInt).toOption
    yInt <- Try(y.toInt).toOption
    direction <- interpretDirection(facing)
  } yield Place(CoOrds(xInt, yInt), direction)

  private def interpretDirection(dirString: String): Option[Direction] = dirString match {
    case "NORTH" => Some(North)
    case "SOUTH" => Some(South)
    case "WEST" => Some(West)
    case "EAST" => Some(East)
    case _ => None
  }

  private val placePattern = """PLACE (\d+),(\d+),(NORTH|SOUTH|EAST|WEST)""".r
}


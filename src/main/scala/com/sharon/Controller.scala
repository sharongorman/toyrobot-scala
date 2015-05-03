package com.sharon

import scala.util.Try

object Controller {
  def processCommand[GameType <:GameApi[GameType]](game: GameType, command: String): CommandResult[GameType] = command match {
    case "REPORT" => CommandResult(game.report, game)
    case "LEFT" => CommandResult(None, game.turn(Left))
    case "RIGHT" => CommandResult(None, game.turn(Right))
    case "MOVE" => CommandResult(None, game.move)
    case placePattern(x, y, facing) => processPlaceCommand(x, y, facing, game) getOrElse doNothing(game)
    case _ => doNothing(game)
  }

  private def doNothing[GameType <:GameApi[GameType]](game: GameType): CommandResult[GameType] = {
    CommandResult(None,game)
  }

  private def processPlaceCommand[GameType <:GameApi[GameType]](x: String, y: String, facing: String, game: GameType) : Option[CommandResult[GameType]] =  for {
        xInt <- Try(x.toInt).toOption
        yInt <- Try(y.toInt).toOption
        direction <- parseDirection(facing)
  } yield CommandResult(None, game.place(CoOrds(xInt, yInt), direction))

  private def parseDirection(dirString: String): Option[Direction] = dirString match {
    case "NORTH" => Some(North)
    case "SOUTH" => Some(South)
    case "WEST" => Some(West)
    case "EAST" => Some(East)
    case _ => None
  }

  private val placePattern = """PLACE (\d+),(\d+),(NORTH|SOUTH|EAST|WEST)""".r
}

case class CommandResult[GameType <: GameApi[GameType]](output: Option[String], game: GameType)

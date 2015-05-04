package com.sharon

trait Command
case class Place(coOrds: CoOrds, facing: Direction) extends Command
case object Move extends Command
case class Turn(rotation: Rotation) extends Command
case object Report extends Command

case class CommandResult(output: Option[String], game: Game)

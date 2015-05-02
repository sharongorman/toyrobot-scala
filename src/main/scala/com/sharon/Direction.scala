package com.sharon

sealed trait Direction {
  def turn(rotation: Rotation) = {
    val increment = rotation match {
      case Left => 3
      case Right =>1
    }
    val directions = Array(North, East, South, West)
    directions((directions.indexOf(this) + increment) % 4)
  }
}

case object North extends Direction
case object South extends Direction
case object West extends Direction
case object East extends Direction

sealed trait Rotation
case object Left extends Rotation
case object Right extends Rotation




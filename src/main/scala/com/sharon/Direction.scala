package com.sharon

sealed trait Direction {
  def left = {
    val directions = Array(North, East, South, West)
    directions((directions.indexOf(this) + 3) % 4)
  }

  def right = {
    val directions = Array(North, East, South, West)
    directions((directions.indexOf(this) + 1) % 4)
  }
}

case object North extends Direction
case object South extends Direction
case object West extends Direction
case object East extends Direction




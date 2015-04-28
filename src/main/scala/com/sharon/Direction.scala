package com.sharon


//object Direction  {
//  val directions = Array(North, East, South, West)
//  def left = {
//    Direction.directions((Direction.directions.indexOf(this)) -1 % 4)
//  }
//
//  def right = {
//    Direction.directions((Direction.directions.indexOf(this)) -1 % 4)
//  }
//  val North = Direction(0,1)
//  val South = Direction(0,-1)
//  val East = Direction(1,0)
//  val West = Direction(-1,0)
//}

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




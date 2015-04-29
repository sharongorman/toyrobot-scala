package com.sharon

case class CoOrds(xPos: Int, yPos: Int) {

  def move(direction: Direction): CoOrds = {
    val (xMovement, yMovement) = direction match {
      case North => (0, 1)
      case South => (0, -1)
      case East  => (1, 0)
      case West  => (-1, 0)
    }
    CoOrds(xPos + xMovement, yPos + yMovement)
  }
}

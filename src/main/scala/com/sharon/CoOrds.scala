package com.sharon

/**
 * Created by sharon.holliday on 4/28/15.
 */
case class CoOrds(xPos: Int, yPos: Int) {
  def move(direction: Direction): CoOrds = {
    val movement = Map(
      North -> (0, 1),
      South -> (0, -1),
      East -> (1, 0),
      West -> (-1, 0)
    )
    val (xMovement, yMovement) = movement(direction)
    CoOrds(xPos + xMovement, yPos + yMovement)
  }
}

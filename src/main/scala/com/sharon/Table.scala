package com.sharon

/**
 * Created by sharon.holliday on 4/28/15.
 */
case class Table(xSize: Int, ySize: Int)  {
  val xRange = 0 until xSize
  val yRange = 0 until ySize

  def isOn_:(coOrds: CoOrds): Boolean = {
    (xRange contains coOrds.xPos) && (yRange contains coOrds.yPos)
  }
}

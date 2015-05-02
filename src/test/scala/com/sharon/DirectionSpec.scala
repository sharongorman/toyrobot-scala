package com.sharon

import org.specs2.mutable._
class DirectionSpec extends Specification {

  "should turn left" in {

    "west is left of north" in {North.turn(Left) === West}
    "south is left of west" in {West.turn(Left) === South}
    "east is left of south" in {South.turn(Left) === East}
    "north is left of east" in {East.turn(Left) === North}
  }

  "should turn right" in {
    "east is right of north" in {North.turn(Right) === East}
    "south is right of east" in {East.turn(Right) === South}
    "west is right of south" in {South.turn(Right) === West}
    "north is right of west" in {West.turn(Right) === North}
  }
}

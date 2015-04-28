package com.sharon

import org.specs2.mutable._
/**
 * Created by sharon.holliday on 4/25/15.
 */
class DirectionSpec extends Specification {


  "should turn left" in {

    "west is left of north" in {North.left === West}
    "south is left of west" in {West.left === South}
    "east is left of south" in {South.left === East}
    "north is left of east" in {East.left === North}
  }

  "should turn right" in {
    "east is right of north" in {North.right === East}
    "south is right of east" in {East.right === South}
    "west is right of south" in {South.right === West}
    "north is right of west" in {West.right === North}
  }
}

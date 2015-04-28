package com.sharon

import org.specs2.mutable._
/**
 * Created by sharon.holliday on 4/28/15.
 */
class CoOrdsSpec extends Specification {
  "north of 1,1 is 1,2" in {
    CoOrds(1,1).move(North) === CoOrds(1,2)
  }

  "west of 1,1 is 0,1" in {
    CoOrds(1,1).move(West) === CoOrds(0,1)
  }

  "south of 1,1 is 1,0" in {
    CoOrds(1,1).move(South) === CoOrds(1,0)
  }

  "east of 1,1 is 1,2" in {
    CoOrds(1,1).move(East) === CoOrds(2,1)
  }

}

package com.sharon

import org.specs2.mutable.Specification

/**
 * Created by sharon.holliday on 4/28/15.
 */
class TableSpec extends Specification {
  "for a table 5x5" >> {
    val table = new Table(5, 5)
    "0,0 is on the table" in {
      (CoOrds(0,0) isOn_:  table) === true
    }

    "4,4 is on the table" in {
      (CoOrds(4,4) isOn_:  table) === true
    }

    "5,0 is off the table" in {
      (CoOrds(5,0) isOn_:  table) === false
    }

    "0,5 is off the table" in {
      (CoOrds(0,5) isOn_:  table) === false
    }
  }
}

package com.sharon

import org.specs2.mutable.Specification
import CommandInterpreter._


class CommandInterpreterSpec extends Specification {
  "Describe Controller" >> {
    "it receives valid commands" >> {
      "place command" in {
        interpret("PLACE 3,5,WEST") should beSome(Place(CoOrds(3, 5), West))
      }

      "move command" in {
        interpret("MOVE") should beSome(Move)
      }

      "turn left command" in {
        interpret("LEFT") should beSome(Turn(Left))
      }

      "turn right command" in {
        interpret("RIGHT") should beSome(Turn(Right))
      }

      "report" in {
        interpret("REPORT") should beSome(Report)
      }
    }
  }
}

package com.sharon

import org.specs2.mutable.Specification


class ControllerSpec extends Specification {
  "Describe Controller" >> {
    val game = new MockGame(None)
    "it receives valid commands" >> {
      "place command" in {
        val CommandResult(_, updatedGame) = Controller.processCommand(game, "PLACE 3,5,WEST")
        updatedGame.receivedCommand should beSome(Place(CoOrds(3, 5), West))
      }

      "move command" in {
        val CommandResult(_, updatedGame) = Controller.processCommand(game,"MOVE")
        updatedGame.receivedCommand should beSome(Move)
      }

      "turn left command" in {
        val CommandResult(_, updatedGame) = Controller.processCommand(game,"LEFT")
        updatedGame.receivedCommand should beSome(Turn(Left))
      }

      "turn right command" in {
        val CommandResult(_, updatedGame) = Controller.processCommand(game,"LEFT")
        updatedGame.receivedCommand should beSome(Turn(Left))
      }

      "report" in {
        val CommandResult(output, updatedGame) = Controller.processCommand(game,"REPORT")
        output should beSome("Dummy output")
      }
    }
  }

  private case class MockGame(receivedCommand: Option[Command]) extends GameApi[MockGame] {
    def report: Option[String] = Some("Dummy output")
    def move: MockGame = MockGame(Some(Move))
    def place(coOrds: CoOrds, facing: Direction): MockGame = MockGame(Some(Place(coOrds, facing)))
    def turn(rotation: Rotation): MockGame = MockGame(Some(Turn(rotation)))
  }

  private trait Command
  private case class Place(coOrds: CoOrds, facing: Direction) extends Command
  private case object Move extends Command
  private case class Turn(rotation: Rotation) extends Command
  private case object Report extends Command

}

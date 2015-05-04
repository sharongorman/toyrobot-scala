package com.sharon

object Main {
  def main(args: Array[String]) {
    val game = Game(Table(5,5), None)
    processLoop(game)
  }

  def processLoop(game: Game): Unit = {
    val ln = scala.io.StdIn.readLine("Next command please:")
    val CommandResult(output, updatedGame) = processCommand(game, CommandInterpreter.interpret(ln))
    output map println
    processLoop(updatedGame)
  }

  def processCommand(game: Game, maybeCommand : Option[Command]): CommandResult = maybeCommand match {
    case None => CommandResult(None, game)
    case Some(command) => game.execute(command)
  }
}


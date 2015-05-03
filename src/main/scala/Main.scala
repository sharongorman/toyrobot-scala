package com.sharon

object Main {
  def main(args: Array[String]) {
    val game = Game(Table(5,5), None)
    processLoop(game)
  }

  def processLoop(game: Game): Unit = {
    val ln = scala.io.StdIn.readLine("Next command please:")
    val CommandResult(output, newGame) = Controller.processCommand(game, ln)
    output map println
    processLoop(newGame)
  }
}


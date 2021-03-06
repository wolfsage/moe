package org.moe.runtime

class MoeSystem(
   private var STDOUT : java.io.PrintStream    = Console.out,
   private var STDIN  : java.io.BufferedReader = Console.in,
   private var STDERR : java.io.PrintStream    = Console.err
 ) {

  def getSTDIN  = STDIN
  def getSTDOUT = STDOUT
  def getSTDERR = STDERR

  def getEnv = sys.env

  def exit ()            = sys.exit()
  def exit (status: Int) = sys.exit(status)
}

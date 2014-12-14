package propaganda {
  object Propaganda {
    def main(args: Array[String]): scala.Unit = {
      println("Starting the Propaganda!")
      var c = 3
      val v = Set(1, 2, 3)
      val h = new Hanoi(new Tower(0), new Tower(1), new Tower(2))
      h.init
      println("Initial " + h)
      h.solve
      println("Result " + h)
    }
  }
}
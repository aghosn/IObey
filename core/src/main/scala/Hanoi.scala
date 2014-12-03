package propaganda
import scala.collection.mutable.Stack

class Hanoi(origin: Tower, end: Tower, buffer: Tower) {

  def init {
    var c = 3
    var v  = List.range(0, 5).reverse
    v.foreach(i => origin.add(i))
  }  

  def solve: Unit = {
    origin.moveDisks(5, end, buffer)
  }

  override def toString = "{"+origin + ", "+buffer+", "+end+"}"  
}
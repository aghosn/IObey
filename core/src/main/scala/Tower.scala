package propaganda
import scala.collection.mutable.Stack

class Tower(index: Integer) {
  val disks = new Stack[Integer]()
  
  def getIndex: Integer = index

  def add(d: Integer) {
    if(!disks.isEmpty && disks.head <= d)
      throw new Exception(s"${index}:Error placing the disk ${d} on top of ${disks.head}")
    else 
      disks.push(d)
  }

  def moveToTop(t: Tower ) {
    val top = disks.pop
    t.add(top)
    //println(s"${index}:Moved disk ${top} from ${index} to ${t.getIndex}")
  }

  def moveDisks(n: Integer, dest: Tower, buff: Tower) {
    if(n > 0) {
      moveDisks(n - 1, buff, dest)
      moveToTop(dest)
      buff.moveDisks(n- 1, dest, this)
    }
  }

  override def toString = "["+index+":"+disks+"]"
}
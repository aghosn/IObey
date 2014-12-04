import scala.meta.internal.ast._
import tqlscalameta.ScalaMetaTraverser._
import scala.obey.model._
import scala.obey.tools.Utils._
import scala.language.reflectiveCalls

@Tag("Var") @Tag("Perso") object MyRule extends Rule {
  val name = "Follow the leader"

  def message(t: String): Message = Message(s"The 'var' $t was never reassigned and should therefore be a 'val'")

  def apply: Matcher[List[Message]] = {
    collectIn[Set] {
      case Term.Assign(Term.Name(b), _) => 
      println("Taking this shit: "+b)
      b
    }.down feed { assign =>
      (collect {
        case Defn.Var(_, Term.Name(b) :: Nil, _, _) if (!assign.contains(b)) => message(b)
      } <~
        update {
          case Defn.Var(a, Term.Name(b) :: Nil, c, Some(d)) if (!assign.contains(b)) =>
            Defn.Val(a, Term.Name(b) :: Nil, c, d)
        }).down
    }
  }
}
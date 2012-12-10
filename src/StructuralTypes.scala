
object StructuralTypes {
  type T = {
     type X
     def x : X
  }

  object Foo {
    type X = Int
    def x: X = 42
  }

  object Bar {
    type X = String
    def x: X = ""
  }

  def test1(t: T): t.X = t.x
  def test2(t: T): T#X = t.x


  val i: Int = test1(Foo)
  val f: T#X = test2(Foo)


//  val k: Int = test2(Foo)
//
//  val j: Int = f
//  val g: T#X = test2(Bar)




}

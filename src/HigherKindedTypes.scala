object HigherKindedTypes extends App {

  type Callback[T] = Function[T, Unit]

  val x : Callback[Int] =
    y => println(y + 2)

  x(1)

  def foo[M[_]](f: M[Int]) { println(f) }
  foo(x)
  foo[Callback](x)
//  foo[Function1](x)

//  foo[({type X[Y] = Function[Y, Unit]})#X]((x: Int) => println(x))
}

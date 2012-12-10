object PathDependentTypes {

  class Outer {

    trait Inner

    def x = new Inner {}

    def foo(inner: this.Inner) {}
    def bar(inner: Outer#Inner) {}
  }

  val a = new Outer
  val b = new Outer

  a.foo(a.x)
//  a.foo(b.x)
  a.bar(a.x)
}

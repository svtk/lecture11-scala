object CompoundTypes {

  trait Foo
  trait Bar

  trait SomeTrait {
    type Abstract
    type Concrete  = Foo
    type Compound =
              Abstract with Foo with Bar
  }

  trait X
  class SomeClass extends SomeTrait {
    type Abstract = X
  }

  def test() {
    val s : SomeClass#Compound =
      new X with Foo with Bar {}
  }
}

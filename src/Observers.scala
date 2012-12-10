import collection.mutable

object Observers extends App {

  trait Observable {
    type Handle
    private val listeners =
      new mutable.HashMap[Handle, this.type => Unit]

    def observe(callback: this.type => Unit): Handle = {
      val handle = createHandle(callback)
      listeners += (handle -> callback)
      handle
    }

    def unobserve(handle: Handle) {
      listeners -= handle
    }

    protected def createHandle(callback: this.type => Unit)
                                 : Handle
    protected def notifyListeners() {
      for (callback <- listeners.values) callback(this)
    }
  }


  trait DefaultHandles extends Observable {
    type Handle = (this.type => Unit)
    protected def createHandle(callback : this.type => Unit)
                                 : Handle =
      callback
  }


  class IntStore(private var value: Int)
      extends Observable with DefaultHandles {

    def get() : Int = value
    def set(newValue: Int) {
      value = newValue
      notifyListeners()
    }

    override def toString = s"IntStore($value)"
  }

  val x = new IntStore(1)
  val handle: x.Handle = x.observe(println)

  x.set(2)
  x.unobserve(handle)
  x.set(4)

  val y = new IntStore(2)
//  y.unobserve(handle)

  val callback = println(_ : Any)
  val xHandle : x.Handle = x.observe(callback)
  val yHandle : y.Handle = y.observe(callback)

//  y.unobserve(xHandle)
//  y.unobserve(yHandle)

  println(xHandle == yHandle)

}

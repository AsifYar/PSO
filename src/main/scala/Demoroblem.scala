class Demoroblem {
  def Sphere(x :scala.collection.immutable.Vector[Double]) : Double = {
    import breeze.linalg.sum

    //takes an element and first square it than sums it

    val power = x.map(math.pow(_, 2))

    return  sum(power)
  }
}

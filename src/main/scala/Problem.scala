//import breeze.linalg.{max, sum}
import breeze.linalg._

import breeze.numerics._
class Problem {

  def Sphere(x :DenseVector[Double]) : Double = {

    //takes an element and first square it than sums it

    val power = x.map(math.pow(_, 2))

    return  sum(power)
  }

  def Ackley(x: DenseVector[Double]): Double =
  {
    val a=20
    val b=0.2
    val c=2 * constants.Pi
    var cols =x.length;
    var inverseCols = 1.0/cols
    val power = x.map(math.pow(_, 2))
    var sum1  = sum(power)
    var y =  -a * exp(-b * sqrt(inverseCols * sum1)) -  exp(inverseCols * sum(cos(c * x))) +a + constants.E
    y
  }
  def SumOfsquare(x :DenseVector[Double]) : Double = {
    var res=0.0

    for(i <- 0 to x.length-1  )
    {
      var index = i + 1
      res =res + index * (x(i) * x(i))
    }
    res
  }
}

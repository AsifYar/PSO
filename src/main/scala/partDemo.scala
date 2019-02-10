import breeze.linalg.{DenseMatrix, sum}
import breeze.numerics.{constants, cos, exp, sqrt}
object partDemo {
  def main(args: Array[String]): Unit = {
    import breeze.linalg.{DenseVector, inv}
    var x:DenseVector[Double] =DenseVector(1.0,2.0,3.0)
    val c=2 * constants.Pi
    var col =x.length
    println(col)
    var invr = 1.0 / col
    println(invr)
    println(exp(invr * sum(cos(c * x))))


  }

}

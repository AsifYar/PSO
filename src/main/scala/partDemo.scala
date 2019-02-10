import breeze.linalg.{DenseMatrix, sum}
import breeze.numerics.{constants, cos, exp, sqrt}
object partDemo {

  import breeze.linalg.DenseVector

  def main(args: Array[String]): Unit = {
    var dense:DenseVector[Double] = DenseVector(10,11,13,14)
    print(Sumsqr(dense))
  }
  def Sumsqr(x :DenseVector[Double]) : Double = {
    var res=0.0

    for(i <- 0 to x.length-1  )
      {
        var index = i + 1
        res =res + index * (x(i) * x(i))
      }
    res
  }

}

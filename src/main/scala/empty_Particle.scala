
import breeze.linalg._

class empty_Particle(dimension :Int) {

  var Position =      DenseVector[Double](dimension)
  var Velocity =      DenseVector[Double](dimension)
  var Cost =          math.random

  var BestPosition =  DenseVector[Double](dimension) //Array[Double]()
  var BestCost =      math.random

}

import breeze.linalg._
import breeze.linalg.{max, sum}
object SpherePSO {

  def main(args: Array[String]): Unit = {
    import java.util.concurrent.ThreadLocalRandom
    import scala.collection.mutable.ArrayBuffer

    var problem = new Problem()

    //Problem Definition
    println("***Sphere function optimization with PSO***")
    println("***Enter values for following arguments***")
    println()
    print("Enter Lower Bound (Recommended value= -5.12) = ")
    var MinVal = scala.io.StdIn.readDouble();
    println()
    print("Enter Upper Bound (Recommended value= 5.12) = ")
    var MaxVal = scala.io.StdIn.readDouble();
    println()
    print("Number of Dimensions(d) = ")
    var nvar = scala.io.StdIn.readInt(); //Dimensions ; Number of unknown Decision variables
    println("Number of Dimensions(d) = " + nvar)
    //var MinVal = -512; //Lower Bound of Decision variables
    //var MaxVal = 512 //Upper Bound of Decision variables

    //Parameters for PSO

    var kappa = 1;
    var phi1 = 2.05
    var phi2 = 2.05
    var phi = phi1 + phi2
    var chi = 2 * kappa / math.abs(2 - phi - math.sqrt(math.pow(phi, 2) - 4 * phi))

    print("Number of Maximum Iterations = ")
    var MaxIt = scala.io.StdIn.readInt() //Maximum number of iteration
    println("Number of Maximum Iterations = " + MaxIt)

    print("Population (SWARM) Size = ")
    var nPop = scala.io.StdIn.readInt() //Population Size (Swarm Size)
    println("Population (SWARM) Size = " + nPop)
    println()
    println("Press any key to Start")
    scala.io.StdIn.readLine()

    var w: Double = chi //Inertia
    val c1 = chi * phi1
    val c2 = chi * phi2


    //Define Max and min velocity for particle
    var MaxVelocity:Double = 0.2 * (MaxVal - MinVal)
    var MinVelocity:Double = -MaxVelocity

    //Initialization

    val random: ThreadLocalRandom = ThreadLocalRandom.current();
    var particle = new ArrayBuffer[empty_Particle](nPop) //create a particle from empty_Particle structure / class

    var GlobalBest_Cost = Double.PositiveInfinity
    var GlobalBest_Position = DenseVector.fill(nvar)(math.random)


    for (i <- 0 to nPop) {
      particle.append(new empty_Particle(nvar))
      particle(i).Position = DenseVector.fill(nvar)(random.nextDouble(MinVal, MaxVal + 1)) //Array.fill(nvar) (random.nextDouble(MinVal , MaxVal + 1))
      particle(i).Cost =problem.Sphere(particle(i).Position)
      particle(i).Velocity = DenseVector.fill(nvar)(0.0)

      //update personal best

      particle(i).BestPosition = particle(i).Position
      particle(i).BestCost = particle(i).Cost

      //Update Global Best
      if (particle(i).BestCost < GlobalBest_Cost) {
        GlobalBest_Cost = particle(i).BestCost
        GlobalBest_Position = particle(i).BestPosition
      }
    }
    //Main loop of PSO

    for (iteration: Int <- 0 to MaxIt-1) // for all iteration
    {
      for (i <- 0 to nPop-1) //for every member of population/swarm we have to iterate
      {
        //update velocity
        particle(i).Velocity = (w * particle(i).Velocity) +
          c1 * DenseVector.fill(nvar)(math.random) .* (particle(i).BestPosition - particle(i).Position) +
          c2 * DenseVector.fill(nvar)(math.random) .* (GlobalBest_Position - particle(i).Position)

        //Limits for Velocity
        //particle(i).Velocity = particle(i).Velocity.min(MaxVelocity)

        particle(i).Velocity = max(particle(i).Velocity, MinVelocity)
        particle(i).Velocity = min(particle(i).Velocity, MaxVelocity)

        //update position
        particle(i).Position = particle(i).Position + particle(i).Velocity

        //Apply upper and lower bound limits to position of particle
        //position of particle(i) should not less than lower bound
        particle(i).Position = max( particle(i).Position,MinVal.toDouble) //Lower bound
        particle(i).Position = min( particle(i).Position,MaxVal.toDouble) //upper bound

        //Evaluation
        particle(i).Cost = problem.Sphere(particle(i).Position)

        //update personal best
        if (particle(i).Cost < particle(i).BestCost) //check for best cost and position till now gained by particle
        {
          particle(i).BestPosition = particle(i).Position;
          particle(i).BestCost = particle(i).Cost

          //if ith particle is better than its best,it is probability it will be best than Global Best

          if (particle(i).BestCost < GlobalBest_Cost) {
            GlobalBest_Cost = particle(i).BestCost
            GlobalBest_Position = particle(i).BestPosition
          }
        }
      }
    }
    println("Global Best Cost :" + GlobalBest_Cost)
    println("Global Best Position :" + GlobalBest_Position)

    for (x <- 0 to nPop - 1) {
      println("particle " + x + " Current Position=" + particle(x).Position + " : Cost=" + particle(x).Cost + " : Velocity=" + particle(x).Velocity + " : Best Position=" + particle(x).BestPosition + " : BestCost=" + particle(x).BestCost)
    }
  }
}
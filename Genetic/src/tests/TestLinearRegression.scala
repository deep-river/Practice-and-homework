package tests

import org.scalatest._
import genetics.geometry._
import genetics.GeneticAlgorithm._

class TestLinearRegression extends FunSuite {

  val EPSILON: Double = 0.1

  def equalLines(l1: Line, l2: Line): Boolean = {
    (l1.slope - l2.slope).abs < EPSILON
    (l1.yIntercept - l2.yIntercept).abs < EPSILON
  }

  test("TestLinearRegression") {
    val test_list01 = List(new Point(1, 1), new Point(3, 3), new Point(5, 5), new Point(7, 7), new Point(10, 10), new Point(12, 12), new Point(15, 15))
    val line01 = new Line(1, 0) // y = x
    val test_list02 = List(new Point(-5, -14), new Point(-4, -11), new Point(-3, -8), new Point(-2, -5), new Point(-1, -2), new Point(0, 1), new Point(1, 4), new Point(2, 7), new Point(4, 13))
    val line02 = new Line(3, 1) // y = 3x + 1
    val test_list03 = List(new Point(-4, 15), new Point(-1, 0), new Point(2, -15), new Point(5, -30), new Point(-3, 10), new Point(0, -5), new Point(3, -20), new Point(4, -25))
    val line03 = new Line(-5, -5) // y = -5x - 5

    assert(equalLines(linearRegression(test_list01), line01), "line01")
    assert(equalLines(linearRegression(test_list02), line02), "line02")
    assert(equalLines(linearRegression(test_list03), line03), "line03")
  }
}
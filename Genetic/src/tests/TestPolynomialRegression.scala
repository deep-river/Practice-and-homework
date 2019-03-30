package tests

import org.scalatest._
import genetics.geometry._
import genetics.GeneticAlgorithm._

class TestPolynomialRegression extends FunSuite {

  val EPSILON: Double = 0.1

  def equalPolynomial(l1: Polynomial, l2: Polynomial): Boolean = {
    var flag: Boolean = false
    for (index <- 1 to l1.coefficients.length) {
      flag = (l1.coefficients.apply(index - 1) - l2.coefficients.apply(index - 1)).abs < EPSILON
    }
    flag
  }

  test("TestPolynomialRegression") {
    val test_list01 = List(new Point(-1, 1), new Point(1, 3), new Point(3, 13), new Point(-3, 7), new Point(-5, 21), new Point(5, 31))
    val line01 = new Polynomial(List(1, 1, 1)) // y = x^2^ + x + 1
    val test_list02 = List(new Point(-4, -159), new Point(-3, -62), new Point(-2, -15), new Point(-1, 0), new Point(0, 1), new Point(1, 6), new Point(2, 33), new Point(3, 100))
    val line02 = new Polynomial(List(1, 0, 2, 3)) // y = 3x^3^ + 2x^2^ + 1
    val test_list03 = List(new Point(-4, 75), new Point(-3, 20), new Point(-2, 21), new Point(-1, 6), new Point(0, -1), new Point(1, 0), new Point(2, 9), new Point(3, 26))
    val line03 = new Polynomial(List(-1, -3, 4)) // y = 4x^2^ - 3x -1
    //val test_list04 = List()
    //val line04 = new Polynomial(List())

    assert(equalPolynomial(polynomialRegression(test_list01, 2), line01), "line01")
    assert(equalPolynomial(polynomialRegression(test_list02, 3), line02), "line02")
    assert(equalPolynomial(polynomialRegression(test_list03, 2), line03), "line03")
  }
}
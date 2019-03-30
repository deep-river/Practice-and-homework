package genetics

import genetics.genes.{Animal, Gene}
import geometry._
import scala.math.{Pi, random, tan}


object GeneticAlgorithm {

  def geneticAlgorithm[T](fitnessFunc: T => Double, convertFunc: List[Gene] => T, sample: List[Gene]): T = {
    val genes_per_animal = sample.length //Step 1.1
    var generated_animals: List[Animal] = generateRandomAnimals(20, genes_per_animal) //Step 1.2
    /*
    for (animal <- generated_animals) {
      animal.fitness = fitnessFunc(convertFunc(animal.genes)) //Step 2&3
    }
    val animalsSorted = generated_animals.sortWith(compareAnimals)  //sort animals by fitness, fitness not converted to [0,1]
    //setup next generation
    var next_gen: List[Animal] = List()
    next_gen = next_gen :+ animalsSorted.head
    next_gen = next_gen :+ mutate(animalsSorted.head)
    next_gen = next_gen :+ mutate(animalsSorted.head)
    next_gen = next_gen :+ mutate(animalsSorted.apply(1))
    next_gen = next_gen ::: combine(next_gen)
    next_gen = next_gen ::: generateRandomAnimals(10, genes_per_animal)
    */
    for (runtime <- 1 to 10000) {
      for (animal <- generated_animals) {
        animal.fitness = fitnessFunc(convertFunc(animal.genes)) //Step 2&3
      }
      val animalsSorted = generated_animals.sortWith(compareAnimals)  //sort animals by fitness, fitness not converted to [0,1]
      //setup next generation
      var next_gen: List[Animal] = List()
      next_gen = next_gen :+ animalsSorted.head
      next_gen = next_gen :+ mutate(animalsSorted.head)
      next_gen = next_gen :+ mutate(animalsSorted.head)
      next_gen = next_gen :+ mutate(animalsSorted.apply(1))
      next_gen = next_gen ::: combine(next_gen)
      next_gen = next_gen ::: generateRandomAnimals(10, genes_per_animal)
      generated_animals = next_gen
    }
    val final_genes = generated_animals.head.genes
    convertFunc(final_genes)
  }

  def compareAnimals (a1: Animal, a2: Animal): Boolean = {
    a1.fitness < a2.fitness
  }

  def generateRandomAnimals (number: Int, genes_needed: Int): List[Animal] = {
    var generated_animals: List[Animal] = List()
    var generated_genes: List[Gene] = List()
    for (x <- 1 to number) {
      //generated_genes = List()
      for (y <- 1 to genes_needed) {
        generated_genes = generated_genes :+ new Gene(random())
      }
      generated_animals = generated_animals :+ new Animal(generated_genes)
      generated_genes = List()
    }
    generated_animals
  }

  def mutate (original: Animal): Animal = {
    var mutated_genes: List[Gene] = List()
    for (gene <- original.genes) {
      var variation: Double = gene.geneValue + (random() - 0.5) * 0.01
      while (1.0 < variation || variation < 0.0) {
        variation = gene.geneValue + (random() - 0.5) * 0.01
      }
      mutated_genes = mutated_genes :+ new Gene(variation)
    }
    new Animal(mutated_genes)
  }

  def combine (parents: List[Animal]): List[Animal] = {
    var combined_animals: List[Animal] = List()
    for (i <- 0 until parents.length - 1) {
      for (j <- i + 1 until parents.length){
        //var gene1: Gene = new Gene((parents.apply(i).genes.head.geneValue + parents.apply(j).genes.head.geneValue) * 0.5)
        //var gene2: Gene = new Gene((parents.apply(i).genes.apply(1).geneValue + parents.apply(j).genes.apply(1).geneValue) * 0.5)
        //combined_animals = combined_animals :+ new Animal(List(gene1, gene2))
        var gene_list: List[Gene] = List()
        for (gene <- 1 to parents.head.genes.length) {
          gene_list = gene_list :+ new Gene((parents.apply(i).genes.apply(gene - 1).geneValue + parents.apply(j).genes.apply(gene - 1).geneValue) * 0.5)
        }
      }
    }
    combined_animals
  }
/*
  def fitnessFunc (points: List[Point]): Line => Double = {

    obj: Line => {
      var sum: Double = 0.0
      for (point <- points) {
        sum += (obj.evaluate(point.x) - point.y).abs
      }
      sum
    }
  }
*/

  def fitnessFuncLinear (points: List[Point]): Line => Double = {

    obj: Line => {
      var sum: Double = 0.0
      for (point <- points) {
        sum += (obj.evaluate(point.x) - point.y).abs
      }
      sum
    }
  }

  def fitnessFuncPolynomial (points: List[Point]): Polynomial => Double = {

    obj: Polynomial => {
      var sum: Double = 0.0
      for (point <- points) {
        sum += (obj.evaluate(point.x) - point.y).abs
      }
      sum
    }
  }

  //def fitnessFunc[T] (fit: T => Double):

  def convertFuncLinear (genes: List[Gene]): Line = {
    val slope = tan((genes.head.geneValue - 0.5) * Pi)
    val y_intercept = tan((genes.apply(1).geneValue - 0.5) * Pi)
    new Line(slope, y_intercept)
  }

  def convertFuncPolynomial (genes: List[Gene]): Polynomial = {
    var coefficients: List[Double] = List()
    for (gene <- genes) {
      coefficients = coefficients :+ tan((gene.geneValue - 0.5) * Pi)
    }
    new Polynomial(coefficients)
  }

  def linearRegression (points: List[Point]): Line = {
    val sample: List[Gene] = List(new Gene(random()), new Gene(random()))
    geneticAlgorithm(fitnessFuncLinear(points), convertFuncLinear, sample)
  }

  def polynomialRegression (points: List[Point], degree: Int): Polynomial = {
    var sample: List[Gene] = List()
    for (gene <- 1 to degree + 1) {
      sample = sample :+ new Gene(random())
    }
    geneticAlgorithm(fitnessFuncPolynomial(points), convertFuncPolynomial, sample)
  }
}

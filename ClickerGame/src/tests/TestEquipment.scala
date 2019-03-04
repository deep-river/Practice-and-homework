package tests

import org.scalatest._
import clicker.equipment.{Shovels, Excavators, GoldMines}

class TestEquipment extends FunSuite{

  def ~=(x: Double, y: Double, precision: Double) = {
    if ((x - y).abs < precision) true else false
  }

  test("TestEquipment"){
    val shovelTest = new Shovels
    assert((shovelTest.goldPerSecond() - 0.0).abs < 0.001, "shovelgoldPerSecond0")
    assert((shovelTest.goldPerClick() - 0.0).abs < 0.001, "shovelgoldPerClick0")
    assert((shovelTest.costOfNextPurchase() - 10.0).abs < 0.001, "shovelcostOfNextPurchase0")
    shovelTest.buy()
    assert((shovelTest.goldPerSecond() - 0.0).abs < 0.001, "shovelgoldPerSecond1")
    assert((shovelTest.goldPerClick() - 1.0).abs < 0.001, "shovelgoldPerClick1")
    assert((shovelTest.costOfNextPurchase() - 10.5).abs < 0.001, "shovelcostOfNextPurchase1")


    val excaTest = new Excavators
    assert((excaTest.goldPerSecond() - 0.0).abs < 0.001, "ExcavatorsgoldPerSecond0")
    assert((excaTest.goldPerClick() - 0.0).abs < 0.001, "ExcavatorsgoldPerClick0")
    assert((excaTest.costOfNextPurchase() - 200.0).abs < 0.001, "ExcavatorscostOfNextPurchase0")
    excaTest.buy()
    assert((excaTest.goldPerSecond() - 10.0).abs < 0.001, "ExcavatorsgoldPerSecond1")
    assert((excaTest.goldPerClick() - 20.0).abs < 0.001, "ExcavatorsgoldPerClick1")
    assert((excaTest.costOfNextPurchase() - 220.0).abs < 0.001, "ExcavatorscostOfNextPurchase1")


    val gmTest = new GoldMines
    assert((gmTest.goldPerSecond() - 0.0).abs < 0.001, "GoldMinesgoldPerSecond0")
    assert((gmTest.goldPerClick() - 0.0).abs < 0.001, "GoldMinesgoldPerClick0")
    assert((gmTest.costOfNextPurchase() - 1000.0).abs < 0.001, "GoldMinescostOfNextPurchase0")
    gmTest.buy()
    assert((gmTest.goldPerSecond() - 100.0).abs < 0.001, "GoldMinesgoldPerSecond1")
    assert((gmTest.goldPerClick() - 0.0).abs < 0.001, "GoldMinesgoldPerClick1")
    assert((gmTest.costOfNextPurchase() - 1100.0).abs < 0.001, "GoldMinescostOfNextPurchase1")
  }
}

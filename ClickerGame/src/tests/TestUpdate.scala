package tests

import org.scalatest._
import clicker.Game

class TestUpdate extends FunSuite{

  test("TestUpdate"){
    val gameTest = new Game
    assert((gameTest.goldPerSecond() - 0.0).abs < 0.0001, "goldPerSecond0")
    assert((gameTest.goldPerClick() - 1.0).abs < 0.0001, "goldPerClick0")
    gameTest.clickGold()
    assert((gameTest.gold - 1.0).abs < 0.0001, "gold0")
    gameTest.buyEquipment("shovel")
    assert(gameTest.equipment("shovel").numberOwned == 0, "shovelAmount0")
    gameTest.update(System.nanoTime())
    assert((gameTest.gold - 1.0).abs < 0.0001, "update0")

    //buy 1 shovel, 1 excavator and 1 mine
    gameTest.gold = 1210.0
    gameTest.buyEquipment("shovel")
    gameTest.buyEquipment("excavator")
    gameTest.buyEquipment("mine")
    assert((gameTest.goldPerSecond() - 110.0).abs < 0.0001, "goldPerSecond1")
    assert((gameTest.goldPerClick() - 22.0).abs < 0.0001, "goldPerClick1")
    gameTest.clickGold()
    assert((gameTest.gold - 22.0).abs < 0.0001, "gold1")
    val timeTest: Long = System.nanoTime()
    //println(timeTest - gameTest.lastUpdateTime)
    val goldCalculate: Double = (timeTest - gameTest.lastUpdateTime) * 0.000000001 * 110.0 + 22.0
    //println(goldCalculate)
    gameTest.update(timeTest)
    assert((gameTest.gold - goldCalculate).abs < 0.0001, "update1")
    gameTest.gold = 0.0
    gameTest.update(timeTest + 2E9.toLong)
    assert((gameTest.gold - 220.0).abs < 0.0001, "update2")
  }
}

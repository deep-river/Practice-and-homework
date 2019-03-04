package tests

import org.scalatest._
import clicker.Game

class TestJSON extends FunSuite{

  test("TestJSON"){
    val stateJson: String = """{
                              "gold": 3410.1482007745067,
                              "lastUpdateTime": 14656200,
                              "equipment": {
                               "shovel": {
                               "numberOwned": 10,
                               "name": "Shovel"
                               },
                               "excavator": {
                               "numberOwned": 9,
                               "name": "Excavator"
                               },
                               "mine": {
                               "numberOwned": 7,
                               "name": "Gold Mine"
                               }
                               }
                              }
                              """
    var gameTest = new Game
    gameTest.fromJSON(stateJson)
    assert((gameTest.gold - 3410.1482007745067).abs < 0.0001, "gold")
    assert((gameTest.lastUpdateTime - 14656200).abs < 0.0001, "time")
    assert((gameTest.equipment("shovel").numberOwned - 10).abs < 0.0001, "shovel")
    assert((gameTest.equipment("excavator").numberOwned - 9).abs < 0.0001, "excavator")
    assert((gameTest.equipment("mine").numberOwned - 7).abs < 0.0001, "mine")
  }
}

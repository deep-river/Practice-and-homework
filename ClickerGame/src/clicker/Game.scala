package clicker

import clicker.equipment._
import play.api.libs.json.{JsValue, Json}

class Game {

  // Do not change these state variable names, types, or initial values
  //
  // These same names, types, and initial values will be the same in all submissions on AutoLab so you can
  //  use these in your test cases
  var gold: Double = 0.0
  var lastUpdateTime: Long = System.nanoTime()
  var equipment: Map[String, Equipment] = Map("shovel" -> new Shovels, "excavator" -> new Excavators, "mine" -> new GoldMines)
  //

  def goldPerSecond(): Double = {
    var gps: Double = 0.0
    for ((key, value) <- equipment){
      gps += value.goldPerSecond()
    }
    gps
  }

  def goldPerClick(): Double = {
    var gpc: Double = 0.0
    for ((key, value) <- equipment){
      gpc += value.goldPerClick()
    }
    gpc + 1
  }


  def clickGold(): Unit = {
    gold += goldPerClick()
  }

  def buyEquipment(equipmentKey: String): Unit = {
    if (equipment.get(equipmentKey) != null){
      val cost = equipment(equipmentKey).costOfNextPurchase()
      if (cost <= gold){
        equipment(equipmentKey).buy()
        gold -= cost
      }
    }
  }

  /**
    * takes the current epoch time in nanoseconds
    */
  def update(time: Long): Unit = {
    gold += (time - lastUpdateTime) * 0.000000001 * goldPerSecond()
    lastUpdateTime = time
  }


  def toJSON(): String = {
    val shovelJson = Json.toJson(Map(
      "numberOwned" -> Json.toJson(equipment("shovel").numberOwned),
      "name" -> Json.toJson("Shovel")
    ))
    val excavatorJson = Json.toJson(Map(
      "numberOwned" -> Json.toJson(equipment("excavator").numberOwned),
      "name" -> Json.toJson("Excavator")
    ))
    val mineJson = Json.toJson(Map(
      "numberOwned" -> Json.toJson(equipment("mine").numberOwned),
      "name" -> Json.toJson("Gold Mine")
    ))

    val equipJson: Map[String, JsValue] = Map(
      "shovel" -> shovelJson,
      "excavator" -> excavatorJson,
      "mine" -> mineJson
    )
    val jsonState: Map[String, JsValue] = Map(
      "gold" -> Json.toJson(gold),
      "lastUpdateTime" -> Json.toJson(lastUpdateTime),
      "equipment" -> Json.toJson(equipJson)
    )
    Json.stringify(Json.toJson(jsonState))
  }


  def fromJSON(jsonGameState: String): Unit = {
    val parsed: JsValue =Json.parse(jsonGameState)
    gold = (parsed \ "gold").as[Double]
    lastUpdateTime = (parsed \ "lastUpdateTime").as[Long]
    equipment("shovel").numberOwned = (parsed \ "equipment" \ "shovel" \ "numberOwned").as[Int]
    equipment("excavator").numberOwned = (parsed \ "equipment" \ "excavator" \ "numberOwned").as[Int]
    equipment("mine").numberOwned = (parsed \ "equipment" \ "mine" \ "numberOwned").as[Int]
    /*
    val shoveMap = (parsed \ "equipment").as[Map[String, JsValue]]
    equipment("shovel").numberOwned = (shoveMap("shovel") \ "numberOwned").as[Int]
    val excavatorMap = (parsed \ "equipment").as[Map[String, JsValue]]
    equipment("excavator").numberOwned = (excavatorMap("excavator") \ "numberOwned").as[Int]
    val mineMap = (parsed \ "equipment").as[Map[String, JsValue]]
    equipment("mine").numberOwned = (mineMap("mine") \ "numberOwned").as[Int]
    */
  }


  // Given
  def goldString(): String = {
    f"$gold%1.0f"
  }

  def buttonText(equipmentKey: String): String = {
    val thing: Equipment = this.equipment.getOrElse(equipmentKey, null) // will crash program if key not found
    val cost = thing.costOfNextPurchase()
    val gpc = thing.goldPerClick()
    val gps = thing.goldPerSecond()
    thing.name + f"\n$cost%1.0f gold\n$gpc%1.0f gpc\n$gps%1.0f gps\nowned: " + thing.numberOwned
  }

  //

}

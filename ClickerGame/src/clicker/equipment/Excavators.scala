package clicker.equipment

class Excavators extends Equipment{

  this.name = "Excavator"

  override def goldPerSecond(): Double = {
    10.0 * numberOwned
  }

  override def goldPerClick(): Double = {
    20.0 * numberOwned
  }

  override def costOfNextPurchase(): Double = {
    if (numberOwned == 0){
      200.0
    }else{
      200.0 * (1 + numberOwned * 0.1)
    }
  }

}

package clicker.equipment

class Shovels extends Equipment {

  this.name = "Shovel"

  override def goldPerSecond(): Double = {
    0.0
  }

  override def goldPerClick(): Double = {
    numberOwned
  }

  override def costOfNextPurchase(): Double = {
    if (numberOwned == 0){
      10.0
    }else{
      10.0 * (1 + numberOwned * 0.05)
    }
  }
}

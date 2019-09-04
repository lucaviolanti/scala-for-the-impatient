object Chapter06 {

  // 1. Write an object `Conversions` with methods `inchesToCentimeters`, `gallonsToLiters`, and `milesToKilometers`.
  object Conversions {
    def in2cm(inches: Double): Double = inches * 2.54

    def gal2l(gallons: Double): Double = gallons * 3.78541

    def mi2km(miles: Double): Double = miles * 1.60934
  }

  // 2. The preceding problem wasn't very object-oriented. Provide a general superclass `UnitConversion` and define
  // objects `InchesToCentimeters`, `GallonsToLiters`, and `MilesToKilometers` that extend it.
  class UnitConversion(multiplier: Double) {
    def convert(value: Double): Double = value * multiplier
  }

  object In2cm extends UnitConversion(2.54)

  object Gal2l extends UnitConversion(3.78541)

  object Mi2km extends UnitConversion(1.60934)

  // 3. Define an `Origin` object that extends `java.awt.Point`. Why is this not actually a good idea? (Have a close
  // look at the methods of the `Point` class.)
  object Origin extends java.awt.Point

  // 4. Define a `Point` class with a companion object so that you can construct `Point` instances as `Point(3, 4)`,
  // without using new.
  class Point(x: Int, y: Int) extends java.awt.Point

  object Point {
    def apply(x: Int, y: Int): Point = new Point(x, y)
  }

}

// 5. Write a Scala application, using the `App` trait, that prints its command-line arguments in reverse order,
// separated by spaces. For example, `scala Reverse Hello World` should print `World Hello`.
object Reverse extends App {
  args.reverse.foreach(println)
}

object Chapter06Continued {

  // 6. Write an enumeration describing the four playing card suits so that the `toString` method returns ♣, ♦, ♥, or ♠.
  object Suit extends Enumeration {
    val Club = Value("♣")
    val Diamond = Value("♦")
    val Heart = Value("♥")
    val Spade = Value("♠")
  }

  // 7. Implement a function that checks whether a card suit value from the preceding exercise is red.
  def isRed(s: Suit.Value): Boolean = {
    import Suit._
    s == Diamond || s == Heart
  }

  // 8. Write an enumeration describing the eight corners of the RGB color cube. As IDs, use the color values
  // (for example, 0xff0000 for Red).
  object CornersOfTheRGBCube extends Enumeration {
    val Blue = Value(0x0000ff)
    val Cyan = Value(0x00ffff)
    val Magenta = Value(0xff00ff)
    val White = Value(0xffffff)
    val Black = Value(0x000000)
    val Green = Value(0x00ff00)
    val Red = Value(0xff0000)
    val Yellow = Value(0xffff00)
  }
}

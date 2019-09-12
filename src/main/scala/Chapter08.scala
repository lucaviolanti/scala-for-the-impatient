object Chapter08 {

  // 1. Extend the following `BankAccount` class to a `CheckingAccount` class that charges $1 for every deposit and
  // withdrawal.
  // ```
  // class BankAccount(initialBalance: Double) {
  //   private var balance = initialBalance
  //   def currentBalance = balance
  //   def deposit(amount: Double) = { balance += amount; balance }
  //   def withdraw(amount: Double) = { balance -= amount; balance }
  // }
  // ```
  class BankAccount(initialBalance: Double) {
    private var balance = initialBalance

    def currentBalance: Double = balance

    def deposit(amount: Double): Double = {
      balance += amount;
      balance
    }

    def withdraw(amount: Double): Double = {
      balance -= amount;
      balance
    }
  }

  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    override def deposit(amount: Double): Double = super.deposit(amount - 1)

    override def withdraw(amount: Double): Double = super.withdraw(amount + 1)
  }

  // 2. Extend the `BankAccount` class of the preceding exercise into a class `SavingsAccount` that earns interest every
  // month (when a method `earnMonthlyInterest` is called) and has three free deposits or withdrawals every month. Reset
  // the transaction count in the `earnMonthlyInterest method`.
  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    private var freeDeposits = 3
    private val interest = 0.05

    def earnMonthlyInterest(): Unit = {
      super.deposit(currentBalance * interest)
    }
  }

  // 3. Consult your favorite Java or C++ textbook which is sure to have an example of a toy inheritance hierarchy,
  // perhaps involving employees, pets, graphical shapes, or the like. Implement the example in Scala.
  abstract class Animal {
    def makeSound(): Unit
  }

  class Dog extends Animal {
    override def makeSound(): Unit = println("Woof")
  }

  class Cat extends Animal {
    override def makeSound(): Unit = println("Meow")
  }

  // 4. Define an abstract class `Item` with methods `price` and `description`. A `SimpleItem` is an item whose price
  // and description are specified in the constructor. Take advantage of the fact that a `val` can override a `def`.
  // A `Bundle` is an item that contains other items. Its price is the sum of the prices in the bundle. Also provide a
  // mechanism for adding items to the bundle and a suitable description method.
  abstract class Item {
    def description(): String

    def price(): Double
  }

  class SimpleItem(val description: String, val price: Double) extends Item

  class Bundle(description: String, items: Item*) extends Item {
    override def price(): Double = items.map(_.price()).sum

    override def description(): String = description
  }

  // 5. Design a class `Point` whose x and y coordinate values can be provided in a constructor. Provide a subclass
  // `LabeledPoint` whose constructor takes a `label` value and x and y coordinates, such as
  // `new LabeledPoint("Black Thursday", 1929, 230.07)`
  class Point(val x: Double, val y: Double)

  class LabeledPoint(label: String, x: Double, y: Double) extends Point(x, y)

  // 6. Define an abstract class `Shape` with an abstract method `centerPoint` and subclasses `Rectangle` and `Circle`.
  // Provide appropriate constructors for the subclasses and override the `centerPoint` method in each subclass.
  abstract class Shape {
    def centrePoint(): Point
  }

  private def avg(a: Double, b: Double): Double = a + b / 2

  class Rectangle(topLeft: Point, bottomRight: Point) extends Shape {
    override def centrePoint(): Point = new Point(avg(topLeft.x, bottomRight.x), avg(topLeft.y, bottomRight.y))
  }

  class Circle(centre: Point, radius: Double) extends Shape {
    override def centrePoint(): Point = centre
  }

  // 7. Provide a class `Square` that extends `java.awt.Rectangle` and has three constructors: one that constructs a
  // square with a given corner point and width, one that constructs a square with corner (0, 0) and a given width, and
  // one that constructs a square with corner (0, 0) and width 0.
  class Square(corner: java.awt.Point, width: Int) extends java.awt.Rectangle(corner.x, corner.y, width, width) {
    def this(width: Int) = {
      this(new java.awt.Point(0, 0), width)
    }

    def this() = {
      this(new java.awt.Point(0, 0), 0)
    }
  }

  // 8. Compile the `Person` and `SecretAgent` classes in Section 8.6, “Overriding Fields,” on page 95 and analyze the
  // class files with `javap`. How many name fields are there? How many name getter methods are there? What do they get?
  // (Hint: Use the -c and -private options.)
  class Person(val name: String) {
    override def toString = s"${getClass.getName}[name=$name]"
  }

  class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret" // Don't want to reveal name ...
    override val toString = "secret" // ... or class name
  }

  /*
   Both have:
   - name field
   - name getter
   - toString
   */

  // 9. In the `Creature` class of Section 8.10, “Construction Order and Early Definitions,” on page 98, replace
  // `val range` with a `def`. What happens when you also use a `def` in the `Ant` subclass? What happens when you use
  // a `val` in the subclass? Why?
  class Creature {
    def range: Int = 10

    val env: Array[Int] = new Array[Int](range)
  }

  class Ant extends Creature {
    override def range = 2
  }

  // 10. The file scala/collection/immutable/Stack.scala contains the definition
  // ```
  // class Stack[A] protected (protected val elems: List[A])
  // ```
  // Explain the meanings of the protected keywords. (Hint: Review the discussion of private constructors in Chapter 5.)
  /* The first `protected` refers to the constructor; it makes it only invokable by another constructor within `Stack`
  * or a descendant class. Same for the the `elems` field.
  */


  // 11. Define a value class `Point` that packs integer `x` and `y` coordinates into a `Long`
  // (which you should make private).
  object exercise11 {

    class Point private(private val packed: Long) {
      def x: Int = (packed >> 32).toInt

      def y: Int = packed.toInt
    }

    object Point {
      def apply(x: Int, y: Int): Point = {
        new Point((x.toLong << 32) | y)
      }
    }

  }

}

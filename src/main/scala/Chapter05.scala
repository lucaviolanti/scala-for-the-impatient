object Chapter05 {

  // 1. Improve the `Counter` class in Section 5.1, “Simple Classes and Parameterless Methods,” on page 55 so that it
  // doesn’t turn negative at `Int.MaxValue`.
  class Counter {
    private var value: Int = 0

    def increment(): Unit = {
      if (value < Int.MaxValue) value += 1
    }

    def current(): Int = value
  }

  // 2. Write a class `BankAccount` with methods `deposit` and `withdraw`, and a read-only property `balance`.
  class BankAccount {
    private var balance: BigDecimal = 0

    def deposit(amount: BigDecimal): Unit = balance += amount

    def withdraw(amount: BigDecimal): Unit = balance -= amount
  }

  // 3. Write a class `Time` with read-only properties `hours` and `minutes` and a method `before(other: Time): Boolean`
  // that checks whether this time comes before the other. A `Time` object should be constructed as `new Time(hrs, min)`
  // where `hrs` is in military time format (between 0 and 23).
  class Time {
    private var hours: Int = 0
    private var minutes: Int = 0

    def this(hours: Int, minutes: Int) = {
      this()
      if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) throw new IllegalArgumentException("Invalid format")
      else {
        this.hours = hours
        this.minutes = minutes
      }
    }

    def before(other: Time): Boolean =
      this.hours < other.hours || (this.hours == other.hours && this.minutes < other.minutes)
  }

  // 4. Reimplement the `Time` class from the preceding exercise so that the internal representation is the number of
  // minutes since midnight (between 0 and 24 × 60 – 1). Do not change the public interface. That is, client code should
  // be unaffected by your change.
  class TimeInMinutesSinceMidnight {
    private var internalTime: Int = 0

    def this(hours: Int, minutes: Int) = {
      this()
      if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) throw new IllegalArgumentException("Invalid format")
      else {
        this.internalTime = hours * 60 + minutes
      }
    }

    def before(other: TimeInMinutesSinceMidnight): Boolean = this.internalTime < other.internalTime
  }

  // 5. Make a class `Student` with read-write JavaBeans properties `name` (of type `String`) and `id` (of type `Long`).
  // What methods are generated? (Use `javap` to check.) Can you call the JavaBeans getters and setters in Scala?
  // Should you?
  class Student {

    import java.beans.BeanProperty

    @BeanProperty var name: String = _
    @BeanProperty var id: Long = 0L
  }

  /*
    public class $line3.$read$$iw$$iw$Student {
      private java.lang.String name;
      private long id;
      public java.lang.String name();
      public void name_$eq(java.lang.String);
      public long id();
      public void id_$eq(long);
      public long getId();
      public java.lang.String getName();
      public void setId(long);
      public void setName(java.lang.String);
      public $line3.$read$$iw$$iw$Student();
    }
   */

  // 6. In the `Person` class of Section 5.1, “Simple Classes and Parameterless Methods,” on page 55, provide a primary
  // constructor that turns negative ages to 0.
  class Person(var name: String, var age: Int) {
    if (age < 0) {
      age = 0
    }
  }

  // 7. Write a class `Person` with a primary constructor that accepts a string containing a first name, a space, and
  // a last name, such as `new Person("Fred Smith")`. Supply read-only properties `firstName` and `lastName`. Should the
  // primary constructor parameter be a `var`, a `val`, or a plain parameter? Why?
  class PersonViaString(fullName: String) {
    val bits = fullName.split(" ")
    val firstName = bits(0)
    val lastName = bits(1)
  }

  // 8. Make a class `Car` with read-only properties for manufacturer, model name, and model year, and a read-write
  // property for the license plate. Supply four constructors. All require the manufacturer and model name. Optionally,
  // model year and license plate can also be specified in the constructor. If not, the model year is set to -1 and the
  // license plate to the empty string. Which constructor are you choosing as the primary constructor? Why?
  class Car(val manufacturer: String, val modelName: String, val modelYear: Int = -1, var licensePlate: String = "") {
    def this(manufacturer: String, modelName: String, modelYear: Int) {
      this(manufacturer, modelName, modelYear, "")
    }

    def this(manufacturer: String, modelName: String, licensePlate: String) {
      this(manufacturer, modelName, -1, licensePlate)
    }

    def this(manufacturer: String, modelName: String) {
      this(manufacturer, modelName, -1, "")
    }
  }

  // 9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your choice). How much shorter is the Scala
  // class?
  /* How about no */

  // 10. Consider the class
  // ```
  // class Employee(val name: String, var salary: Double) {
  //   def this() { this("John Q. Public", 0.0) }
  // }
  // ```
  // Rewrite it to use explicit fields and a default primary constructor. Which form do you prefer? Why?
  class Employee(val name: String = "John Q. Public", var salary: Double = 0.0)

}
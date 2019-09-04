object Chapter04 {
  // 1. Set up a map of prices for a number of gizmos that you covet. Then produce a second map with the same keys and
  // the prices at a 10 percent discount.
  def task01(): Map[String, Double] = {
    val prices: Map[String, Double] = Map("thing1" -> 10.0, "thing2" -> 20.0, "thing3" -> 30.0)
    for (g <- prices) yield (g._1, g._2 * 0.9)
  }

  // 2. Write a program that reads words from a file. Use a mutable map to count how often each word appears. To read
  // the words, simply use a `java.util.Scanner`:
  // ```
  // val in = new java.util.Scanner(new java.io.File("myfile.txt"))
  // while (in.hasNext()) {
  //   process(in.next())
  // }
  // ```
  // Or look at Chapter 9 for a Scalaesque way. At the end, print out all words and their counts.
  def countWords(): Unit = {
    val in = new java.util.Scanner(new java.io.File("src/main/resources/myfile.txt"))

    import scala.collection.mutable.{ListMap, Map}
    val counter: Map[String, Int] = ListMap[String, Int]()

    def process(input: String): Unit = {
      val normalised: String = input.toLowerCase().strip().split("\\W+").head
      counter += (normalised -> (counter.getOrElse(normalised, 0) + 1))
    }

    while (in.hasNext()) {
      process(in.next())
    }

    println(counter)
  }

  // 3. Repeat the preceding exercise with an immutable map.
  def countWordsImmutable(): Unit = {
    val in = new java.util.Scanner(new java.io.File("src/main/resources/myfile.txt"))

    var counter: Map[String, Int] = Map[String, Int]()

    def process(input: String): Unit = {
      val normalised: String = input.toLowerCase().strip().split("\\W+").head
      counter += (normalised -> (counter.getOrElse(normalised, 0) + 1))
    }

    while (in.hasNext()) {
      process(in.next())
    }

    println(counter)
  }

  // 4. Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.
  def countWordsSorted(): Unit = {
    val in = new java.util.Scanner(new java.io.File("src/main/resources/myfile.txt"))
    import scala.collection.immutable.SortedMap
    var counter: SortedMap[String, Int] = SortedMap[String, Int]()

    def process(input: String): Unit = {
      val normalised: String = input.toLowerCase().strip().split("\\W+").head
      counter += (normalised -> (counter.getOrElse(normalised, 0) + 1))
    }

    while (in.hasNext()) {
      process(in.next())
    }

    println(counter)
  }

  // 5. Repeat the preceding exercise with a `java.util.TreeMap` that you adapt to the Scala API.
  def countWordsWithTreeMap(): Unit = {
    val in = new java.util.Scanner(new java.io.File("src/main/resources/myfile.txt"))
    import java.util.TreeMap

    import scala.collection.JavaConverters._
    import scala.collection.mutable.Map
    val counter: Map[String, Int] = new TreeMap[String, Int]().asScala

    def process(input: String): Unit = {
      val normalised: String = input.toLowerCase().strip().split("\\W+").head
      counter += (normalised -> (counter.getOrElse(normalised, 0) + 1))
    }

    while (in.hasNext()) {
      process(in.next())
    }

    println(counter)
  }

  // 6. Define a linked hash map that maps "Monday" to `java.util.Calendar.MONDAY`, and similarly for the other
  // weekdays. Demonstrate that the elements are visited in insertion order.
  val weekdays: collection.mutable.Map[String, Int] = collection.mutable.LinkedHashMap(
    "Monday" -> java.util.Calendar.MONDAY,
    "Tuesday" -> java.util.Calendar.TUESDAY,
    "Wednesday" -> java.util.Calendar.WEDNESDAY,
    "Thursday" -> java.util.Calendar.THURSDAY,
    "Friday" -> java.util.Calendar.FRIDAY,
    "Saturday" -> java.util.Calendar.SATURDAY,
    "Sunday" -> java.util.Calendar.SUNDAY
  )

  // 7. Print a table of all Java properties reported by the `getProperties` method of the `java.lang.System` class,
  // like this:
  // ```
  //   java.runtime.name      | Java(TM) SE Runtime Environment
  //   sun.boot.library.path  | /home/apps/jdk1.6.0_21/jre/lib/i386
  //   java.vm.version        | 17.0-b16
  //   java.vm.vendor         | Sun Microsystems Inc.
  //   java.vendor.url        | http://java.sun.com/
  //   path.separator         | :
  //   java.vm.name           | Java HotSpot(TM) Server VM
  // ```
  // You need to find the length of the longest key before you can print the table.
  def printJavaProperties(): Unit = {
    import scala.collection.JavaConverters._
    import scala.collection.mutable
    val properties: mutable.Map[String, String] = java.lang.System.getProperties().asScala
    val longestKeyLength: Int = -(properties.keys.maxBy(_.length).length)
    properties.foreach(p => printf(s"%${longestKeyLength}s  | %s\n", p._1, p._2))
  }

  // 8. Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and the largest values
  // in the array.
  def minmax(values: Array[Int]): (Int, Int) = (values.min, values.max)

  // 9. Write a function `lteqgt(values: Array[Int], v: Int)` that returns a triple containing the counts of values less
  // than v, equal to v, and greater than v.
  def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = (
    values.filter(_ < v).length,
    values.filter(_ == v).length,
    values.filter(_ > v).length
  )

  // 10. What happens when you zip together two strings, such as `"Hello".zip("World")`? Come up with a plausible use
  // case.
  def zipTwoStrings(a: String, b: String): collection.immutable.Seq[(Char, Char)] = {
    a.zip(b)
  }
}
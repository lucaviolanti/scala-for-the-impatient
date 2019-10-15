import scala.math.Ordering.Double

object Chapter09 {
  // 1. Write a Scala code snippet that reverses the lines in a file (making the last line the first one and so on).
  def reverseLinesInAFile(pathToFile: String): List[String] = {
    import scala.io.Source
    val file = Source.fromFile(pathToFile)
    val reversedLines = file.getLines().toList.reverse
    file.close()
    reversedLines
    // Try it with `reverseLinesInAFile("src/main/resources/file-to-reverse.txt")`.
  }

  // 2. Write a Scala program that reads a file with tabs, replaces each tab with spaces so that tab stops are at
  // n-column boundaries, and writes the result to the same file.
  def replaceTabsWithSpaces(pathToFile: String, nColumns: Int = 2): Unit = {
    import scala.io.Source
    val file = Source.fromFile(pathToFile)
    val lines = file.getLines
    for (l <- lines) {
      println(l.replaceAll("\t", " " * nColumns))
      // Don't want to write to the same file.
    }
    file.close()
    // Try it with `replaceTabsWithSpaces("src/main/resources/file-with-tabs.txt", 1)`.
  }

  // 3. Write a Scala code snippet that reads a file and prints all words with more than 12 characters to the console.
  // Extra credit if you can do this in a single line.
  def printAllWordsWithMoreThan12Characters(pathToFile: String): Unit = {
    import scala.io.Source
    val file = Source.fromFile(pathToFile)
    file.mkString.split("\\W+").filter(s => s.length > 12).foreach(println)
    file.close()
    // Try it with `printAllWordsWithMoreThan12Characters("src/main/resources/file-with-tabs.txt")`
  }

  // 4. Write a Scala program that reads a text file containing only floating-point numbers.
  // Print the sum, average, maximum, and minimum of the numbers in the file.
  def printSumAverageMaximumAndMinimum(pathToFile: String): Unit = {
    import scala.io.Source
    implicit val ordering = Ordering.Double.TotalOrdering
    val file = Source.fromFile(pathToFile)
    val lines = file.getLines().toList
    val numbers = lines.map(_.toDouble)
    val sum = numbers.foldLeft(0.0)(_ + _)
    println(s"Sum: ${sum}\nAverage: ${sum / numbers.length}")
    println(s"Max: ${numbers.max}\nMin: ${numbers.min}")
    file.close()
    // Try it with `printSumAverageMaximumAndMinimum("src/main/resources/file-containing-only-floating-point-numbers.txt")`
  }

  // 5. Write a Scala program that writes the powers of 2 and their reciprocals to a file, with the exponent ranging
  // from 0 to 20. Line up the columns.
  //    1     1
  //    2     0.5
  //    4     0.25
  //  ...     ...
  def printTheFirst21PowersOf2AndTheirReciprocals(): Unit = {
    println(s"1\t1") // deal with the special case first
    for (exp <- 1 to 20) {
      val pow = math.pow(2, exp).toInt
      println(s"${pow}\t${1d/pow}")
    }
  }

  // 6. Make a regular expression searching for quoted strings "like this, maybe with \" or \\" in a Java or C++
  // program. Write a Scala program that prints out all such strings in a source file.
  def searchQuotedStrings(pathToFile: String): Unit = {
    import scala.io.Source
    val file = Source.fromFile(pathToFile)
    val quoted = """\"(\\.|[^\"])*\"""".r
    quoted.findAllIn(file.mkString).foreach(println)
    file.close()
    // Try it with `searchQuotedStrings("src/main/scala/Chapter01.scala")`
  }

  // 7. Write a Scala program that reads a text file and prints all tokens in the file that are not floating point
  // numbers. Use a regular expression.

  // 8. Write a Scala program that prints the `src` attributes of all `img` tags of a web page.
  // Use regular expressions and groups.

  // 9. Write a Scala program that counts how many files with `.class` extension are in a given directory and its
  // subdirectories.

  // 10. Expand the example in Section 9.8, "Serialization", on page 113. Construct a few `Person` objects, make
  // some of them friends of others, and save an `Array[Person]` to a file. Read the array back in and verify that
  // the friend relations are intact.
}
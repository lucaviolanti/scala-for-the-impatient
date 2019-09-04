object Chapter02 {
  // 1. The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero.
  // Write a function that computes this value.
  def signum(n: Int): Int = if (n > 0) 1 else if (n < 0) -1 else 0

  // 2. What is the value of an empty block expression `{}`? What is its type?
  def emptyBlock: Unit = {}

  // 3. Come up with one situation where the assignment `x = y = 1` is valid in Scala. (Hint: Pick a suitable type for x.)
  var x: Unit = ()
  var y: Int = 0
  x = y = 1

  // 4. Write a Scala equivalent for the Java loop
  // `for (int i = 10; i >= 0; i--) System.out.println(i);`
  var i = 10
  while (i >= 0) {
    println(i)
    i -= 1
  }

  // 5. Write a procedure `countdown(n: Int)` that prints the numbers from n to 0.
  def countdown(n: Int): Unit = {
    var i = n
    while (i >= 0) {
      println(i)
      i -= 1
    }
  }

  // 6. Write a for loop for computing the product of the Unicode codes of all letters in a string.
  // For example, the product of the characters in "Hello" is 9415087488L.
  def productOfUnicodes(s: String): Long = {
    var p = 1L
    for (l <- s) {
      p = p * l.toInt
    }
    p
  }

  // 7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps Scaladoc.)
  def productOfUnicodesWithoutLoops(s: String): Long = {
    s.foldLeft(1L)(_ * _.toInt)
  }

  // 8. Write a function product(s : String) that computes the product, as described in the preceding exercises.
  def product(s: String): Long = {
    productOfUnicodes(s)
  }

  // 9. Make the function of the preceding exercise a recursive function.
  def recursiveProduct(s: String): Long = {
    if (s.isEmpty) {
      return 1L
    }
    s.head.toLong * recursiveProduct(s.tail)
  }

  // 10. Write a function that computes x^n, where n is an integer. Use the following recursive definition:
  //  - x^n = y * y if n is even and positive, where y = x^(n/2).
  //  - x^n = x * x^(n–1) if n is odd and positive.
  //  - x^0 = 1.
  //  - x^n = 1 / x^(-n) if n is negative.
  // Don’t use a `return` statement.
  def pow(x: Int, n: Int): Int = {
    if (n > 0) {
      if (n % 2 == 0) {
        val y = pow(x, n / 2)
        y * y
      } else {
        x * pow(x, n - 1)
      }
    } else if (n == 0) {
      1
    } else {
      1 / pow(x, -n)
    }
  }

  // 11. Define a string interpolator date so that you can define a java.time.LocalDate as
  // date"$year-$month-$day". You need to define an “implicit” class with a date
  // method, like this:
  // ```implicit class DateInterpolator(val sc: StringContext) extends AnyVal {
  //   def date(args: Any*): LocalDate = ...
  // }```
  // `args(i)` is the value of the ith expression. Convert each to a string and then to an integer, and pass them to the
  // `LocalDate.of` method. If you already know some Scala, add error handling. Throw an exception if there aren’t three
  // arguments, or if they aren’t integers, or if they aren’t separated by dashes. (You get the strings in between the
  // expressions as `sc.parts`.)
  implicit class DateInterpolator(val sc: StringContext) extends AnyVal {

    import java.time.LocalDate

    def date(args: Any*): LocalDate = {
      if (args.size != 3) {
        throw new IllegalArgumentException("Not enough parameters")
      }
      val year = args(0).toString.toInt
      val month = args(1).toString.toInt
      val day = args(2).toString.toInt
      LocalDate.of(year, month, day)
    }
  }

}
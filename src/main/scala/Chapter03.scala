import scala.collection.immutable._
import scala.collection.mutable

object Chapter03 {

  import scala.collection.mutable.ArrayBuffer

  // 1. Write a code snippet that sets `a` to an array of n random integers between 0 (inclusive) and n (exclusive).
  def snippet(n: Int): Array[Int] = {
    import scala.util.Random

    val a = new Array[Int](n)
    val r = Random
    for (i <- a.indices) {
      a(i) = r.nextInt()
    }
    a
  }

  // 2. Write a loop that swaps adjacent elements of an array of integers.
  // For example, `Array(1, 2, 3, 4, 5)` becomes `Array(2, 1, 4, 3, 5)`.
  def swapAdjacentElements(a: Array[Int]): Unit = {
    var i = 0
    while (i < a.length) {
      val j = i + 1
      if (j < a.length) {
        val tmp = a(i)
        a(i) = a(j)
        a(j) = tmp
        i += 1
      }
      i += 1
    }
  }

  // 3. Repeat the preceding assignment, but produce a new array with the swapped values. Use `for/yield`.
  def swapAdjacentElementsWithForYield(a: Array[Int]): Array[Int] =
    (for (i <- a.indices) yield {
      if ((i % 2) == 0 && (i + 1) == a.length) a(i)
      else if ((i % 2) == 0) a(i + 1)
      else a(i - 1)
    }).toArray

  // 4. Given an array of integers, produce a new array that contains all positive values of the original array, in
  // their original order, followed by all values that are zero or negative, in their original order.
  def task04(array: Array[Int]): Array[Int] = {
    val parts = array.partition(_ > 0)
    Array.concat(parts._1, parts._2)
  }

  // 5. How do you compute the average of an `Array[Double]`?
  def avg(a: Array[Double]): Double = a.sum / a.length

  // 6. How do you rearrange the elements of an `Array[Int]` so that they appear in reverse sorted order? How do you do
  // the same with an `ArrayBuffer[Int]`?
  def reverseSort(a: Array[Int]): Array[Int] = a.sortWith(_ > _)

  def reverseSort(ab: ArrayBuffer[Int]): ArrayBuffer[Int] = ab.sortWith(_ > _)

  // 7. Write a code snippet that produces all values from an array with duplicates removed. (Hint: Look at Scaladoc.)
  def removeDuplicates[A](as: Array[A]): Array[A] = as.distinct

  // 8. Suppose you are given an array buffer of integers and want to remove all but the first negative
  // number. Here is a sequential solution that sets a flag when the first negative number is called,
  // then removes all elements beyond.
  // ```
  // var first = true
  // var n = a.length
  // var i = 0
  // while (i < n) {
  //   if (a(i) >= 0) i += 1
  //   else {
  //     if (first) { first = false; i += 1 }
  //     else { a.remove(i); n -= 1 }
  //   }
  // }
  // ```
  // This is a complex and inefficient solution. Rewrite it in Scala by collecting positions of the negative elements,
  // dropping the first element, reversing the sequence, and calling `a.remove(i)` for each index.
  def removeAllButTheFirstNegative(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    val negativesButTheFirstReversed: Seq[Int] =
      (for (i <- a.indices if a(i) < 0)
        yield i)
        .drop(1)
        .reverse
    for (n <- negativesButTheFirstReversed) {
      a.remove(n)
    }

    a
  }

  // 9. Improve the solution of the preceding exercise by collecting the positions that should be moved and their target
  // positions. Make those moves and truncate the buffer. Don’t copy any elements before the first unwanted element.
  def removeAllButTheFirstNegativeImproved(a: ArrayBuffer[Int]): ArrayBuffer[Int] = ???

  // 10. Make a collection of all time zones returned by `java.util.TimeZone.getAvailableIDs` that are in America.
  // Strip off the "America/" prefix and sort the result.
  def usTimeZones(): Array[String] = {
    val prefix = "America/"
    java.util.TimeZone.getAvailableIDs
        .filter(_.startsWith(prefix))
        .map(_.stripPrefix(prefix))
        .sorted
  }

  // 11. Import `java.awt.datatransfer._` and make an object of type `SystemFlavorMap` with the call
  // `val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]`
  // Then call the `getNativesForFlavor` method with parameter `DataFlavor.imageFlavor` and get the return value as a
  // Scala buffer. (Why this obscure class? It’s hard to find uses of `java.util.List` in the standard Java library.)
  def task10(): mutable.Buffer[String] = {
    import java.awt.datatransfer._

    import scala.collection.JavaConverters._

    val flavors: SystemFlavorMap = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    flavors.getNativesForFlavor(DataFlavor.imageFlavor).asScala
  }
}
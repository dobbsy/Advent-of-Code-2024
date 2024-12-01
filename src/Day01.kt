import kotlin.math.abs

fun main() {
    fun parseInput(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        for (line in input) {
            val (number1String, number2String) = line.split("\\s+".toRegex())
            list1 += number1String.toInt()
            list2 += number2String.toInt()
        }

        return list1 to list2
    }

    fun part1(input: List<String>): Int {
        val (list1, list2) = parseInput(input)

        list1.sort()
        list2.sort()

        return list1.zip(list2).sumOf { (number1, number2) -> abs(number1 - number2) }
    }

    fun part2(input: List<String>): Int {
        val (list1, list2) = parseInput(input)

        return list1.sumOf { number1 -> number1 * list2.count {number2 -> number2 == number1} }
    }



    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

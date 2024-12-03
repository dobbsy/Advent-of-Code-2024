fun main() {
    val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    fun part1(input: String): Long {
        val matches = mulRegex.findAll(input)
        return matches.sumOf { match ->
            match.groupValues[1].toLong() * match.groupValues[2].toLong()
        }
    }

    fun part2(input: String): Long {
        var mulBlockStartIndex = 0
        var mulBlockEndIndex = input.indexOf("don't()")
        var res = 0L

        while (mulBlockStartIndex != -1 && mulBlockEndIndex != -1) {
            val matches = mulRegex.findAll(input.substring(mulBlockStartIndex, mulBlockEndIndex))
            res += matches.sumOf { match ->
                match.groupValues[1].toLong() * match.groupValues[2].toLong()
            }

            mulBlockStartIndex = input.indexOf("do()", mulBlockEndIndex)
            mulBlockEndIndex = input.indexOf("don't()", mulBlockStartIndex)
        }
        if (mulBlockStartIndex != -1) {
            val matches = mulRegex.findAll(input.substring(mulBlockStartIndex))
            res += matches.sumOf { match ->
                match.groupValues[1].toLong() * match.groupValues[2].toLong()
            }
        }

        return res
    }

    // Or read a large test input from the `src/Day03_test.txt` file:
    val testInput = readInputComplete("Day03_test")
    check(part1(testInput) == 161L)

    check(part2(testInput) == 48L)

    // Read the input from the `src/Day03.txt` file.
    val input = readInputComplete("Day03")
    part1(input).println()
    part2(input).println()
}

fun main() {
    fun part1(input: List<String>): Int {
        val reports = input.map { it.split(' ').map(String::toInt) }
        var numberOfSafeReports = 0
        for (report in reports) {
            val windowedReport = report.windowed(2)
            val diffRange = if (windowedReport[0][0] > windowedReport[0][1]) 1..3 else -3..-1
            val isSafeReport = windowedReport.all { it[0] - it[1] in diffRange }
            
            if (isSafeReport) numberOfSafeReports++
        }

        return numberOfSafeReports
    }

    fun part1Alt(input: List<String>): Int {
        val reports = input.map { it.split(' ').map(String::toInt) }

        return reports.map { report -> report.windowed(2) }
            .count { windowedReport ->
            if (windowedReport[0][0] > windowedReport[0][1]) {
                windowedReport.all { it[0] - it[1] in 1..3}
            } else {
                windowedReport.all { it[0] - it[1] in -3..-1}
            }
        }
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { it.split(' ').map(String::toInt) }
        var numberOfSafeReports = 0
        for (report in reports) {
            val windowedIndexedReport = report.withIndex().windowed(2)
            val diffRange = if (windowedIndexedReport[0][0].value > windowedIndexedReport[0][1].value) {
                1..3
            } else {
                -3..-1
            }
            var isSafeReport = true
            var indexToDelete = -1

            for (indexedWindow in windowedIndexedReport) {
                val (_, level1: Int) = indexedWindow[0]
                val (index2: Int, level2: Int) = indexedWindow[1]

                if (level1 - level2 !in diffRange) {
                    indexToDelete = index2
                    break
                }
            }

            if (indexToDelete != -1) {
                val newReport: MutableList<Int> = ArrayList(report)
                newReport.removeAt(indexToDelete)
                isSafeReport = newReport.windowed(2)
                    .all { it[0] - it[1] in diffRange }
            }

            if (!isSafeReport) {
                val newReport: MutableList<Int> = ArrayList(report)
                newReport.removeAt(indexToDelete - 1)
                isSafeReport = newReport.windowed(2)
                    .all { it[0] - it[1] in diffRange }
            }

            if (isSafeReport) numberOfSafeReports++
        }

        return numberOfSafeReports
    }

    // Or read test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part1Alt(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1Alt(input).println()
    part2(input).println()
}

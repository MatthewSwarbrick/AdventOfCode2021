fun main() {
    solvePart1()
    solvePart2()
}

private fun solvePart1() {
    val answer = puzzle
        .hexToBinary()
        .toPacket().second
        .toTotalVersion()

    println("Solution to part1: $answer")
}

private fun solvePart2() {
    val answer = puzzle
        .hexToBinary()
        .toPacket().second
        .value

    println("Solution to part2: $answer")
}

private fun String.hexToBinary(): String {
    var binary = this.toBigInteger(16).toString(2)
    while(binary.length % 4 != 0) {
        binary = "0$binary"
    }
    return binary
}

private fun String.toPacket() : Pair<String, Packet> {
    val version = this.substring(0, 3).toInt(2)
    val typeId = this.substring(3, 6).toInt(2)
    return if(typeId.isLiteral()) {
        val (remainingTransmission, literalValue) = this.substring(6, this.length).toLiteralValue()
        remainingTransmission to Packet(version,  literalValue)
    } else {
        val operator = typeId.toOperator()
        when (this.substring(6, 7).toInt(2)) {
            0 -> {
                val totalBitLength = this.substring(7, 22).toInt(2)
                var remainingTransmission = this
                var startIndexOfNextPacket = 22
                val packets = mutableListOf<Packet>()
                while(startIndexOfNextPacket < totalBitLength + 22) {
                    val (transmission, packet) = this.substring(startIndexOfNextPacket, this.length).toPacket()
                    remainingTransmission = transmission
                    startIndexOfNextPacket = this.length - remainingTransmission.length
                    packets.add(packet)
                }
                val value = packets.toValue(operator)
                remainingTransmission to Packet(version, value, operator, packets)
            }
            1 -> {
                val numberOfSubPackets = this.substring(7, 18).toInt(2)
                var remainingTransmission = this
                var startIndexOfNextPacket = 18
                val packets = mutableListOf<Packet>()
                for(i in 1..numberOfSubPackets) {
                    val (transmission, packet) = this.substring(startIndexOfNextPacket, this.length).toPacket()
                    remainingTransmission = transmission
                    startIndexOfNextPacket = this.length - remainingTransmission.length
                    packets.add(packet)
                }
                val value = packets.toValue(operator)
                remainingTransmission to Packet(version, value, operator, packets)
            } else -> {
              throw NotImplementedError()
            }
        }
    }
}

private fun Packet.toTotalVersion(): Long =
    this.version + this.packets.map { it.toTotalVersion() }.sumOf { it }

private fun String.toLiteralValue(): Pair<String, Long> {
    var literalValueBinary = ""
    var binary = this
    var endIndex = 0

    var group: String
    do {
        group = binary.substring(0, 5)
        literalValueBinary += group.substring(1, group.length)
        binary = binary.substring(5, binary.length)
        endIndex += group.length
    } while(group[0] == '1')

    return this.substring(endIndex, this.length) to literalValueBinary.toLong(2)
}

private fun Int.isLiteral() = this == 4

private fun Int.toOperator() = when(this) {
    0 -> Operator.SUM
    1 -> Operator.PRODUCT
    2 -> Operator.MINIMUM
    3 -> Operator.MAXIMUM
    5 -> Operator.GREATER_THAN
    6 -> Operator.LESS_THAN
    7 -> Operator.EQUAL_TO
    else -> Operator.EQUAL_TO
}

private fun List<Packet>.toValue(operator: Operator): Long =
    when(operator) {
        Operator.SUM -> this.sumOf { it.value }
        Operator.PRODUCT -> this.fold(1){ acc, packet -> acc * packet.value }
        Operator.MINIMUM -> this.minOf { it.value }
        Operator.MAXIMUM -> this.maxOf { it.value }
        Operator.GREATER_THAN -> if(this[0].value > this[1].value) 1 else 0
        Operator.LESS_THAN -> if(this[0].value < this[1].value) 1 else 0
        Operator.EQUAL_TO -> if(this[0].value == this[1].value) 1 else 0
    }


data class Packet(val version: Int, val value: Long, val operator: Operator? = null, val packets: List<Packet> = emptyList())

enum class Operator {
    SUM,
    PRODUCT,
    MINIMUM,
    MAXIMUM,
    GREATER_THAN,
    LESS_THAN,
    EQUAL_TO
}
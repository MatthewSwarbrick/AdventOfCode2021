import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.exp

class SnailPairTests {
    @ParameterizedTest
    @ValueSource(
        strings = [
            "[9,8]",
            "[[9,8],1]"
        ]
    )
    fun `SnailPair toString converts correctly`(expected: String) {
        val snailPair = expected.toSnailPair()
        val actual = snailPair.toString()
        assert(actual == expected) {"Expected:\n$expected\nBut got\n$actual"}
    }
}
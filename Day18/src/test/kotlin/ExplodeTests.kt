import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExplodeTests {
    @ParameterizedTest
    @CsvSource(
        value = [
            "[[[[[9,8],1],2],3],4]|[[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]]|[7,[6,[5,[7,0]]]]",
            "[[6,[5,[4,[3,2]]]],1]|[[6,[5,[7,0]]],3]",
            "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]|[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
            "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]|[[3,[2,[8,0]]],[9,[5,[7,0]]]]",
            "[[[[4,0],[5,0]],[[[4,5],[2,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]|[[[[4,0],[5,4]],[[0,[7,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]"
        ],
        delimiter = '|'
    )
    fun `I can explode a snail pair`(input: String, expected: String) {
        val actual = input
            .toSnailPair()
            .explode()

        assert(actual.toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }
}
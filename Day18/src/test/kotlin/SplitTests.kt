import org.junit.jupiter.api.Test

class SplitTests {
    @Test
    fun `Example 1 split test`() {
        val input = "[[[[7,7],[7,8]],[[9,5],[8,0]]],[[[9,10],20],[8,[9,0]]]]"

        val expected = "[[[[7,7],[7,8]],[[9,5],[8,0]]],[[[9,[5,5]],20],[8,[9,0]]]]"
        val actual = input.toSnailPair().split().toString()
        assert(actual == expected) { "Expected:\n$expected\nBut got:\n$actual" }
    }
}
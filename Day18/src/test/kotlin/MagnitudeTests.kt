import org.junit.jupiter.api.Test

class MagnitudeTests {
    @Test
    fun `Example 1 magnitude test`() {
        val input = "[[1,2],[[3,4],5]]"

        val expected = 143L
        val actual = input.toSnailPair().toMagnitude()
        assert(actual == expected) { "Expected:\n$expected\nBut got:\n$actual"}
    }

    @Test
    fun `Example 2 magnitude test`() {
        val input = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"

        val expected = 1384L
        val actual = input.toSnailPair().toMagnitude()
        assert(actual == expected) { "Expected:\n$expected\nBut got:\n$actual"}
    }

    @Test
    fun `Example 3 magnitude test`() {
        val input = "[[[[1,1],[2,2]],[3,3]],[4,4]]"

        val expected = 445L
        val actual = input.toSnailPair().toMagnitude()
        assert(actual == expected) { "Expected:\n$expected\nBut got:\n$actual"}
    }

    @Test
    fun `Example 4 magnitude test`() {
        val input = "[[[[3,0],[5,3]],[4,4]],[5,5]]"

        val expected = 791L
        val actual = input.toSnailPair().toMagnitude()
        assert(actual == expected) { "Expected:\n$expected\nBut got:\n$actual"}
    }

    @Test
    fun `Example 5 magnitude test`() {
        val input = "[[[[5,0],[7,4]],[5,5]],[6,6]]"

        val expected = 1137L
        val actual = input.toSnailPair().toMagnitude()
        assert(actual == expected) { "Expected:\n$expected\nBut got:\n$actual"}
    }

    @Test
    fun `Example 6 magnitude test`() {
        val input = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"

        val expected = 3488L
        val actual = input.toSnailPair().toMagnitude()
        assert(actual == expected) { "Expected:\n$expected\nBut got:\n$actual"}
    }
}
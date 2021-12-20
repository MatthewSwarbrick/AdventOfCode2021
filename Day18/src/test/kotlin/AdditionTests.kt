import org.junit.jupiter.api.Test

class AdditionTests {
    @Test
    fun `Example 1 snail pairs are added correctly`() {
        val input = listOf(
            "[[[[4,3],4],4],[7,[[8,4],9]]]",
            "[1,1]"
        )

        val expected = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 1)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 2 snail pairs are added correctly`() {
        val input = listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]"
        )

        val expected = "[[[[1,1],[2,2]],[3,3]],[4,4]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 3)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 3 snail pairs are added correctly`() {
        val input = listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]",
            "[5,5]"
        )

        val expected = "[[[[3,0],[5,3]],[4,4]],[5,5]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 4)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 4 snail pairs are added correctly`() {
        val input = listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]",
            "[5,5]",
            "[6,6]"
        )

        val expected = "[[[[5,0],[7,4]],[5,5]],[6,6]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 5)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }

    @Test
    fun `Example 5 snail pairs are added correctly`() {
        val input = listOf(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
            "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
            "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
            "[7,[5,[[3,8],[1,4]]]]",
            "[[2,[2,2]],[8,[8,1]]]",
            "[2,9]",
            "[1,[[[9,3],9],[[9,0],[0,7]]]]",
            "[[[5,[7,4]],7],1]",
            "[[[[4,2],2],6],[8,7]]"
        )

        val expected = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
        val actual = input.toSnailPairs().toSum()
        assert(actual.count() == 9)
        assert(actual.last().toString() == expected) { "Expected: \n$expected\nBut got:\n$actual" }
    }
}
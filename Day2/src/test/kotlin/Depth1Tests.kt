import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Depth1Tests {
    @Test
    fun `Forward commands are ignored`() {
        val input = listOf(
            "forward 5"
        )

        val actual = input.getDepth1()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `Multiple forward commands are ignored`() {
        val input = listOf(
            "forward 5",
            "forward 3",
            "forward 2"
        )

        val actual = input.getDepth1()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `Down commands are calculated correctly`() {
        val input = listOf(
            "down 5"
        )

        val actual = input.getDepth1()
        assertEquals(expected = 5, actual)
    }

    @Test
    fun `Up commands are calculated correctly`() {
        val input = listOf(
            "up 5"
        )

        val actual = input.getDepth1()
        assertEquals(expected = -5, actual)
    }

    @Test
    fun `Down and Up commands are calculated correctly`() {
        val input = listOf(
            "up 5",
            "up 5",
            "down 2"
        )

        val actual = input.getDepth1()
        assertEquals(expected = -8, actual)
    }
}
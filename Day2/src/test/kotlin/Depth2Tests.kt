import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Depth2Tests {
    @Test
    fun `If no forward command then depth is zero`() {
        val input = listOf(
            "down 5",
            "down 3"
        )

        val actual = input.getDepth2()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `If no up and down command then depth is zero`() {
        val input = listOf(
            "forward 5",
            "forward 2"
        )

        val actual = input.getDepth2()
        assertEquals(expected = 0, actual)
    }

    @Test
    fun `Depth is calculated correctly`() {
        val input = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )

        val actual = input.getDepth2()
        assertEquals(expected = 60, actual)
    }
}
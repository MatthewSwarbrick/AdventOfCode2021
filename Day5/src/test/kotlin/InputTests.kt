import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InputTests {
    @Test
    fun `Input line can be converted to coord points`() {
        val (x1, y1, x2, y2) = "1,2 -> 3,4".toCoords()

        assertEquals(1, x1)
        assertEquals(2, y1)
        assertEquals(3, x2)
        assertEquals(4, y2)
    }
}
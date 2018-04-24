package eu.ha3.x.exercise.consumers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets.UTF_8


/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
public class StdinConsumerTest {
    private var originalStdin: InputStream? = null

    @BeforeEach
    internal fun setUp() {
        originalStdin = System.`in`
    }

    @AfterEach
    internal fun tearDown() {
        System.setIn(originalStdin)
    }

    @Test
    public fun `it should read two lines`() {
        // Setup
        System.setIn(ByteArrayInputStream("A\nB2".toByteArray(UTF_8)))

        // Exercise
        val result = StdinConsumer().readLines()

        // Verify
        assertThat(result).isEqualTo(listOf("A", "B2"))
    }

    @Test
    public fun `it should read two lines even with Windows style newlines`() {
        // Setup
        System.setIn(ByteArrayInputStream("A\r\nB2".toByteArray(UTF_8)))

        // Exercise
        val result = StdinConsumer().readLines()

        // Verify
        assertThat(result).isEqualTo(listOf("A", "B2"))
    }

    @Test
    public fun `it should read two lines even with trailing line terminator`() {
        // Setup
        System.setIn(ByteArrayInputStream("A\nB2\n".toByteArray(UTF_8)))

        // Exercise
        val result = StdinConsumer().readLines()

        // Verify
        assertThat(result).isEqualTo(listOf("A", "B2"))
    }
}
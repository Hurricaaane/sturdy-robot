package eu.ha3.x.exercise.listener.stdout

import eu.ha3.x.exercise.domain.Mower
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.Charset

/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
public class StdoutListenerTest {
    private var originalStdout: PrintStream? = null

    private lateinit var substituteStdoutStream: ByteArrayOutputStream
    private lateinit var substituteStdout: PrintStream

    @BeforeEach
    internal fun setUp() {
        originalStdout = System.out

        substituteStdoutStream = ByteArrayOutputStream()
        substituteStdout = PrintStream(substituteStdoutStream)

        System.setOut(substituteStdout)
    }

    @AfterEach
    internal fun tearDown() {
        System.setOut(originalStdout)

        substituteStdout.close()
        substituteStdoutStream.close()
    }

    @Test
    public fun `it should print to the standard output with north`() {
        // Execute
        StdoutListener().invoke(Mower.State(5, 10, Mower.Orientation.NORTH))

        // Verify
        val charsetName = Charset.defaultCharset().name()
        val lineSeparator = System.lineSeparator()
        assertThat(substituteStdoutStream.toString(charsetName)).isEqualTo("5 10 N$lineSeparator")
    }

    @Test
    public fun `it should print to the standard output with east`() {
        // Execute
        StdoutListener().invoke(Mower.State(5, 10, Mower.Orientation.EAST))

        // Verify
        val charsetName = Charset.defaultCharset().name()
        val lineSeparator = System.lineSeparator()
        assertThat(substituteStdoutStream.toString(charsetName)).isEqualTo("5 10 E$lineSeparator")
    }

    @Test
    public fun `it should print to the standard output with south`() {
        // Execute
        StdoutListener().invoke(Mower.State(5, 10, Mower.Orientation.SOUTH))

        // Verify
        val charsetName = Charset.defaultCharset().name()
        val lineSeparator = System.lineSeparator()
        assertThat(substituteStdoutStream.toString(charsetName)).isEqualTo("5 10 S$lineSeparator")
    }

    @Test
    public fun `it should print to the standard output with west`() {
        // Execute
        StdoutListener().invoke(Mower.State(5, 10, Mower.Orientation.WEST))

        // Verify
        val charsetName = Charset.defaultCharset().name()
        val lineSeparator = System.lineSeparator()
        assertThat(substituteStdoutStream.toString(charsetName)).isEqualTo("5 10 W$lineSeparator")
    }
}
package eu.ha3.x.exercise.consumers.stdin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream
import java.nio.charset.Charset


/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
public class StdinConsumerTest {
    private var originalStdout: PrintStream? = null
    private var originalStdin: InputStream? = null

    private lateinit var substituteStdoutStream: ByteArrayOutputStream
    private lateinit var substituteStdout: PrintStream

    @BeforeEach
    internal fun setUp() {
        originalStdout = System.out
        originalStdin = System.`in`

        substituteStdoutStream = ByteArrayOutputStream()
        substituteStdout = PrintStream(substituteStdoutStream)

        System.setOut(substituteStdout)
    }

    @AfterEach
    internal fun tearDown() {
        System.setOut(originalStdout)
        System.setIn(originalStdin)

        substituteStdout.close()
        substituteStdoutStream.close()
    }

    @Test
    public fun `placeholder it should print "Not implemented" to the standard output`() {
        // Exercise
        StdinConsumer.main(emptyArray())

        // Verify
        val charsetName = Charset.defaultCharset().name()
        val lineSeparator = System.lineSeparator()
        assertThat(substituteStdoutStream.toString(charsetName)).isEqualTo("Not implemented$lineSeparator")
    }
}
package eu.ha3.x.exercise.executables.unix

import org.assertj.core.api.Assertions
import org.assertj.core.util.Files
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * (Default template)
 * Created on 2018-04-24
 *
 * @author Ha3
 */
public class UnixExecutableIntegrationTest {
    private var originalStdin: InputStream? = null
    private var originalStdout: PrintStream? = null

    private lateinit var substituteStdoutStream: ByteArrayOutputStream
    private lateinit var substituteStdout: PrintStream

    @BeforeEach
    internal fun setUp() {
        originalStdin = System.`in`
        originalStdout = System.out

        substituteStdoutStream = ByteArrayOutputStream()
        substituteStdout = PrintStream(substituteStdoutStream)

        System.setOut(substituteStdout)
    }

    @AfterEach
    internal fun tearDown() {
        System.setIn(originalStdin)
        System.setOut(originalStdout)

        substituteStdout.close()
        substituteStdoutStream.close()
    }

    @Test
    public fun `acceptance case for a piped input`() {
        // Given
        val input = """5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA"""
        System.setIn(ByteArrayInputStream(input.toByteArray(StandardCharsets.UTF_8)))

        // When
        UnixExecutable.main(emptyArray())

        // Then
        val charsetName = Charset.defaultCharset().name()
        val lineSeparator = System.lineSeparator()
        Assertions.assertThat(substituteStdoutStream.toString(charsetName)).isEqualTo("1 3 N${lineSeparator}5 1 E$lineSeparator")
    }

    @Test
    public fun `acceptance case for a file input`() {
        val file = Files.newTemporaryFile()
        try {
            // Given
            val input = """5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA"""
            file.writeText(input, StandardCharsets.UTF_8)

            // When
            UnixExecutable.main(arrayOf(file.absolutePath))

            // Then
            val charsetName = Charset.defaultCharset().name()
            val lineSeparator = System.lineSeparator()
            Assertions.assertThat(substituteStdoutStream.toString(charsetName)).isEqualTo("1 3 N${lineSeparator}5 1 E$lineSeparator")

        } finally {
            Files.delete(file)
        }
    }
}
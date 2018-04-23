package eu.ha3.x.exercise.consumers.file

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.Files
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets.UTF_8


/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
public class FileConsumerTest {
    private lateinit var file: File

    @BeforeEach
    internal fun setUp() {
        file = Files.newTemporaryFile()
    }

    @AfterEach
    internal fun tearDown() {
        Files.delete(file)
    }

    @Test
    public fun `it should read two lines`() {
        // Setup
        file.writeText("A\nB2", UTF_8)

        // Exercise
        val result = FileConsumer(file.toPath()).readLines()

        // Verify
        assertThat(result).isEqualTo(listOf("A", "B2"))
    }

    @Test
    public fun `it should read two lines even with Windows style newlines`() {
        // Setup
        file.writeText("A\r\nB2", UTF_8)

        // Exercise
        val result = FileConsumer(file.toPath()).readLines()

        // Verify
        assertThat(result).isEqualTo(listOf("A", "B2"))
    }

    @Test
    public fun `it should read two lines even with trailing line terminator`() {
        // Setup
        file.writeText("A\nB2\n", UTF_8)

        // Exercise
        val result = FileConsumer(file.toPath()).readLines()

        // Verify
        assertThat(result).isEqualTo(listOf("A", "B2"))
    }
}
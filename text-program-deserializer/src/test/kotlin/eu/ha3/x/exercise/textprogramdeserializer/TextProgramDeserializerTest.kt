package eu.ha3.x.exercise.textprogramdeserializer

import eu.ha3.x.exercise.domain.Mower.*
import eu.ha3.x.exercise.domain.Program
import eu.ha3.x.exercise.domain.Program.MowingInstruction
import eu.ha3.x.exercise.domain.Surface
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

/**
 * (Default template)
 * Created on 2018-04-12
 *
 * @author Ha3
 */
public class TextProgramDeserializerTest {
    @Test
    public fun `it should not try to deserialize zero lines`() {
        // Exercise and Verify
        assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
            TextProgramDeserializer().deserialize(emptyList())
        }
    }

    @Test
    public fun `it should not try to deserialize the first line given an invalid input`() {
        // Exercise and Verify
        assertAll(
            Executable {
                assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                    TextProgramDeserializer().deserialize(listOf(""))
                }
            },
            Executable {
                assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                    TextProgramDeserializer().deserialize(listOf(""))
                }
            },
            Executable {
                assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                    TextProgramDeserializer().deserialize(listOf("1"))
                }
            },
            Executable {
                assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                    TextProgramDeserializer().deserialize(listOf("1 1 1"))
                }
            },
            Executable {
                assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                    TextProgramDeserializer().deserialize(listOf("x 1"))
                }
            },
            Executable {
                assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                    TextProgramDeserializer().deserialize(listOf("x x"))
                }
            }
        )
    }

    @Test
    public fun `it should deserialize the surface only`() {
        // Exercise
        val result = TextProgramDeserializer().deserialize(listOf("5 10"))

        // Verify
        assertThat(result).isEqualTo(Program(Surface(5, 10), emptyList()))
    }

    @Test
    public fun `it should not try to deserialize the second line given an invalid input`() {
        // Exercise and Verify
        assertAll(
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "5 10", "G"))
                    }
                },
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "x 10 N", "G"))
                    }
                },
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "5 x N", "G"))
                    }
                },
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "5 10 Z", "G"))
                    }
                },
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "5 10 20", "G"))
                    }
                },
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "5 10 N 20", "G"))
                    }
                }
        )
    }

    @Test
    public fun `it should deserialize the second line with north`() {
        // Setup
        val expected = listOf(MowingInstruction(State(5, 10, Orientation.NORTH), emptyList()))

        // Exercise
        val result = TextProgramDeserializer().deserialize(listOf("5 10", "5 10 N", ""))

        // Verify
        assertThat(result).isEqualTo(Program(Surface(5, 10), expected))
    }

    @Test
    public fun `it should deserialize the second line with east`() {
        // Setup
        val expected = listOf(MowingInstruction(State(5, 10, Orientation.EAST), emptyList()))

        // Exercise
        val result = TextProgramDeserializer().deserialize(listOf("5 10", "5 10 E", ""))

        // Verify
        assertThat(result).isEqualTo(Program(Surface(5, 10), expected))
    }

    @Test
    public fun `it should deserialize the second line with south`() {
        // Setup
        val expected = listOf(MowingInstruction(State(5, 10, Orientation.SOUTH), emptyList()))

        // Exercise
        val result = TextProgramDeserializer().deserialize(listOf("5 10", "5 10 S", ""))

        // Verify
        assertThat(result).isEqualTo(Program(Surface(5, 10), expected))
    }

    @Test
    public fun `it should deserialize the second line with west`() {
        // Setup
        val expected = listOf(MowingInstruction(State(5, 10, Orientation.WEST), emptyList()))

        // Exercise
        val result = TextProgramDeserializer().deserialize(listOf("5 10", "5 10 W", ""))

        // Verify
        assertThat(result).isEqualTo(Program(Surface(5, 10), expected))
    }

    @Test
    public fun `it should not try to deserialize the third line given an invalid input`() {
        // Exercise and Verify
        assertAll(
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "5 10 N", "B"))
                    }
                },
                Executable {
                    assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
                        TextProgramDeserializer().deserialize(listOf("5 10", "5 10 N", "G G"))
                    }
                }
        )
    }

    @Test
    public fun `it should deserialize the third line`() {
        // Setup
        val commands = listOf(
                Command.LEFT,
                Command.LEFT,
                Command.RIGHT,
                Command.RIGHT,
                Command.FORWARD,
                Command.FORWARD,
                Command.LEFT,
                Command.RIGHT,
                Command.FORWARD
        )
        val expected = listOf(MowingInstruction(State(5, 10, Orientation.NORTH), commands))

        // Exercise
        val result = TextProgramDeserializer().deserialize(listOf("5 10", "5 10 N", "GGDDAAGDA"))

        // Verify
        assertThat(result).isEqualTo(Program(Surface(5, 10), expected))
    }

    @Test
    public fun `it should deserialize the fourth and more lines`() {
        // Setup
        val expectedA = MowingInstruction(State(5, 10, Orientation.NORTH), listOf(Command.LEFT))
        val expectedB = MowingInstruction(State(1, 2, Orientation.WEST), listOf(Command.RIGHT, Command.FORWARD))

        // Exercise
        val result = TextProgramDeserializer().deserialize(listOf("5 10", "5 10 N", "G", "1 2 W", "DA"))

        // Verify
        assertThat(result).isEqualTo(Program(Surface(5, 10), listOf(expectedA, expectedB)))
    }

    @Test
    public fun `it should not deserialize an even number of lines`() {
        // Exercise and Verify
        assertThrows(TextProgramDeserializer.TextProgramParseException::class.java) {
            TextProgramDeserializer().deserialize(listOf("5 10", "5 10 N", "G", "1 2 W"))
        }
    }
}
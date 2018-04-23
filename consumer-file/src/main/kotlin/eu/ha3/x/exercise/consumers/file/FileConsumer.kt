package eu.ha3.x.exercise.consumers.file

import eu.ha3.x.exercise.consumers.Consumer
import java.nio.file.Files
import java.nio.file.Path

/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
class FileConsumer(private val path: Path) : Consumer {
    override fun readLines(): List<String> {
        return Files.readAllLines(path)
    }
}
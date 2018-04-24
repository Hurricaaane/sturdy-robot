package eu.ha3.x.exercise.consumers

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
class StdinConsumer : Consumer {
    override fun readLines(): List<String> {
        val inputStream = System.`in`
        return BufferedReader(InputStreamReader(inputStream)).use(BufferedReader::readLines)
    }
}
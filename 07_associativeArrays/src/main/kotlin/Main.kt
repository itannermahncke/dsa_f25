package org.example

fun main() {
    val w = BinaryWriter(keepTrackOfBinaryString = true)
    val string = "AABABBBABAABABBBABBABB"
    w.write(string)
    val lza = LempelZiv().encode(
        w.toByteArray(),
    )
    print(lza.toBinaryString())
}
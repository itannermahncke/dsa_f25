package org.example

class LempelZiv {
    /**
     * Encode a string in accordance with the Lempel-Ziv algorithm.
     */
    fun encode(
        bytes: ByteArray,
        keepTrackOfBinaryString: Boolean = false
        ): BinaryWriter {
        // make the codebook
        val codeBook = AssociativeArray<List<Byte>, Int>()
        codeBook[listOf()] = codeBook.size()

        // encoded data will get written here
        val encodedData = BinaryWriter(keepTrackOfBinaryString)

        // lempel ziv important values
        var startPos = 0 // start at the leftmost byte
        var codeWidth = 1 // indicates how many places to pad out binaries (grows with # codes)
        var transition = 2 // powers of 2; indicates when to increase codeWith
        for (i in bytes.indices) {
            // get sequence from start until i
            val sequence = bytes.slice(startPos until i)

            // see if the sequence, including letter at i, is already a key in the codebook
            val codeWord = codeBook[sequence + listOf(bytes[i])]
            if (codeWord == null) {
                encodedData.write(codeBook[sequence]!!, codeWidth)
                encodedData.write(bytes[i])
                // if the codebook has enough elements that binary padding must go up
                if (codeBook.size() == transition) {
                    transition *= 2
                    codeWidth++
                }
                codeBook[sequence + listOf(bytes[i])] = codeBook.size()
                startPos = i + 1
            }
        }
        // get the last item which is guaranteed to already be in the codebook
        val sequence = bytes.slice(startPos until bytes.size)
        encodedData.write(codeBook[sequence]!!, codeWidth)
        return encodedData
    }
}
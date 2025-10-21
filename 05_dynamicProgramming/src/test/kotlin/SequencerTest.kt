import org.example.Sequencer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SequencerTest {

    private val sequencer = Sequencer()

    @Test
    fun testPerfectMatch() {
        val s1 = "AAAA"
        val s2 = "AAAA"
        val score = sequencer.needlemanWunsch(s1, s2)
        assertEquals(4, score, "Perfect match should have a score equal to sequence length")
    }

    @Test
    fun testAllMismatch() {
        val s1 = "AAAA"
        val s2 = "TTTT"
        val score = sequencer.needlemanWunsch(s1, s2)
        assertEquals(-4, score, "All mismatches should yield negative score")
    }

    @Test
    fun testEmptySequences() {
        val s1 = ""
        val s2 = ""
        val score = sequencer.needlemanWunsch(s1, s2)
        assertEquals(0, score, "Two empty sequences should have score 0")
    }

    @Test
    fun testOneEmptySequence() {
        val s1 = "ACTG"
        val s2 = ""
        val score = sequencer.needlemanWunsch(s1, s2)
        assertEquals(s1.length*-1, score, "One empty sequence should yield a score equal to -length")
    }

    @Test
    fun testSingleCharacterMatch() {
        val s1 = "A"
        val s2 = "A"
        val score = sequencer.needlemanWunsch(s1, s2)
        assertEquals(1, score, "Single matching characters should yield +1")
    }

    @Test
    fun testSingleCharacterMismatch() {
        val s1 = "A"
        val s2 = "G"
        val score = sequencer.needlemanWunsch(s1, s2)
        assertEquals(-1, score, "Single mismatch should yield -1")
    }

    @Test
    fun testClassicExampleGATTACA() {
        val s1 = "GATTACA"
        val s2 = "GCATGCU"
        val score = sequencer.needlemanWunsch(s1, s2)
        assertEquals(0, score, "Classic GATTACA/GCATGCU alignment should yield score 0")
    }

    @Test
    fun testSymmetryProperty() {
        val s1 = "ACGT"
        val s2 = "AGCT"
        val score1 = sequencer.needlemanWunsch(s1, s2)
        val score2 = sequencer.needlemanWunsch(s2, s1)
        assertEquals(score1, score2, "Needleman–Wunsch should be symmetric")
    }

    @Test
    fun testPartialMatch() {
        val s1 = "ACGT"
        val s2 = "AGT"
        val score = sequencer.needlemanWunsch(s1, s2)
        // Expected = 1 (A match) - 1 (gap) + 1 (G match) + 1 (T match) = 2
        assertEquals(2, score, "Partial match with one gap should yield score 2")
    }
}

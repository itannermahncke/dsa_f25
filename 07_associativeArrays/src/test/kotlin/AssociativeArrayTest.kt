import org.example.AssociativeArray
import org.junit.jupiter.api.BeforeEach
import kotlin.test.*

class AssociativeArrayTest {
    lateinit var map: AssociativeArray<String, Int>
    @BeforeEach
    fun setup() {
        map = AssociativeArray()
    }
    @Test
    fun testInsertAndGet() {
        map["k1"] = 5
        map["k2"] = 10
        assertEquals(5, map["k1"])
        assertEquals(10, map["k2"])
        assertNull(map["k3"])
    }

    @Test
    fun testReplaceValue() {
        map["k1"] = 5
        map["k1"] = 10
        assertEquals(10, map["k1"])
        assertEquals(1, map.size())
    }

    @Test
    fun testContains() {
        map["k1"] = 5
        map["k2"] = 10
        assertTrue("k1" in map)
        assertTrue("k2" in map)
        assertFalse("k3" in map)
    }

    @Test
    fun testRemove() {
        map["k1"] = 5
        assertTrue(map.remove("k1"))
        assertFalse(map.contains("k1"))
        assertNull(map["k1"])
        assertEquals(0, map.size())
        assertFalse(map.remove("k2"))
    }

    @Test
    fun testCollisionHandling() {
        val map = AssociativeArray<Int, String>()
        // Force same hash index by using small primes or custom hash
        for (i in 0 until 100) map[i] = "val$i"
        for (i in 0 until 100) assertEquals("val$i", map[i])
    }

    @Test
    fun testRehashing() {
        val map = AssociativeArray<Int, Int>()
        val count = 1000
        for (i in 0 until count) map[i] = i
        for (i in 0 until count) assertEquals(i, map[i])
        assertEquals(count, map.size())
    }

    @Test
    fun testKeyValuePairs() {
        map["k1"] = 5
        map["k2"] = 10
        val pairs = map.keyValuePairs()
        assertTrue(pairs.contains("k1" to 5))
        assertTrue(pairs.contains("k2" to 10))
    }
}

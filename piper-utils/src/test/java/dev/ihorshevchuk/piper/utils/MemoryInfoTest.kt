package dev.ihorshevchuk.piper.utils

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Test
import java.io.File

/**
 * Tests for [MemoryInfo]: current process memory usage in bytes,
 * or null where it cannot be determined. VmRSS is read from
 * /proc/self/status.
 */
class MemoryInfoTest {

    @Test
    fun `getMemoryUsage never crashes`() {
        // Must not throw on any platform; the value is null where /proc is absent.
        MemoryInfo.getMemoryUsage()
    }

    @Test
    fun `getMemoryUsage reads VmRSS on Linux`() {
        assumeTrue(File("/proc/self/status").exists())

        val usage = MemoryInfo.getMemoryUsage()

        assertNotNull(usage)
        assertTrue(usage!! > 0)
    }
}

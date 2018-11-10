package test;

import org.junit.Test;

/**
 * Test qui appelle les autres tests
 */
public class AllTests {
    @Test
    public void testLaunch() throws Exception{
        new LecteurDonneesTest().testLire();
    }
}

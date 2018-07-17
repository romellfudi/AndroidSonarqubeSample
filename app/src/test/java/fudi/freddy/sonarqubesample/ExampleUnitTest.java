package fudi.freddy.sonarqubesample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        A a = new A();
        a.dfg(2);
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addition_isNotCorrect() throws Exception {
        assertEquals(5, 3 + 2);
        A a = new A();
        a.dfg(3);
    }
}
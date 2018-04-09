package service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                AdminServiceTest.class,
                PersonServiceTest.class,
                UserServiceTest.class
        })

public class AllServiceTest {
}

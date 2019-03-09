package service;

import java.util.Arrays;
import java.util.List;

public abstract class ServiceFactory {

    private static final List<PersonService> services =
            Arrays.asList(
                new AdminServiceImpl(),
                new PersonServiceImpl(),
                new UserServiceImpl()
            );

    public static <T extends PersonService> T getService(Class<T> cl) {
        for(PersonService service : services)
            if(cl.isInstance(service))
                return cl.cast(service);
        return null;
    }
}

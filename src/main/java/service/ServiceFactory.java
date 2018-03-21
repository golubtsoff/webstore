package service;

public abstract class ServiceFactory {
    private static volatile AdminService adminService;
    private static volatile PersonService personService;
    private static volatile UserService userService;

    public static AdminService getAdminService() {
        if(adminService == null) {
            synchronized(ServiceFactory.class) {
                if(adminService == null) {
                    adminService = new AdminServiceImpl();
                }
            }
        }
        return adminService;
    }

    public static PersonService getPersonService() {
        if(personService == null) {
            synchronized(ServiceFactory.class) {
                if(personService == null) {
                    personService = new PersonServiceImpl();
                }
            }
        }
        return personService;
    }

    public static UserService getUserService() {
        if(userService == null) {
            synchronized(ServiceFactory.class) {
                if(userService == null) {
                    userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }
}

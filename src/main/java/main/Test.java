package main;

import dao.DBService;

/**
 * Created by Evgeniy Golubtsov on 10.02.2018.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        DBService dbs = new DBService();
        dbs.close();
    }
}

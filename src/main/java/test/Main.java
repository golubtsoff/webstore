package test;

import java.nio.file.Paths;

/**
 * Created by Evgeniy Golubtsov on 26.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Paths.get(".", "logs", "webstore.log").toAbsolutePath().normalize());
    }
}

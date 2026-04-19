package lr6;

import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class ServerLauncher {

    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        String webappDir = new File("web").getAbsolutePath();
        tomcat.addWebapp("", webappDir);

        System.out.println("Tomcat started on port " + port);

        tomcat.start();
        tomcat.getServer().await();
    }
}

package lr6;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        // 👉 ОДИН путь, без вариантов
        File webapp = new File("src/main/webapp");
        Context context = tomcat.addContext("", webapp.getAbsolutePath());

        // сервлет
        Tomcat.addServlet(context, "volumeServlet", "lr6.VolumeServlet");
        context.addServletMappingDecoded("/volume", "volumeServlet");

        tomcat.getConnector();

        System.out.println("🔥 Tomcat started on port " + port);

        tomcat.start();
        tomcat.getServer().await();
    }
}

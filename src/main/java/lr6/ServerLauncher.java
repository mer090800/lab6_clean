package lr6;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        // ПОДКЛЮЧАЕМ ПАПКУ web (ВАЖНО!)
String webappDir = new File(
    ServerLauncher.class.getResource("/webapp").toURI()
).getAbsolutePath();
Context context = tomcat.addContext("", webappDir);

        // РЕГИСТРАЦИЯ СЕРВЛЕТА
        Tomcat.addServlet(context, "volumeServlet", "lr6.VolumeServlet");
        context.addServletMappingDecoded("/", "volumeServlet");

        tomcat.getConnector();

        System.out.println("🔥 Tomcat started on port " + port);

        tomcat.start();
        tomcat.getServer().await();
    }
}

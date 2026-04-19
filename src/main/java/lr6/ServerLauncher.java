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
        Context context = tomcat.addContext("", System.getProperty("java.io.tmpdir"));

        // РЕГИСТРАЦИЯ СЕРВЛЕТА
        Tomcat.addServlet(context, "volumeServlet", "lr6.VolumeServlet");
        context.addServletMappingDecoded("/", "volumeServlet");

        tomcat.getConnector();

        System.out.println("🔥 Tomcat started on port " + port);

        tomcat.start();
        tomcat.getServer().await();
    }
}

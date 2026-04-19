package lr6;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        // ВАЖНО: указываем текущую папку как базу
        String baseDir = new File(".").getAbsolutePath();
        Context context = tomcat.addContext("", baseDir);

        Tomcat.addServlet(context, "volumeServlet", new VolumeServlet());
        context.addServletMappingDecoded("/volume", "volumeServlet");

        tomcat.getConnector();

        System.out.println("🔥 Tomcat started on port " + port);

        tomcat.start();
        tomcat.getServer().await();
    }
}

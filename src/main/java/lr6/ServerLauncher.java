package lr6;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        Context context = tomcat.addContext("", null);

        // РЕГИСТРАЦИЯ СЕРВЛЕТА ВРУЧНУЮ
        Tomcat.addServlet(context, "volumeServlet", new VolumeServlet());
        context.addServletMappingDecoded("/volume", "volumeServlet");

        System.out.println("🔥 Tomcat started on port " + port);

        tomcat.start();
        tomcat.getServer().await();
    }
}

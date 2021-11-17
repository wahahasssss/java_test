import py4j.GatewayServer;

public class Py4jTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Py4jTest app = new Py4jTest();
        GatewayServer server = new GatewayServer(app);
        server.start();
    }

}

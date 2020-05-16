
import com.douaiwan.eliminate.pojo.data.ServerData;
import com.google.gson.Gson;

public class TestWebSocket {
    public static void main(String[] argus ){
        ServerData serverData = new ServerData(1);
        serverData.getOnline().put(100l,null);
        System.out.println( new Gson().toJson(serverData));
        System.out.println( new Gson().fromJson(new Gson().toJson(serverData),ServerData.class));
    }
}

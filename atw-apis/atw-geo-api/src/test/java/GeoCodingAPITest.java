import com.tpo.world.api.geo.coding.api.impl.GeoCodingAPI;
import com.tpo.world.api.geo.geo.GeoPoint;

public class GeoCodingAPITest {

    public static void main(String[] args) {
        GeoCodingAPI api = new GeoCodingAPI();
        System.out.println(api.getAddress(new GeoPoint(52.513329, 13.395451)));

    }

}

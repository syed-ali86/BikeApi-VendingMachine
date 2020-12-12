package stepDefinition;








import apiModels.Network;
import apiModels.NetworkStations;
import apiModels.Networks;
import apiModels.Station;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Utils {

    private static URL url;
    private static HttpURLConnection con;
    private static ObjectMapper objectMapper;
    private static Networks networks;
    private static Network network;
    private static NetworkStations stations;
    public static boolean hitURL(){

        try {
            url = new URL("http://api.citybik.es/v2/networks");
            con = (HttpURLConnection) url.openConnection();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean hitURL(String networkId){

        try {
            url = new URL("http://api.citybik.es/v2/networks/"+networkId);
            con = (HttpURLConnection) url.openConnection();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static  int getResponse() {

        try {

            return con.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static  Networks getResponseData() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
               con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine())!=null){
                response.append(inputLine);
            }
            objectMapper = new ObjectMapper();
             networks= objectMapper.readValue(response.toString(),Networks.class);
            return networks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  int getNumberOfStations() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine())!=null){
                response.append(inputLine);
            }
            objectMapper = new ObjectMapper();
            stations= objectMapper.readValue(response.toString(),NetworkStations.class);

            return stations.getNetwork().getStations().size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static  int verifyFreeBike() {

        int count = 0;
        String time = stations.getNetwork().getStations().get(0).getTimestamp();
        String freeBike = stations.getNetwork().getStations().get(0).getFree_bikes();
        if(!time.matches("....-..-..T..:..:..Z")){
            count++;
        }
        if(freeBike==null) {
            count++;
        }
        else if(Integer.parseInt(freeBike)>=0){
            count++;
        }

        return count;
    }





    public static boolean getNetworkInfo(String networkId) {
        boolean flag = false;
        for(Network network2 : networks.getNetworks()){
            network = network2;
            if(network.getId().equals(networkId)){
                    flag = true;
                    break;
              //  assertThat(network.getLocation().getCountry(),equalTo(data.get("country")));
               // assertThat(network.getLocation().getLatitude(),equalTo(Double.valueOf(data.get("latitude"))));
               // assertThat(network.getLocation().getLongitude(),equalTo(Double.valueOf(data.get("longitude"))));
            }

        }
        return flag;
    }

    public static Boolean getLocationInfo(String city, String country) {
        boolean flag = false;

            if(network.getLocation().getCity().equals(city)&&network.getLocation().getCountry().equals(country)){
                flag = true;

                //  assertThat(network.getLocation().getCountry(),equalTo(data.get("country")));
                // assertThat(network.getLocation().getLatitude(),equalTo(Double.valueOf(data.get("latitude"))));
                // assertThat(network.getLocation().getLongitude(),equalTo(Double.valueOf(data.get("longitude"))));
            }


        return flag;
    }

    public static boolean getLocationPosition(String lat, String lon) {
        boolean flag = false;

        if(network.getLocation().getLatitude().equals(Double.parseDouble(lat)) && network.getLocation().getLongitude().equals(Double.parseDouble(lon))){
            flag = true;

            //  assertThat(network.getLocation().getCountry(),equalTo(data.get("country")));
            // assertThat(network.getLocation().getLatitude(),equalTo(Double.valueOf(data.get("latitude"))));
            // assertThat(network.getLocation().getLongitude(),equalTo(Double.valueOf(data.get("longitude"))));
        }


        return flag;
    }

}

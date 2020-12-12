package apiModels;

public class Station {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpty_slots() {
        return empty_slots;
    }

    public void setEmpty_slots(String empty_slots) {
        this.empty_slots = empty_slots;
    }

    public String getFree_bikes() {
        return free_bikes;
    }

    public void setFree_bikes(String free_bikes) {
        this.free_bikes = free_bikes;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



    private String id;
    private String name;
    private String empty_slots;
    private String free_bikes;
    private String latitude;
    private String longitude;
    private String timestamp;

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    private Object extra;


}

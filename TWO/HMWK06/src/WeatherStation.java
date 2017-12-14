
/**
 * Created by Zachary on 10/28/2016.
 */
public class WeatherStation implements Comparable<WeatherStation>{
    String stationName;
    String place;
    String state;
    String country;
    int elevation;
    public WeatherStation (String stationName, String place, String state, String country, int elevation){
        setstationName(stationName);
        setplace(place);
        setState(state);
        setCountry(country);
        setElevation(elevation);
    }
    public String getStationName() {
        return stationName;
    }

    public void setstationName(String stationName) {
        this.stationName = stationName;
    }

    public String getplace() {
        return place;
    }

    public void setplace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
    @Override
    public String toString(){
        return "Station Name:" + stationName +",Location:" +place+", State: "+state+
                ", Country: '"+country+", Elevation: "+elevation;
    }
    @Override
    public int compareTo(WeatherStation o){
        int result=0;
        result=stationName.compareTo(o.stationName);
        return result;
    }
}
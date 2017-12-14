/*Zachary Czarnecki
HMWK06
Ask the user at the keyboard to type in a two-letter state abbreviation. Then it should query the "weather" table in
the database named "misc" on "turing.cs.missouriwestern.edu." Retrieve all the weather stations for the specified state.
Put the records into an ArrayList. Then print the associative array.
*/
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        System.out.println("Write a two letter State Abbreviation to pick a state.");
        ArrayList<WeatherStation> list=queryDatabase("turing.cs.missouriwestern.edu", "misc", "csc254", "age126");
        Collections.sort(list);
        for(WeatherStation place: list){
            System.out.println(place);
        }
    }
    public static ArrayList<WeatherStation> queryDatabase(String server, String database, String user, String password){
        Scanner input=new Scanner(System.in);
        String chosen=input.nextLine();
        ArrayList<WeatherStation> list=new ArrayList<>();
        String connectionString=String.format("jdbc:mysql://%s/%s?user=%s&password=%s", server, database, user, password);
        System.out.println("Connection String: " + connectionString);
        String queryString=String.format("SELECT station, place, state, country, elevation FROM weather WHERE state LIKE '%s' ",chosen);
        try {
            Connection conn = DriverManager.getConnection(connectionString);
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(queryString);
            while(rs.next()){
                String stationName=rs.getString("station");
                String place=rs.getString("place");
                String state=rs.getString("state");
                String country = rs.getString("country");
                int elevation=rs.getInt("elevation");
                WeatherStation monty=new WeatherStation(stationName, place, state, country, elevation);
                list.add(monty);
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Oops! Nuthin' there!");
            System.exit(1);
        }
        return list;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiREST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Uppercase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel
 */
public class apiRESTUppercase {

    public static void postUppercase(Uppercase up) {
        try {
            URL url = new URL(Comms.SERVER_REST + "/entity.uppercase/");
            HttpURLConnection ucon = (HttpURLConnection) url.openConnection();

            ucon.setRequestMethod("POST");
            ucon.setDoInput(true);
            ucon.setDoOutput(true);
            ucon.setRequestProperty("Content-Type", "application/json");
            ucon.setRequestProperty("Accept", "application/json");

            PrintWriter out = new PrintWriter(ucon.getOutputStream(), true);
            Gson gson = new Gson();
            String json = gson.toJson(up);
            System.out.println("apiRestUppercase -> postUppercase (json): "+json);
            out.println(json);
            out.flush();
            ucon.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(ucon.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static List<Uppercase> findAll(){
        try {
            URL url = new URL(Comms.SERVER_REST+"/entity.uppercase/");
            HttpURLConnection ucon = (HttpURLConnection) url.openConnection();
            
            ucon.setRequestMethod("GET");
            ucon.setDoInput(true);
            ucon.setRequestProperty("Content-Type", "application/json");
            ucon.setRequestProperty("Accept", "application/json");
            ucon.connect();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(ucon.getInputStream()));
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00").create();
            // We need to do this because the fromJSON converter has 
            // trouble with JAVA Collections
            Uppercase[] upArray = gson.fromJson(in, Uppercase[].class);
            // Although here I use Arrays.asList(upArray), it is not 
            // good practise because the resulting list can no be modified!!!!
            List<Uppercase> computationsList = Arrays.asList(upArray);
            return computationsList;
        } catch (MalformedURLException ex) {
            Logger.getLogger(apiRESTUppercase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(apiRESTUppercase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
}

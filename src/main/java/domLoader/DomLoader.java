package domLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Karl on 4.09.2017.
 */
public class DomLoader {

    private static String testUrl = "https://www.google.ee/";

    public static void readUrlHtmlSourceCode() throws IOException {
        URL oracle = new URL(testUrl);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();
        System.out.println(a.toString());
    }



}

package com.parcial;

import java.net.*;
import java.io.*;

public class HttpServer {
  public static void main(String[] args) throws IOException {
   ServerSocket serverSocket = null;
   try { 
      serverSocket = new ServerSocket(36000);
   } catch (IOException e) {
      System.err.println("Could not listen on port: 35000.");
      System.exit(1);
   }

   Socket clientSocket = null;
   try {
       System.out.println("Listo para recibir ...");
       clientSocket = serverSocket.accept();
   } catch (IOException e) {
       System.err.println("Accept failed.");
       System.exit(1);
   }
   PrintWriter out = new PrintWriter(
                         clientSocket.getOutputStream(), true);
   BufferedReader in = new BufferedReader(
                         new InputStreamReader(clientSocket.getInputStream()));
   String inputLine, outputLine;
   while ((inputLine = in.readLine()) != null) {
      System.out.println("Recibí: " + inputLine);
      if (!in.ready()) {break; }
   }
   outputLine = "HTTP/1.1 200 OK\r\n"
        + "Content-Type: text/html\r\n"
         + "\r\n"
         + "<!DOCTYPE html>\n"
         + "<html>\n"
         + "<head>\n"
         + "<meta charset=\"UTF-8\">\n"
         + "<title>Title of the document</title>\n"
         + "</head>\n"
         + "<body>\n"
         + "<h1>Mi propio mensaje</h1>\n"
         + "</body>\n"
         + "</html>\n";

    out.println(outputLine);
    out.close(); 
    in.close(); 
    clientSocket.close(); 
    serverSocket.close(); 
  }

  public class HttpConnectionExample {

   private static final String USER_AGENT = "Mozilla/5.0";
   private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";

   public static void main(String[] args) throws IOException {

       URL obj = new URL(GET_URL);
       HttpURLConnection con = (HttpURLConnection) obj.openConnection();
       con.setRequestMethod("GET");
       con.setRequestProperty("User-Agent", USER_AGENT);
       
       int responseCode = con.getResponseCode();
       System.out.println("GET Response Code :: " + responseCode);
       
       if (responseCode == HttpURLConnection.HTTP_OK) { 
           BufferedReader in = new BufferedReader(new InputStreamReader(
                   con.getInputStream()));
           String inputLine;
           StringBuffer response = new StringBuffer();

           while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
           }
           in.close();

           System.out.println(response.toString());
       } else {
           System.out.println("GET request not worked");
       }
       System.out.println("GET DONE");
   }

} 
}

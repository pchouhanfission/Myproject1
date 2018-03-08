package com.server;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class MainAss4 {
	
	public static final String BASE_URI;
    public static final String protocol;
    public static final Optional<String> HOSTNAME;
    public static final String path;
    public static final Optional<String> PORT;
    
    static{
      protocol = "http://";
      HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
      PORT = Optional.ofNullable(System.getenv("PORT"));
      path = "/Assignment4";
      BASE_URI = protocol + HOSTNAME.orElse("localhost") + ":" + PORT.orElse("8080") +  path + "/";
    }
    
    public static HttpServer startServer() {
       final ResourceConfig rc = new ResourceConfig().packages("com.server");
        HttpServer newServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        
        
        newServer.getServerConfiguration().addHttpHandler(
        	    new uploadHandler(),
        	    path + "/upload" );
        
        newServer.getServerConfiguration().addHttpHandler(
        	    new downloadHandler(),
        	    path + "/download" );
        
        return newServer;
}
public static void main(String[] args) throws IOException {
	

	 	final HttpServer server1 = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "+
	 	"%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server1.stop();
}

	
	

}
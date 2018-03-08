package com.server;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

import java.io.File;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

public class uploadHandler extends HttpHandler {

	@Override
	public void service(Request request, Response response) throws Exception {
		
		Options options=new Options();
		DB mydb=factory.open(new File("Myleveldb") ,options);
		
		String x=request.getHeader("img");
		String path="/home/fission/Downloads/PUSHserver1/"+x+"";
		
		try {
			mydb.put(x.getBytes(), path.getBytes());
			response.addHeader("avail","true");
				
		}
		catch(Exception e) {
			response.addHeader("avail","false");
			System.out.println(e);
		}
		finally {
			mydb.close();
		}
		
	}

}

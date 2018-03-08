package com.client;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

import java.io.File;
import java.io.IOException;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;

public class TotalEntryInDB {
	public static void main(String[] arg) throws IOException {
	

		Options options=new Options();
		DB mydb=factory.open(new File("Myleveldb") ,options);
		
		DBIterator iterator=mydb.iterator();
		iterator.seekToFirst();
		while(iterator.hasNext()) {
			
			byte[] key=iterator.peekNext().getKey();
			byte[] value=iterator.peekNext().getValue();
			
			System.out.println("Key :"+new String(key)+"\tValue :"+new String(value));
			iterator.next();
			
			
		}
		iterator.close();
		mydb.close();
		
	}

}

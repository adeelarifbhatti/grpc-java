package com.grpc.javamemcache;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;


public class MemCacheServer 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        System.out.println( "Hello World!" );
        Server server = ServerBuilder.forPort(9090).addService(new MemCacheService()).build();
        server.start();
        System.out.println("String the Server > " + server.getPort() );
        server.awaitTermination();
        
    }
}

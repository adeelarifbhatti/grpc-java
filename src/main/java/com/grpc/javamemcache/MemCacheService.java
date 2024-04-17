package com.grpc.javamemcache;

import com.grpc.javamemcache.proto.Javamemcache.Capital;
import com.grpc.javamemcache.proto.Javamemcache.Country;
import com.grpc.javamemcache.proto.MemCacheGrpc.MemCacheImplBase;

import io.grpc.stub.StreamObserver;

public class MemCacheService extends MemCacheImplBase {

	@Override
	public void query(Country request, StreamObserver<Capital> responseObserver) {
		System.out.println("Inside Queryin the mem cache Service > method query");
		String country = request.getName();
		
		Capital.Builder capital = Capital.newBuilder();
		responseObserver.onNext(capital.build());
		responseObserver.onCompleted();
	}
	
	
	
	
}
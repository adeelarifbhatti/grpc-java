package com.grpc.javamemcache;

import java.util.HashMap;

//import com.google.protobuf.Descriptors.FieldDescriptor;
import com.grpc.javamemcache.proto.Javamemcache.Capital;
import com.grpc.javamemcache.proto.Javamemcache.Country;
import com.grpc.javamemcache.proto.Javamemcache.EnterCountry;
import com.grpc.javamemcache.proto.Javamemcache.Status;
import com.grpc.javamemcache.proto.MemCacheGrpc.MemCacheImplBase;

import io.grpc.stub.StreamObserver;
 
public class MemCacheService extends MemCacheImplBase {
	private HashMap<String, String> cC = new HashMap<>();

	@Override
	public void add(EnterCountry request, StreamObserver<Status> responseObserver) {
		System.out.println("Inside add the mem cache Service > method add " + (!request.getCountry().isEmpty() || !request.getCapital().isEmpty()));
		Status.Builder status = Status.newBuilder();
//		FieldDescriptor fieldDescriptor = request.getDescriptorForType().findFieldByName("country");
//		System.out.println(fieldDescriptor.getName());

		if(request.getCountry().isEmpty() || request.getCapital().isEmpty()) {
			cC.put(request.getCountry(), request.getCapital());
			status.setResponseCode(201).setResponseMessage("Country/Capital Added");
		}
		else if(!request.getCountry().isEmpty() || !request.getCapital().isEmpty()){
				status.setResponseCode(422).setResponseMessage("Please enter the right values");
		}
		responseObserver.onNext(status.build());
		responseObserver.onCompleted();	
	}

	@Override
	public void query(Capital request, StreamObserver<Country> responseObserver) {
	System.out.println("Inside Queryin the mem cache Service > method query");
	Country.Builder country = Country.newBuilder();
	System.out.println(request.getCountry().isEmpty());
	if(request.getCountry().isEmpty()){
		country.setCapital("Please write the right values");
		
	}			
	else if(cC.containsKey(request.getCountry())) {
//		outPut=cC.get(request.getCountry());
		System.out.println("Capital Found " +cC.get(request.getCountry()));
		country.setCapital(cC.get(request.getCountry()));
	}

	else if(!request.getCountry().isEmpty() || !cC.containsKey(request.getCountry())) {
		country.setCapital("capital not found");
	}
	responseObserver.onNext(country.build());
	responseObserver.onCompleted();
	}
	
}
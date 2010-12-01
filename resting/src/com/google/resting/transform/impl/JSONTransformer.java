package com.google.resting.transform.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.resting.component.impl.Alias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.transform.Transformer;
/**
 * Base transformer for transforming JSON response. 
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 * @param <T> Target type
 */
public class JSONTransformer<T> implements Transformer<T, ServiceResponse> {

	@Override
	public T createEntity(String source, Class<T> targetType) {
		Gson gson=new Gson();
		T dest=null;
		try {
			dest = gson.fromJson(source,targetType );
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		return dest;
	}//createEntity
	
	@Override
	public List<T> getEntityList(ServiceResponse serviceResponse, Class<T> targetType, Alias alias){
		List<T> dests=null;
		JSONArray responseArray=null;
		String singleAlias=alias.getSingleAlias();
		
		try {
			JSONObject responseObject=new JSONObject(serviceResponse.getResponseString());
			if(responseObject.has(singleAlias)){
				Object aliasedObject=responseObject.get(singleAlias);
				if (aliasedObject instanceof JSONArray)
					responseArray=responseObject.getJSONArray(singleAlias);
				
				else {
					dests=new ArrayList<T>(1);
					dests.add(createEntity(((JSONObject)aliasedObject).toString(),targetType));
					return dests;

				}
			}
			else 
				return null;
			int arrayLength=responseArray.length();
			dests=new ArrayList<T>(arrayLength);
			for(int i=0;i<arrayLength;i++){
				JSONObject jsonObject=responseArray.getJSONObject(i);
				dests.add(createEntity(jsonObject.toString(), targetType));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return dests;
	}//getEntityList

}//JSONTransformer

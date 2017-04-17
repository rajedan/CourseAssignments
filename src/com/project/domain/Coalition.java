package com.project.domain;

import java.util.List;

/**
 * It represents the coalition formed by n number of Cloud Providers with coalition properties
 * 
 * @author rdansena
 *
 */
public class Coalition {
	
	/**
	 * The cloud providers participated in this Coalition
	 */
	List<String> myCloudProviders;
	
	int myCharacteristicFunctionValue;
	
	public Coalition(List<String> pCloudProviders, int pCharacteristicFunctionValue) {
		myCloudProviders = pCloudProviders;
		myCharacteristicFunctionValue = pCharacteristicFunctionValue;
	}
	
	@Override
	public String toString() {
		return myCloudProviders+" - "+myCharacteristicFunctionValue;
	}
	
}
package com.qa.sdet.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.qa.sdet.util.JSONUtils;
import com.qa.sdet.util.bean.Country;

public class CountryDetails {

	private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

	private static final String serviceURL = "http://restcountries.eu/rest/v2/";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		userChoice();
	}

	public static void userChoice() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\n Search Country By \n 1 for By Name \n 2 for By Code  \n 3 for exit \n ");
		String input;
		try {
			input = reader.readLine();
			if (input.equals("3"))
				System.exit(1);
			System.out.print(" \n Enter your search criteria \n");
			String searchKey = reader.readLine();
			// System.out.print(input);
			switch (input) {
			case "1":
				getCountryDetailsByName(searchKey);
				userChoice();
				break;
			case "2":
					getCountryDetailsByCode(searchKey);				
				userChoice();
				break;
			case "3":
				System.exit(1);
				break;
			default:
				System.out.print("Please enter valid input \n");
				userChoice();
				break;
			}
		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			System.out.print("BAD DATA  \n");
			userChoice();
		}
	}

	public static List<Country> getCountryDetailsByName(String searchKey)
			throws InterruptedException, ExecutionException, IOException, Exception {
		HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "name/" + searchKey)).GET().build();
		CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
		List<Country> covertFromJsonToObject = null;
		// response.thenAccept(res -> System.out.println(res));
		if (response.get().statusCode() == 500)
			System.out.println("Country Details Not Avaialble");
		else if (response.get().statusCode() == 404)
			throw new Exception("Not Found");
		else {
			covertFromJsonToObject = JSONUtils.convertFromJsonToList(response.get().body(),
					new TypeReference<List<Country>>() {
					});
			System.out.println("\n" + covertFromJsonToObject.get(0));
		}
		return covertFromJsonToObject;
	}

	public static Country getCountryDetailsByCode(String searchKey)
			throws InterruptedException, ExecutionException, IOException, Exception {
		HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "alpha/" + searchKey)).GET().build();
		CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
		Country covertFromJsonToObject = null;
		// response.thenAccept(res -> System.out.println(res));
		if (response.get().statusCode() == 500)
			System.out.println("Country Details Not Avaialble");
		else if (response.get().statusCode() == 404)
			throw new Exception("Not Found");
		else {
			covertFromJsonToObject = JSONUtils.covertFromJsonToObject(response.get().body(), Country.class);
			System.out.println("\n" + covertFromJsonToObject);
		}
		return covertFromJsonToObject;
	}

}

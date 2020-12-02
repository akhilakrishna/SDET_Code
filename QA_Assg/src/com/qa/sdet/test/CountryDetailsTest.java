package com.qa.sdet.test;

import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

import com.qa.sdet.main.CountryDetails;
import com.qa.sdet.util.bean.Country;

public class CountryDetailsTest {

	@Test
	public void testGetCountrytDetailsByName_InvalidSearch() throws InterruptedException, ExecutionException, IOException {
		assertThrows(Exception.class, () -> {
			String searchKey = "565655";
			CountryDetails.getCountryDetailsByName(searchKey);
		});
	}

	@Test
	public void testGetContrytDetailsByName() throws InterruptedException, ExecutionException, IOException, Exception {
		String searchKey = "Spain";
		Assert.assertNotNull(CountryDetails.getCountryDetailsByName(searchKey));
	}
	
	@Test
	public void testGetContrytDetailsByNameValidInput() throws InterruptedException, ExecutionException, IOException, Exception {
		String searchKey = "Spain";
		List<Country> lstCountry = CountryDetails.getCountryDetailsByName(searchKey);
		Assert.assertNotNull("Madrid", lstCountry.get(0).getCapital());
	}


	@Test
	public void testGetContrytDetailsByNameAsNull() throws InterruptedException, ExecutionException, IOException {
		assertThrows(Exception.class, () -> {
			CountryDetails.getCountryDetailsByName(null);
		});
	}

	@Test
	public void testGetContrytDetailsByCode_InvalidSearch()
			throws InterruptedException, ExecutionException, IOException, Exception {
		String searchKey = "565655";
		Assert.assertNull(CountryDetails.getCountryDetailsByCode(searchKey).getCapital());
	}

	@Test
	public void testGetContrytDetailsByCode() throws InterruptedException, ExecutionException, IOException, Exception {
		String searchKey = "US";
		Assert.assertNotNull(CountryDetails.getCountryDetailsByCode(searchKey));
	}

	@Test
	public void testGetContrytDetailsByCodeValidData()
			throws InterruptedException, ExecutionException, IOException, Exception {
		Assert.assertEquals("Bogotá", CountryDetails.getCountryDetailsByCode("COL").getCapital());
	}
}

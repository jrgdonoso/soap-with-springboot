package com.okanuzun.springbootsoapserver.endpoint;

import com.okanuzun.springbootsoapserver.repository.CountryRepository;

import jd.soap.webservice.GetCountryByCurrencyRequest;
import jd.soap.webservice.GetCountryByNameRequest;
import jd.soap.webservice.GetCountryResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://jd/soap/webservice";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryByNameRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryByNameRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryByCurrencyRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryByCurrencyRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getCurrency()));

        return response;
    }
}

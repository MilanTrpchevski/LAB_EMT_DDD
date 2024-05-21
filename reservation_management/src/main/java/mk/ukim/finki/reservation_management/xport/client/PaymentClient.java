package mk.ukim.finki.reservation_management.xport.client;

import mk.ukim.finki.reservation_management.domain.valueobjects.Payment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import java.util.Collections;
import java.util.List;

@Service
public class PaymentClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public PaymentClient(@Value("${app.product-catalog.url}")String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Payment> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/payment").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Payment>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}



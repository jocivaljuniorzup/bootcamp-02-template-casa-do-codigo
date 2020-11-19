package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/country")
public class CountryController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createCountry(@RequestBody @Valid NewCountryRequest newCountryRequest){
        Country country = NewCountryRequest.toModel(newCountryRequest);
        entityManager.persist(country);
        return ResponseEntity.ok(NewCountryResponse.fromModel(country));
    }

    @PostMapping(value = "/state")
    @Transactional
    public ResponseEntity<?> createState(@RequestBody @Valid NewCountryStateRequest newCountryStateRequest){
        CountryState countryState = NewCountryStateRequest.toModel(newCountryStateRequest, entityManager);
        entityManager.persist(countryState);
        return ResponseEntity.ok(NewCountryStateResponse.fromModel(countryState));
    }

}
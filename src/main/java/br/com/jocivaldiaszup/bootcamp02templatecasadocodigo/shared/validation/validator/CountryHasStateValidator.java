package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.validator;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.Country;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.CountryState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase.NewPurchaseRequest;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.exception.FieldMessage;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CountryHasState;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CountryHasStateValidator implements ConstraintValidator<CountryHasState, NewPurchaseRequest> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(CountryHasState constraintAnnotation) {

    }

    @Override
    public boolean isValid(NewPurchaseRequest newPurchaseRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (newPurchaseRequest == null)
            return false;

        List<FieldMessage> list = new ArrayList<>();

        Country country = entityManager.find(Country.class, newPurchaseRequest.getCountryId());

        if (country == null)
            return false;

        Query query = entityManager.createQuery("select t.id from CountryState t where country_id = :value");

        List resultList = query.setParameter("value", country.getId()).getResultList();

        if(newPurchaseRequest.getCountryStateId() == null && resultList.size() > 0){
            //Without state in a country that has states.
            list.add(new FieldMessage("countryId", "Country has states."));
        } else if (newPurchaseRequest.getCountryStateId() != null && !resultList.contains(newPurchaseRequest.getCountryStateId())) {
            //State and Country match
            list.add(new FieldMessage("countryId", "This state doest not belong to country."));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}

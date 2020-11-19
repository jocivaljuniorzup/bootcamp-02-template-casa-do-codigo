package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String fieldName;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if(object == null){
            return true;
        }

        Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+fieldName+"=:value");
        query.setParameter("value", object);

        List<?> list = query.getResultList();

        Assert.isTrue(list.size() <= 1,
                "Unique Id violated for attribute "+ fieldName +" of class "+klass.getName()+".");

        return !list.isEmpty();
    }

}

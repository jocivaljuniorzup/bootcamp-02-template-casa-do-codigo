package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.Country;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.CountryState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CountryHasState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CpfCnpj;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.ExistsId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@CountryHasState
public class NewPurchaseRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @CpfCnpj(message = "Invalid CPF / CNPJ")
    private String document;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotNull
    @ExistsId(domainClass = Country.class, fieldName = "id")
    private Long countryId;

    @ExistsId(domainClass = CountryState.class, fieldName = "id")
    private Long countryStateId;

    @NotBlank
    private String telephoneNumber;

    @NotBlank
    private String zipCode;

    @NotNull
    @Valid
    private NewPurchaseDetailRequest detail;

    public NewPurchaseRequest(@NotBlank @Email String email,
                              @NotBlank String firstName,
                              @NotBlank String lastName,
                              @NotBlank String document,
                              @NotBlank String address,
                              @NotBlank String complement,
                              @NotBlank String city,
                              @NotNull Long countryId,
                              @NotBlank String telephoneNumber,
                              @NotBlank String zipCode,
                              @NotNull NewPurchaseDetailRequest detail) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.countryId = countryId;
        this.telephoneNumber = telephoneNumber;
        this.zipCode = zipCode;
        this.detail = detail;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getCountryStateId() {
        return countryStateId;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public NewPurchaseDetailRequest getDetail() {
        return detail;
    }

    public void setCountryStateId(Long countryStateId) {
        this.countryStateId = countryStateId;
    }

    public static Purchase toModel(NewPurchaseRequest newPurchaseRequest, EntityManager entityManager) {
        Country country = entityManager.find(Country.class, newPurchaseRequest.getCountryId());
        Assert.notNull(country, "The country sent is not registered. Id: " + newPurchaseRequest.getCountryId());

        CountryState countryState = null;
        if(newPurchaseRequest.getCountryStateId() != null) {
            countryState = entityManager.find(CountryState.class, newPurchaseRequest.getCountryStateId());
            Assert.notNull(countryState, "The country state sent is not registered. Id: " + newPurchaseRequest.getCountryStateId());
        }

        Set<NewPurchaseItemRequest> newOrderItemsRequests = newPurchaseRequest.getDetail().getNewOrderItemsRequests();

        Set<PurchaseItem> purchaseItemSet = newOrderItemsRequests.stream()
                .map(x -> NewPurchaseItemRequest.toModel(x, entityManager))
                .collect(Collectors.toSet());

        Purchase purchase = new PurchaseBuilder()
                .setEmail(newPurchaseRequest.getEmail())
                .setFirstName(newPurchaseRequest.getFirstName())
                .setLastName(newPurchaseRequest.getLastName())
                .setCpfCnpj(newPurchaseRequest.getDocument())
                .setAddress(newPurchaseRequest.getAddress())
                .setComplement(newPurchaseRequest.getComplement())
                .setCity(newPurchaseRequest.getCity())
                .setCountry(country)
                .setCountryState(countryState)
                .setTelephoneNumber(newPurchaseRequest.getTelephoneNumber())
                .setZipCode(newPurchaseRequest.getZipCode())
                .setTotalValue(newPurchaseRequest.getDetail().getTotalValue())
                .setPurchaseItemSet(purchaseItemSet)
                .setStatus(PurchaseStatus.OPENED)
                .build();

        return purchase;
    }

    @Override
    public String toString() {
        return "NewPurchaseRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cpfCnpj='" + document + '\'' +
                ", address='" + address + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", country=" + countryId +
                ", countryState=" + countryStateId +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", newPurchaseDetailRequest=" + detail +
                '}';
    }
}

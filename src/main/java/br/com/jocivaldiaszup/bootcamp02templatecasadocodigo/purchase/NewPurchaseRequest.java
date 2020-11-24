package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.Country;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.CountryState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon.Coupon;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CountryHasState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CpfCnpj;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.ExistsId;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.UniqueField;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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

    @ExistsId(domainClass = Coupon.class, fieldName = "code", message = "Invalid coupon code")
    private String couponCode;

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
                              @NotNull @Valid NewPurchaseDetailRequest detail) {

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

    public String getCouponCode() {
        return couponCode;
    }

    public void setCountryStateId(Long countryStateId) {
        this.countryStateId = countryStateId;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    //8
    public static Purchase toModel(NewPurchaseRequest newPurchaseRequest, EntityManager entityManager) {
        //1
        Country country = entityManager.find(Country.class, newPurchaseRequest.getCountryId());
        Assert.notNull(country, "The country sent is not registered. Id: " + newPurchaseRequest.getCountryId());

        //2
        CountryState countryState = null;
        if(newPurchaseRequest.getCountryStateId() != null) {
            countryState = entityManager.find(CountryState.class, newPurchaseRequest.getCountryStateId());
            Assert.notNull(countryState, "The country state sent is not registered. Id: " + newPurchaseRequest.getCountryStateId());
        }

        //2
        Coupon coupon = null;
        if(newPurchaseRequest.getCouponCode() != null) {
            coupon = (Coupon) entityManager.createQuery("select t from Coupon t where t.code = :value", Coupon.class)
                    .setParameter("value", newPurchaseRequest.getCouponCode())
                    .getSingleResult();

            Assert.notNull(coupon, "Invalid coupon code");
            Assert.isTrue(coupon.isValid(), "Expired coupon");
        }

        //1
        Set<NewPurchaseItemRequest> newOrderItemsRequests = newPurchaseRequest.getDetail().getNewOrderItemsRequests();

        //2
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
                .setCoupon(coupon)
                .build();

        Assert.isTrue(purchase.validateTotal(), "Invalid Total");
        purchase.applyCoupon();

        return purchase;
    }

    @Override
    public String toString() {
        return "NewPurchaseRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", address='" + address + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", countryId=" + countryId +
                ", countryStateId=" + countryStateId +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", detail=" + detail +
                '}';
    }
}

package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NewPurchaseResponse {

    private String email;

    private String firstName;

    private String lastName;

    private String document;

    private String address;

    private String complement;

    private String city;

    private String country;

    private String countryState;

    private String telephoneNumber;

    private String zipCode;

    private BigDecimal totalValue;

    private Boolean couponExists;

    private BigDecimal netValue;

    private Set<NewPurchaseItemResponse> items = new HashSet<>();

    public NewPurchaseResponse(String email,
                               String firstName,
                               String lastName,
                               String document,
                               String address,
                               String complement,
                               String city,
                               String country,
                               String telephoneNumber,
                               String zipCode,
                               BigDecimal totalValue,
                               Boolean couponExists,
                               BigDecimal netValue,
                               Set<NewPurchaseItemResponse> items) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.telephoneNumber = telephoneNumber;
        this.zipCode = zipCode;
        this.totalValue = totalValue;
        this.couponExists = couponExists;
        this.netValue = netValue;
        this.items = items;
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

    public String getCountry() {
        return country;
    }

    public String getCountryState() {
        return countryState;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Boolean getCouponExists() {
        return couponExists;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public Set<NewPurchaseItemResponse> getItems() {
        return items;
    }

    public void setCountryState(String countryState) {
        this.countryState = countryState;
    }

    public static NewPurchaseResponse fromModel(Purchase purchase) {

        Set<NewPurchaseItemResponse> newPurchaseItemResponseSet = purchase.getPurchaseItemSet().stream()
                .map(NewPurchaseItemResponse::toModel)
                .collect(Collectors.toSet());


        NewPurchaseResponse newPurchaseResponse = new NewPurchaseResponse(
                purchase.getEmail(),
                purchase.getFirstName(),
                purchase.getLastName(),
                purchase.getDocument(),
                purchase.getAddress(),
                purchase.getComplement(),
                purchase.getCity(),
                purchase.getCountry().getName(),
                purchase.getTelephoneNumber(),
                purchase.getZipCode(),
                purchase.calculateTotalValue(),
                purchase.getCoupon() != null,
                purchase.getTotalValue(),
                newPurchaseItemResponseSet
        );

        if( purchase.getCountryState() != null ){
            newPurchaseResponse.setCountryState(purchase.getCountryState().getName());
        }

        return newPurchaseResponse;
    }
}

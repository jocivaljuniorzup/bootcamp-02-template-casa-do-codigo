package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.Country;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.CountryState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon.Coupon;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CpfCnpj;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PurchaseBuilder {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @CpfCnpj(message = "Invalid CPF / CNPJ")
    private String cpfCnpj;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotNull
    private Country country;

    private CountryState countryState;

    @NotBlank
    private String telephoneNumber;

    @NotBlank
    private String zipCode;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal totalValue;

    @NotNull
    @Size(min = 1)
    private Set<PurchaseItem> purchaseItemSet = new HashSet<>();

    @NotNull
    private PurchaseStatus status;

    private Coupon coupon;

    public PurchaseBuilder() {
    }

    public PurchaseBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PurchaseBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PurchaseBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PurchaseBuilder setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    public PurchaseBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public PurchaseBuilder setComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public PurchaseBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public PurchaseBuilder setCountry(Country country) {
        this.country = country;
        return this;
    }

    public PurchaseBuilder setCountryState(CountryState countryState) {
        this.countryState = countryState;
        return this;
    }

    public PurchaseBuilder setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public PurchaseBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public PurchaseBuilder setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public PurchaseBuilder setPurchaseItemSet(Set<PurchaseItem> purchaseItemSet) {
        this.purchaseItemSet = purchaseItemSet;
        return this;
    }

    public PurchaseBuilder setStatus(PurchaseStatus status) {
        this.status = status;
        return this;
    }

    public PurchaseBuilder setCoupon(Coupon coupon) {
        this.coupon = coupon;
        return this;
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

    public String getCpfCnpj() {
        return cpfCnpj;
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

    public Country getCountry() {
        return country;
    }

    public CountryState getCountryState() {
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

    public Set<PurchaseItem> getPurchaseItemSet() {
        return purchaseItemSet;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public Purchase build(){
        return new Purchase(this);
    }
}

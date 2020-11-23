package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.purchase;

import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.Country;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.country.CountryState;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.coupon.Coupon;
import br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.validation.CpfCnpj;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Purchase implements Serializable {
    private static final long serialVersionUID = -886518882619733813L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne(optional = false)
    private Country country;

    @ManyToOne
    private CountryState countryState;

    @NotBlank
    private String telephoneNumber;

    @NotBlank
    private String zipCode;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal totalValue;

    @NotNull
    @ElementCollection()
    @CollectionTable(name="purchase_item")
    @Size(min = 1)
    private Set<PurchaseItem> purchaseItemSet = new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @ManyToOne
    private Coupon coupon;

    @Deprecated
    public Purchase() {
    }

    public Purchase(@NotBlank @Email String email,
                    @NotBlank String firstName,
                    @NotBlank String lastName,
                    @NotBlank String document,
                    @NotBlank String address,
                    @NotBlank String complement,
                    @NotBlank String city,
                    @NotNull Country country,
                    @NotBlank String telephoneNumber,
                    @NotBlank String zipCode,
                    @NotNull @DecimalMin(value = "0.00", inclusive = false) BigDecimal totalValue,
                    @NotNull @Size(min = 1) Set<PurchaseItem> purchaseItemSet,
                    @NotBlank PurchaseStatus status) {

        Assert.hasText(email, "Email cant be blank");
        Assert.hasText(firstName, "First name cant be blank");
        Assert.hasText(lastName, "Last name cant be blank");
        Assert.hasText(document, "CPF/CNPJ cant be blank");
        Assert.hasText(address, "Address cant be blank");
        Assert.hasText(complement, "Complement cant be blank");
        Assert.hasText(city, "City cant be blank");
        Assert.notNull(country, "Country cant be null");
        Assert.hasText(telephoneNumber, "Telephone Number cant be blank");
        Assert.hasText(zipCode, "ZipCode cant be blank");
        Assert.isTrue(totalValue.compareTo(BigDecimal.ZERO) == 1, "Total value should be greater than 0");
        Assert.isTrue(purchaseItemSet.size() >= 1, "You must add at least one item to the cart");
        Assert.notNull(status, "Status cant be null");

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
        this.purchaseItemSet = purchaseItemSet;
        this.status = status;
    }

    public Purchase(PurchaseBuilder purchaseBuilder) {

        Assert.hasText(purchaseBuilder.getEmail(), "Email cant be blank");
        Assert.hasText(purchaseBuilder.getFirstName(), "First name cant be blank");
        Assert.hasText(purchaseBuilder.getLastName(), "Last name cant be blank");
        Assert.hasText(purchaseBuilder.getCpfCnpj(), "CPF/CNPJ cant be blank");
        Assert.hasText(purchaseBuilder.getAddress(), "Address cant be blank");
        Assert.hasText(purchaseBuilder.getComplement(), "Complement cant be blank");
        Assert.hasText(purchaseBuilder.getCity(), "City cant be blank");
        Assert.notNull(purchaseBuilder.getCountry(), "Country cant be null");
        Assert.hasText(purchaseBuilder.getTelephoneNumber(), "Telephone Number cant be blank");
        Assert.hasText(purchaseBuilder.getZipCode(), "ZipCode cant be blank");
        Assert.isTrue(purchaseBuilder.getTotalValue().compareTo(BigDecimal.ZERO) == 1, "Total value should be greater than 0.");
        Assert.isTrue(purchaseBuilder.getPurchaseItemSet().size() >= 1, "You must add at least one item to the cart.");
        Assert.notNull(purchaseBuilder.getStatus(), "Status cant be null");

        this.email = purchaseBuilder.getEmail();
        this.firstName = purchaseBuilder.getFirstName();
        this.lastName = purchaseBuilder.getLastName();
        this.document = purchaseBuilder.getCpfCnpj();
        this.address = purchaseBuilder.getAddress();
        this.complement = purchaseBuilder.getComplement();
        this.city = purchaseBuilder.getCity();
        this.country = purchaseBuilder.getCountry();
        this.countryState = purchaseBuilder.getCountryState();
        this.telephoneNumber = purchaseBuilder.getTelephoneNumber();
        this.zipCode = purchaseBuilder.getZipCode();
        this.totalValue = purchaseBuilder.getTotalValue();
        this.purchaseItemSet = purchaseBuilder.getPurchaseItemSet();
        this.status = purchaseBuilder.getStatus();
        this.coupon = purchaseBuilder.getCoupon();
    }

    public BigDecimal calculateTotalValue(){
        return this.purchaseItemSet.stream()
                .map(x -> x.getValue().multiply(BigDecimal.valueOf(x.getQuantity())))
                .reduce(BigDecimal.ZERO, (subtotal, value) -> subtotal.add(value));
    }

    public boolean validateTotal(){
        BigDecimal total = this.calculateTotalValue();
        return total.compareTo(this.totalValue) == 0;
    }

    public void applyCoupon() {
        if(this.coupon != null) {
            BigDecimal netValue = this.coupon.getDiscountPercentage()
                    .divide(BigDecimal.valueOf(100l))
                    .multiply(this.totalValue);
            this.totalValue = this.totalValue.subtract(netValue);
            this.status = PurchaseStatus.COUPONAPPLIED;
        }
    }

    public Long getId() {
        return id;
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

    public void setCountryState(CountryState countryState) {
        this.countryState = countryState;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", address='" + address + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", countryState=" + countryState +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", totalValue=" + totalValue +
                ", purchaseItemSet=" + purchaseItemSet +
                ", status=" + status +
                ", coupon=" + coupon +
                '}';
    }
}

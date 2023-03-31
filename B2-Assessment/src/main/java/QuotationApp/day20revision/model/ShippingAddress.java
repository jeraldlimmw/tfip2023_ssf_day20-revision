package QuotationApp.day20revision.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ShippingAddress implements Serializable{
    @NotNull(message="name must not be null")
    @NotBlank(message="Must provide name")
    @Size(min=2, message="Name must be at least 2 characters long")
    private String name;

    @NotNull(message="address must not be null")
    @NotBlank(message="Must provide an address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

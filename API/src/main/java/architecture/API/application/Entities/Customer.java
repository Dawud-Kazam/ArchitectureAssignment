package architecture.API.application.Entities;

import jakarta.persistence.Id;

import java.util.Objects;

public class Customer {
    private @Id Long customerID;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Customer id: " + customerID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer c = (Customer) obj;
        return c.getCustomerID().equals(this.customerID);
    }
}

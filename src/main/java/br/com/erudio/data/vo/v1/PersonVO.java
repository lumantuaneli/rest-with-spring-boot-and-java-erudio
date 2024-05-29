package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"id", "firstName", "lastName", "address", "gender"})
public class PersonVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    @JsonProperty("first-name")
    private String firstName;
    
    @JsonProperty("last-name")
    private String lastName;
    
    private String address;
    
    @JsonIgnore
    private String gender;
    
    public PersonVO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pFirstName) {
        firstName = pFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String pLastName) {
        lastName = pLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String pGender) {
        gender = pGender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, firstName, gender, id, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonVO other = (PersonVO) obj;
        return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
            && Objects.equals(lastName, other.lastName);
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

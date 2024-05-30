package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    private Long personId;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVO() {
        super();
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long pPersonpId) {
        personId = pPersonpId;
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
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(address, firstName, gender, lastName, personId);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PersonVO other = (PersonVO) obj;
        return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender) && Objects.equals(lastName, other.lastName)
            && Objects.equals(personId, other.personId);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

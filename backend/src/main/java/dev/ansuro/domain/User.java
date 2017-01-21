package dev.ansuro.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Andy
 */
@Entity(name = "_User")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private Customer customer;
    
    @Email
    @Column(unique = true)
    private String mail;
    
    //@JsonIgnore
    private String password;
    
    @ManyToMany
    @JoinTable(name = "user_authorities",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Customer getCustomer() {
//        return customer;
//    }
//    
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

//    public void addCustomer(Customer customer) {
//        customer.setUser(this);
//        this.customer = customer;
//    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", mail=" + mail + ", password=" + password + ", authorities=" + authorities + '}';
    }
}

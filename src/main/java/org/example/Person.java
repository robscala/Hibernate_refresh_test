package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="person")
public class Person
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    private String lastName;

    private String firstName;

    @OneToOne(orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "LOGINACCOUNT_ID", foreignKey = @ForeignKey())
    private LoginAccount loginAccount = null;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
}

package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="login_account")
public class LoginAccount
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    private String name = null;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", foreignKey = @ForeignKey())
    private Person owner;
}

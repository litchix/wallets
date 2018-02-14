package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
@Entity
public class JpaWallet implements Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    int id;
    String name;
    @Transient //Don't want to save in database. it is a Business attribute, not a DB item
    List<JpaLine> lines = new ArrayList<>();
    //Liskov substitute principe for further reading

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<JpaLine> getLines() {
        return lines;
    }

    public void setLines(List<JpaLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

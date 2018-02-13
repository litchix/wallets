package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
@Entity
public class JpaUser implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //ajout auto incrément au nouveau users
    int id;
    String name;

    //List<Wallet> wallets = new ArrayList<>();

    //unwritten default empty constructor

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Wallet> getWallets() {
        return null; //this.wallets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWallets(List<Wallet> wallets) {
        //this.wallets = wallets;
    }
}

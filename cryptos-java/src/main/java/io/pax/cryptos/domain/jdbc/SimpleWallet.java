package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

/**
 * Created by AELION on 06/02/2018.
 */
public class SimpleWallet implements Wallet {

    int id;
    String name;

    public SimpleWallet() {//constructor vide pour FullWallet
    }

    public SimpleWallet(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
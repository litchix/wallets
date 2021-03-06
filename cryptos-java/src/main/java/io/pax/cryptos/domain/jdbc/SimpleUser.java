package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
public class SimpleUser implements User {

    int id;
    String name;

    public SimpleUser() {}

    public SimpleUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Wallet> getWallets() {
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
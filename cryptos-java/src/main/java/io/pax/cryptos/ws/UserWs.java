package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
@Path("users")//chemin relatif pour avoir "/cryptos/api/users"
@Produces(MediaType.APPLICATION_JSON)

public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();

        return dao.listUsers();
    }
}

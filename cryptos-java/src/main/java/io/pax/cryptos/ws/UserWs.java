package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
@Path("users")//chemin relatif pour avoir "/cryptos/api/users"
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}")//this is a PathParam (ds la barre d'adresse)
    public User getUser(@PathParam("id") int userId) throws SQLException {
        return new UserDao().findUserWithWallets(userId);
    }

    @POST

    public User createUser(SimpleUser user) {
        String userName = user.getName();
        if (userName == null) {
            throw new NotAcceptableException("No user name sent");
        }
        if (userName.length() < 2) {
            throw new NotAcceptableException("406: Wallet name must have at least 2 letters");
        }
        try {
            int id = new UserDao().createUser(userName);
            return new SimpleUser(id, userName);
        } catch (SQLException e) {
            throw new ServerErrorException("Database error, sorry", 500);
        }

    }


}

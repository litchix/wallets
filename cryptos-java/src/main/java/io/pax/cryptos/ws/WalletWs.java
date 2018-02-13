package io.pax.cryptos.ws;

import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.jdbc.FullWallet;
import io.pax.cryptos.domain.jdbc.SimpleUser;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
@Path("wallets")//chemin relatif pour avoir "/cryptos/api/wallets"

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletWs {

    @GET
    public List<Wallet> getWallets() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

    //JaxRS annotation
    @POST
    //return future wallet with an id
    public Wallet createWallet(FullWallet wallet) { /*sent wallet, has no id*/

        User user = wallet.getUser();
        if (user == null) {
            //400x: navigator sent wrong informations
            throw new NotAcceptableException("No user id sent");

        }
        if (wallet.getName().length() < 2) {
            throw new NotAcceptableException("406: Wallet name must have at least 2 letters");

        }

        try {
            int id = new WalletDao().createWallet(user.getId(), wallet.getName());

            User boundUser = wallet.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            return new FullWallet(id, wallet.getName(), simpleUser);

        } catch (SQLException e) {
            throw new ServerErrorException("Database error, sorry", 500);
        }

    }

}
package io.pax.cryptos.business;

import io.pax.cryptos.domain.Wallet;
import io.pax.cryptos.domain.jpa.JpaLine;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * It is a Java EE Managed EJB
 * EJB is a Super Object that does everything in your back
 */
@Stateless
public class WalletBusiness {

    //EntityManager is given by Wildfly. It's a managed object
    @PersistenceContext
    EntityManager em;

    public JpaWallet findWallet(int id) {
        //transaction is opened in your back
        JpaWallet w = em.find(JpaWallet.class, id);

        String jpql = "SELECT l FROM JpaLine l JOIN l.wallet w WHERE w.id = :id"; //recupere le param "id"

        List<JpaLine> lines = em.createQuery(jpql, JpaLine.class)
                .setParameter("id", id)
                .getResultList();

        w.setLines(lines);

        return w;

    }//and now closed.


}

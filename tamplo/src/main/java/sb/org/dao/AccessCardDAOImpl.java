package sb.org.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sb.org.model.AccessCard;

import java.util.List;

@Repository
public class AccessCardDAOImpl implements AccessCardDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addAccessCard(AccessCard accessCard) {
        sessionFactory.getCurrentSession().saveOrUpdate(accessCard);
    }

    @Override
    public List<AccessCard> getAllAccessCard() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccessCard.class);
        criteria.setMaxResults(10);
        return criteria.list();
    }

    @Override
    public void deleteAccessCard(Integer accessCardId) {
        AccessCard accessCard = (AccessCard) sessionFactory.getCurrentSession().load(
                AccessCard.class, accessCardId);
        if (null != accessCard) {
            this.sessionFactory.getCurrentSession().delete(accessCard);
        }
    }

    @Override
    public AccessCard getAccessCard(int accessCardId) {
        return (AccessCard) sessionFactory.getCurrentSession().get(AccessCard.class,accessCardId);
    }

    @Override
    public AccessCard updateAccessCard(AccessCard accessCard) {
        sessionFactory.getCurrentSession().update(accessCard);
        return accessCard;

    }

    @Override
    public void getEmployeeId(Integer employeeId) {
        System.out.println("employeeId "+employeeId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccessCard.class);
        criteria.add(Restrictions.eq("employee_id", employeeId));
        System.out.println("Get Employee ID");
        System.out.println(criteria.list().size());
        System.out.println("Size is above");
    }

}

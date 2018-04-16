package sb.org.dao;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sb.org.model.Employee;
import sb.org.model.Meeting;

import java.util.List;

@Repository
public class MeetingDAOImpl implements MeetingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMeeting(Meeting meeting) {
        sessionFactory.getCurrentSession().saveOrUpdate(meeting);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Meeting.class);
        criteria.setMaxResults(10);
        return criteria.list();
    }

    @Override
    public void deleteMeeting(Integer meetingId) {
        Meeting meeting = (Meeting) sessionFactory.getCurrentSession().load(
                Meeting.class, meetingId);
        if (null != meeting) {
            this.sessionFactory.getCurrentSession().delete(meeting);
        }
    }

    @Override
    public Meeting getMeeting(int meetingId) {
        return (Meeting)sessionFactory.getCurrentSession().get(Meeting.class,meetingId);
    }

    @Override
    public Meeting updateMeeting(Meeting meeting) {
        sessionFactory.getCurrentSession().update(meeting);
        return meeting;
    }

    @Override
    public List<Employee> getUnEnrolledEmployees(Integer meetingId) {
        System.out.println("Method getUnEnrolledEmployees Called");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Meeting.class)
                .add( Restrictions.eq("id", meetingId) )
                .setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id"));

                        /*.createCriteria("employee").add(Restrictions.like("name", "%a%"));*/
        /*List<Employee> employees = sessionFactory.getCurrentSession().createCriteria(Meeting.class)
                .add( Restrictions.like("id", meetingId) )
                .createCriteria("employee")
                .list();*/
        System.out.println("Employees size() "+criteria.list().size());
        return criteria.list();
    }


    @Override
    public List<Employee> getUnEnrolledEmployeesDetails(Integer meetingId) {
       /* System.out.println("getUnEnrolledEmployeesDetails ");
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Meeting.class)
                .createAlias("meeting", "meeting")
                .createAlias("meeting.employee", "employee")
                .add(Restrictions.eq("employee_id", meetingId));

        System.out.println("Employees size() "+criteria.list().size());
        return criteria.list();*/
       return null;
    }
}

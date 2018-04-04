package sb.org.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sb.org.model.Meeting;

import java.util.List;

@Repository
public class MeetingDAOImpl implements MeetingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addAccessCard(Meeting meeting) {
        sessionFactory.getCurrentSession().saveOrUpdate(meeting);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Meeting.class);
        criteria.setMaxResults(10);
        return criteria.list();
    }

    @Override
    public void deleteTask(Integer meetingId) {
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
}

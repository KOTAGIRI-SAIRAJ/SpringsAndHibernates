package sb.org.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.org.dao.MeetingDAO;
import sb.org.model.Employee;
import sb.org.model.Meeting;

import java.util.List;

@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingDAO meetingDAO;

    @Override
    @Transactional
    public void addMeeting(Meeting meeting) {
        meetingDAO.addMeeting(meeting);
    }

    @Override
    @Transactional
    public List<Meeting> getAllMeetings() {
        return meetingDAO.getAllMeetings();
    }

    @Override
    @Transactional
    public void deleteMeeting(Integer meetingId) {
        meetingDAO.deleteMeeting(meetingId);
    }

    @Override
    @Transactional
    public Meeting getMeeting(int meetingId) {
        Meeting meeting = meetingDAO.getMeeting(meetingId);
        Hibernate.initialize(meeting.getEmployees());
        return meeting;
    }

    @Override
    @Transactional
    public Meeting updateMeeting(Meeting meeting) {
        return meetingDAO.updateMeeting(meeting);
    }

    /*@Override
    public List<Employee> getUnEnrolledEmployeesService(Integer meetingId){
        return meetingDAO.getUnEnrolledEmployees(meetingId);
    }

    @Override
    public List<Employee> getUnEnrolledEmployeesDetails(Integer meetingId){
        return meetingDAO.getUnEnrolledEmployeesDetails(meetingId);
    }*/

}

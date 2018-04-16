package sb.org.dao;

import sb.org.model.Employee;
import sb.org.model.Meeting;

import java.util.List;

public interface MeetingDAO {

    public void addMeeting(Meeting meeting);

    public List<Meeting> getAllMeetings();

    public List<Employee> getUnEnrolledEmployees(Integer meetingId);

    public List<Employee> getUnEnrolledEmployeesDetails(Integer meetingId);

    public void deleteMeeting(Integer meetingId);

    public Meeting getMeeting(int meetingId);

    public Meeting updateMeeting(Meeting meeting);
}

package sb.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.org.dao.MeetingDAO;
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
        meetingDAO.addAccessCard(meeting);
    }

    @Override
    @Transactional
    public List<Meeting> getAllMeetings() {
        return meetingDAO.getAllMeetings();
    }

    @Override
    @Transactional
    public void deleteTask(Integer meetingId) {
        meetingDAO.deleteTask(meetingId);
    }

    @Override
    @Transactional
    public Meeting getMeeting(int meetingId) {
        return meetingDAO.getMeeting(meetingId);
    }

    @Override
    @Transactional
    public Meeting updateMeeting(Meeting meeting) {
        return meetingDAO.updateMeeting(meeting);
    }
}

package sb.org.service;

import sb.org.model.Meeting;

import java.util.List;

public interface MeetingService {

    public void addMeeting(Meeting meeting);

    public List<Meeting> getAllMeetings();

    public void deleteTask(Integer meetingId);

    public Meeting getMeeting(int meetingId);

    public Meeting updateMeeting(Meeting meeting);

}

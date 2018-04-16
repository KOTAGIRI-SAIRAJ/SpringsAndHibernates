package sb.org.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import sb.org.dao.MeetingDAO;
import sb.org.model.Meeting;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class MeetingTestService {

    @Mock
    MeetingDAO meetingDAO;

    @InjectMocks
    MeetingServiceImpl meetingService;

    @Spy
    List<Meeting> meetingList = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        meetingList = getMeetingList();
    }

    @Test
    public void addMeeting() {
        doNothing().when(meetingDAO).addMeeting(any(Meeting.class));
        meetingService.addMeeting(any(Meeting.class));
        verify(meetingDAO,atLeastOnce()).addMeeting(any(Meeting.class));
    }

    @Test
    public void deleteMeeting() {
        doNothing().when(meetingDAO).deleteMeeting(anyInt());
        meetingService.deleteMeeting(anyInt());
        verify(meetingDAO,atLeastOnce()).deleteMeeting(anyInt());
    }

    @Test
    public void updateMeeting() {
        Meeting meeting = meetingList.get(0);
        when(meetingDAO.updateMeeting(anyObject())).thenReturn(meeting);
        meetingService.updateMeeting(anyObject());
        verify(meetingDAO,atLeastOnce()).updateMeeting(anyObject());
    }

    @Test
    public void getAllMeetings(){
        when(meetingDAO.getAllMeetings()).thenReturn(meetingList);
        Assert.assertEquals(meetingService.getAllMeetings(),meetingList);
    }

    @Test
    public void getMeeting() {
        Meeting meeting = meetingList.get(0);
        when(meetingDAO.getMeeting(anyInt())).thenReturn(meeting);
        meetingService.getMeeting(anyInt());
        verify(meetingDAO,atLeastOnce()).getMeeting(anyInt());
    }



    public List<Meeting> getMeetingList() {

        List<Meeting> meetingList = new ArrayList<>();

        Meeting meeting = new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
        meeting.setMeeting_title("SCRUM");

        Meeting meeting1 = new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
        meeting.setMeeting_title("SCRUM");

        meetingList.add(meeting);
        meetingList.add(meeting1);

        return meetingList;
    }
}

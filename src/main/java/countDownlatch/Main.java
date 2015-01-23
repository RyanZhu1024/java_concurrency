package countDownlatch;

/**
 * Created by shuxuan on 1/23/15.
 */
public class Main {
    public static void main(String[] args) {
        VideoConference conference=new VideoConference(10);
        Thread conferenceThread=new Thread(conference);
        conferenceThread.start();
        for(int i=0;i<10;i++){
            Thread participantTh=new Thread(new Participant(conference,"Participant"+i));
            participantTh.start();
        }
    }
}

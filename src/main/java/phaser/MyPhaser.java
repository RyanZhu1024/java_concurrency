package phaser;

import java.util.concurrent.Phaser;

/**
 * Created by shuxuan on 1/26/15.
 */
public class MyPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                return studentArrived();
            case 1:
                return finishFirstExam();
            case 2:
                return finishSecondExam();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean finishExam() {
        System.out.println("All students finished all exams");
        return true;
    }

    private boolean finishSecondExam() {
        System.out.println("All students finished second exam");
        return false;
    }


    private boolean finishFirstExam() {
        System.out.println("All students finished first exam");
        return false;
    }

    private boolean studentArrived() {
        System.out.println("Student: "+this.getRegisteredParties()+" arrived");
        return false;
    }
}

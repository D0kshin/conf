package ru.ac.uniyar.testingcourse.conference;

import java.util.HashMap;

public class Conference implements IConference {

    public void register(Participant participant, Integer feeAmount) {
        Fee fee = new Fee(feeAmount);
        HashMap<Participant, Fee> data = new HashMap<>();
        data.put(participant, fee);
    }

    @Override
    public void markFeePaid(Participant participant) {

    }

    @Override
    public Integer budget() {
        return null;
    }

    @Override
    public void addToBlacklist(String email) {

    }

    @Override
    public void removeFromBlacklist(String email) {

    }
}

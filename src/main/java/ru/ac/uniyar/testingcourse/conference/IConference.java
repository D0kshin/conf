package ru.ac.uniyar.testingcourse.conference;

/**
 * Class representing a scientific conference.
 * Participants can register (fee amount must be specified) if they're not in the blacklist and wasn't already registred.
 * Participants are identified by their email addresses.
 */
public interface IConference {
    class BlacklistedException extends RuntimeException {

        public BlacklistedException() {
            super("User in blacklist!");
        }
    }

    class AlreadyRegistredException extends RuntimeException {

        public AlreadyRegistredException() {
            super("User in blacklist!");
        }
    }

    /**
     * Register participant to the conference.
     * Participant's fee is not paid by default, it should be directly marked as {@link #markFeePaid(Participant)} later
     * @throws BlacklistedException if the participant's email is in the blacklist
     * @throws AlreadyRegistredException if the participant was already registred
     */
    void register(Participant participant, Integer feeAmount);

    /**
     * Mark that participant paid registration fee
     */
    void markFeePaid(Participant participant);

    /**
     * @return sum of registration fees paid by participants
     */
    Integer budget();

    /**
     * Adds specified email to the blacklist
     * @param email to be added
     */
    void addToBlacklist(String email);

    /**
     * Removes specified email from the blacklist
     * @param email to be removed
     */
    void removeFromBlacklist(String email);
}

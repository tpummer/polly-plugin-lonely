package at.nullpointer.polly.plugin.lonely.core;

import java.util.Date;

import de.skuzzle.polly.sdk.eventlistener.IrcUser;
import de.skuzzle.polly.sdk.eventlistener.MessageEvent;

/**
 * Value Object representing the State of an Channel for the Lonely Modus
 * 
 * TODO: set Channelname within this Status too
 * 
 * @author Thomas Pummer
 * 
 */
public class Status {

    /**
     * Time the last {@link MessageEvent} was sent
     */
    private Date time;

    /**
     * {@link IrcUser} who sent the last {@link MessageEvent}
     */
    private IrcUser user;

    /**
     * Flag if the {@link MessageEvent} was an answer to a previous {@link MessageEvent}
     */
    private Boolean wasAnswer;

    /**
     * Flag if the LonelyModus is enabeld for this channel
     */
    private Boolean enabled = false;


    /**
     * @return the time
     */
    public Date getTime() {

        return time;
    }


    /**
     * @param time the time to set
     */
    public void setTime( final Date time ) {

        this.time = time;
    }


    /**
     * @return the user
     */
    public IrcUser getUser() {

        return user;
    }


    /**
     * @param user the user to set
     */
    public void setUser( final IrcUser user ) {

        this.user = user;
    }


    /**
     * @return the wasAnswer
     */
    public Boolean isWasAnswer() {

        return wasAnswer;
    }


    /**
     * @param wasAnswer the wasAnswer to set
     */
    public void setWasAnswer( final Boolean wasAnswer ) {

        this.wasAnswer = wasAnswer;
    }


    /**
     * @return the enabled
     */
    public Boolean isEnabled() {

        return enabled;
    }


    /**
     * @param enabled the enabled to set
     */
    public void setEnabled( final Boolean enabled ) {

        this.enabled = enabled;
    }


    /**
     * Sets the new State
     * 
     * @param newWasAnwer
     * @param newTime
     * @param newUser
     */
    public void change( Boolean newWasAnwer, Date newTime, IrcUser newUser ) {

        this.wasAnswer = newWasAnwer;
        this.time = newTime;
        this.user = newUser;
    }

}

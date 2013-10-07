package at.nullpointer.polly.plugin.lonely.event;

import java.util.Date;

import at.nullpointer.polly.plugin.lonely.core.Status;
import de.skuzzle.polly.sdk.eventlistener.IrcUser;
import de.skuzzle.polly.sdk.eventlistener.MessageEvent;

/**
 * Holds some Methods to check values of {@link Status}
 * 
 * @author Thomas Pummer
 * 
 */
public class StatusComperator {

    /**
     * Checks if 2 users are the same - nullsave
     * 
     * @param user
     * @param user2
     * @return Boolean true/false
     */
    public Boolean isSameUser( IrcUser user, IrcUser user2 ) {

        if ( user == null ) {
            if ( user2 == null ) {
                return true;
            } else {
                return false;
            }
        }

        return user.equals( user2 );

    }


    /**
     * Checks if the oldTime is not older than the allowed Milliseconds
     * 
     * @param oldTime
     * @param newTime
     * @param allowedMilliseconds
     * @return Boolean true/false
     */
    public Boolean letzteAntwortInnerhalb( Date oldTime, Date newTime, Long allowedMilliseconds ) {

        if ( oldTime != null ) {
            return newTime.getTime() <= oldTime.getTime() + allowedMilliseconds;
        }
        return true;

    }


    /**
     * Checks allready had an {@link MessageEvent}
     * 
     * @param status
     * @return Boolean true/false
     */
    public Boolean istErsterEintrag( Status status ) {

        return status.getUser() == null;

    }

}

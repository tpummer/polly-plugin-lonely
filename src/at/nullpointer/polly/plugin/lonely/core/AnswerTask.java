package at.nullpointer.polly.plugin.lonely.core;

import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import de.skuzzle.polly.sdk.IrcManager;

/**
 * Task to check periodicly if an answer for a lonely ircuser is needed
 * 
 * TODO: Check if enough time has passed since last irc msg
 * 
 * @author Thomas Pummer
 * 
 */
public class AnswerTask
        extends TimerTask {

    private transient final IrcManager ircManager;
    private transient final Map<String, Status> statusMap;
    private transient final MessageStore messageStore;


    /**
     * Setting up the LonelyTask
     * 
     * @param ircManager to send answers
     * @param statusMap to check the channels if an answer is needed
     */
    public AnswerTask( final IrcManager ircManager, final Map<String, Status> statusMap ) {

        super();
        this.ircManager = ircManager;
        this.statusMap = statusMap;
        this.messageStore = new MessageStore();
    }


    /**
     * Checks each status in the statusMap if the lonely modus is enabeld and if it should answer and performs that
     * answer
     */
    @Override
    public void run() {

        Set<String> keySet = statusMap.keySet();

        for ( String key : keySet ) {
            Status status = statusMap.get( key );
            if ( status.isEnabled() && !status.isWasAnswer() ) {
                ircManager.sendMessage( key, this.messageStore.getAnswer(), this );
                status.setWasAnswer( Boolean.TRUE );
            }
        }

    }

}

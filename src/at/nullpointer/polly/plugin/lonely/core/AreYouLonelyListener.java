package at.nullpointer.polly.plugin.lonely.core;

import at.nullpointer.polly.plugin.lonely.event.EventManager;
import de.skuzzle.polly.sdk.eventlistener.MessageEvent;
import de.skuzzle.polly.sdk.eventlistener.MessageListener;

/**
 * Listener for Messages to find lonely people
 * 
 * @author Thomas Pummer
 * 
 */
public class AreYouLonelyListener
        implements MessageListener {

    private transient EventManager eventManager;


    /**
     * @param eventManager
     */
    public AreYouLonelyListener( final EventManager eventManager ) {

        this.eventManager = eventManager;
    }


    /**
     * public messages should be checked on lonelyness
     */
    @Override
    public void publicMessage( final MessageEvent event ) {

        this.eventManager.processMessageEvent( event );

    }


    /**
     * on a private Message nothing happens
     */
    @Override
    public void privateMessage( final MessageEvent event ) {

    }


    /**
     * on an action Message nothing happens
     */
    @Override
    public void actionMessage( final MessageEvent event ) {

    }


    /**
     * on a notice Message nothing happens
     */
    @Override
    public void noticeMessage( final MessageEvent event ) {

    }

}

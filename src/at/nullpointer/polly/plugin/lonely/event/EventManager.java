package at.nullpointer.polly.plugin.lonely.event;

import java.util.Map;

import at.nullpointer.polly.plugin.lonely.commands.TimeSetting;
import at.nullpointer.polly.plugin.lonely.core.Status;
import de.skuzzle.polly.sdk.eventlistener.MessageEvent;

// import org.apache.log4j.Logger;

/**
 * If an {@link MessageEvent} occures it's on the Event Manager to decide what to do
 * 
 * TODO: Logging
 * 
 * @author Thomas Pummer
 * 
 */
public class EventManager {

    /**
     * Map to store the info about the lonelyness
     */
    private final transient Map<String, Status> statusMap;

    /**
     * {@link StatusComperator}
     */
    private final transient StatusComperator statusComperator;


    /**
     * @param statusMap where the info about the lonelyness will be stored
     */
    public EventManager( final Map<String, Status> statusMap ) {

        this.statusMap = statusMap;
        this.statusComperator = new StatusComperator();

    }


    /**
     * Processes an {@link MessageEvent}and sets the State for the channel in which the {@link MessageEvent} occured
     * 
     * @param event {@link MessageEvent}
     */
    public void processMessageEvent( final MessageEvent event ) {

        Status status = statusMap.get( event.getChannel() );

        if ( status != null && status.isEnabled() ) {
            Boolean ersterEintrag = this.statusComperator.istErsterEintrag( status );
            Boolean konversation = this.statusComperator.letzteAntwortInnerhalb( status.getTime(), event.getDate(),
                    TimeSetting.KONVERSATION_PAUSE );

            // ersterEintrag oder es keine konversation ist bleibt es dabei
            Boolean isAnswer = Boolean.FALSE;

            if ( !ersterEintrag && konversation ) {
                isAnswer = Boolean.TRUE;
            }
            status.change( isAnswer, event.getDate(), event.getUser() );
        }

    }


    /**
     * Sets enabled for a given channel
     * 
     * @param channel
     * @param enabled
     */
    public void setEnabled( final String channel, final boolean enabled ) {

        Status status = statusMap.get( channel );
        if ( status == null ) {
            status = new Status();
            this.statusMap.put( channel, status );
        }

        status.setEnabled( enabled );

    }


    /**
     * Checks if the LonelyModul is enabled for a given channel
     * 
     * @param channel
     * @return Booelan True/False
     */
    public boolean isEnabled( final String channel ) {

        Status status = statusMap.get( channel );

        Boolean result = Boolean.FALSE;

        if ( status != null ) {
            result = status.isEnabled();
        }

        return result;
    }
}
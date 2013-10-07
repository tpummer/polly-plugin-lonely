package at.nullpointer.polly.plugin.lonely.commands;

import de.skuzzle.polly.sdk.eventlistener.MessageEvent;

/**
 * Configuration of the Time Intervalls
 * 
 * TODO: move to propertie file
 * 
 * @author Thomas Pummer
 * 
 */
public class TimeSetting {

    /**
     * Time between the single Task executions
     */
    public static final Long TASK_INTERVALL = 5L * 60 * 1000;

    /**
     * Max time between 2 {@link MessageEvent} from the same konversation
     */
    public static final Long KONVERSATION_PAUSE = 5L * 60 * 1000;

}

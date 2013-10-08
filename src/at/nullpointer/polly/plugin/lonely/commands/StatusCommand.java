package at.nullpointer.polly.plugin.lonely.commands;

import at.nullpointer.polly.plugin.lonely.event.EventManager;
import de.skuzzle.polly.sdk.Command;
import de.skuzzle.polly.sdk.MyPolly;
import de.skuzzle.polly.sdk.Signature;
import de.skuzzle.polly.sdk.User;
import de.skuzzle.polly.sdk.exceptions.CommandException;
import de.skuzzle.polly.sdk.exceptions.DuplicatedSignatureException;
import de.skuzzle.polly.sdk.exceptions.InsufficientRightsException;

/**
 * Command to turn the lonely modus on and of in the channel the command sent
 * 
 * TODO: extract text sent to user into properties files
 * 
 * @author Thomas Pummer
 * 
 */
public class StatusCommand
        extends Command {

    private transient EventManager eventManager;


    /**
     * @param polly
     * @param eventManager
     * @throws DuplicatedSignatureException
     */
    public StatusCommand( final MyPolly polly, final EventManager eventManager )
            throws DuplicatedSignatureException {

        super( polly, "islonely" );
        this.eventManager = eventManager;

        this.createSignature( "Gibt an ob der Lonely Modus in dem Channel aktiviert ist",
                Permission.TOGGLE_LONELY.getValue() );
    }


    /**
     * @see Command#executeOnBoth(User,String,Signature))
     */
    @Override
    protected boolean executeOnBoth( final User executer, final String channel, final Signature signature )
            throws CommandException, InsufficientRightsException {

        if ( this.match( signature, 0 ) ) {
            String state = this.eventManager.isEnabled( channel ) ? "an" : "aus";

            this.reply( channel, "Lonely Modus in Channel " + channel + " ist derzeit " + state );
        }

        return false;
    }
}

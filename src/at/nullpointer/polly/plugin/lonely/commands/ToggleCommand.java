package at.nullpointer.polly.plugin.lonely.commands;

import at.nullpointer.polly.plugin.lonely.event.EventManager;
import de.skuzzle.polly.sdk.Command;
import de.skuzzle.polly.sdk.MyPolly;
import de.skuzzle.polly.sdk.Parameter;
import de.skuzzle.polly.sdk.Signature;
import de.skuzzle.polly.sdk.Types;
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
public class ToggleCommand
        extends Command {

    private transient EventManager eventManager;


    /**
     * @param polly
     * @param eventManager
     * @throws DuplicatedSignatureException
     */
    public ToggleCommand( final MyPolly polly, final EventManager eventManager )
            throws DuplicatedSignatureException {

        super( polly, "lonely" );
        this.eventManager = eventManager;

        this.createSignature( "Schaltet den Lonely Modus ein/aus", Permission.TOGGLE_LONELY.getValue(), new Parameter(
                "Status", Types.BOOLEAN ) );
    }


    /**
     * @see Command#executeOnBoth(User,String,Signature))
     */
    @Override
    protected boolean executeOnBoth( final User executer, final String channel, final Signature signature )
            throws CommandException, InsufficientRightsException {

        if ( this.match( signature, 0 ) ) {
            this.eventManager.setEnabled( channel, signature.getBooleanValue( 0 ) );
            String state = this.eventManager.isEnabled( channel ) ? "an" : "aus";

            this.reply( channel, "Lonely Modus in Channel " + channel + ": " + state );
        }

        return false;
    }
}

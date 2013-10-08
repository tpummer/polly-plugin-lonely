package at.nullpointer.polly.plugin.lonely;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import at.nullpointer.polly.plugin.lonely.commands.Permission;
import at.nullpointer.polly.plugin.lonely.commands.StatusCommand;
import at.nullpointer.polly.plugin.lonely.commands.TimeSetting;
import at.nullpointer.polly.plugin.lonely.commands.ToggleCommand;
import at.nullpointer.polly.plugin.lonely.core.AnswerTask;
import at.nullpointer.polly.plugin.lonely.core.AreYouLonelyListener;
import at.nullpointer.polly.plugin.lonely.core.Status;
import at.nullpointer.polly.plugin.lonely.event.EventManager;
import de.skuzzle.polly.sdk.AbstractDisposable;
import de.skuzzle.polly.sdk.IrcManager;
import de.skuzzle.polly.sdk.MyPolly;
import de.skuzzle.polly.sdk.PollyPlugin;
import de.skuzzle.polly.sdk.eventlistener.MessageListener;
import de.skuzzle.polly.sdk.exceptions.DatabaseException;
import de.skuzzle.polly.sdk.exceptions.DisposingException;
import de.skuzzle.polly.sdk.exceptions.DuplicatedSignatureException;
import de.skuzzle.polly.sdk.exceptions.IncompatiblePluginException;
import de.skuzzle.polly.sdk.exceptions.RoleException;
import de.skuzzle.polly.sdk.roles.RoleManager;

/**
 * Startup Setup of the Plugin
 * 
 * @author Thomas Pummer
 * 
 */
public class MyPlugin
        extends PollyPlugin {

    private final transient EventManager eventManager;
    private transient MessageListener messageListener;


    /**
     * @see PollyPlugin#PollyPlugin(MyPolly)
     * 
     * @param myPolly
     * @throws IncompatiblePluginException
     * @throws DuplicatedSignatureException
     */
    public MyPlugin( final MyPolly myPolly )
            throws IncompatiblePluginException, DuplicatedSignatureException {

        super( myPolly );

        Map<String, Status> statusMap = new HashMap<>();
        this.eventManager = new EventManager( statusMap );

        addListener( myPolly );

        registerCommands( myPolly );

        startMonitor( myPolly, statusMap );

    }


    private void registerCommands( final MyPolly myPolly )
            throws DuplicatedSignatureException {

        this.addCommand( new ToggleCommand( myPolly, this.eventManager ) );
        this.addCommand( new StatusCommand( myPolly, eventManager ) );
    }


    private void startMonitor( final MyPolly myPolly, final Map<String, Status> statusMap ) {

        TimerTask action = new AnswerTask( myPolly.irc(), statusMap );
        Timer caretaker = new Timer();
        caretaker.schedule( action, TimeSetting.TASK_INTERVALL, TimeSetting.TASK_INTERVALL );
    }


    private void addListener( final MyPolly myPolly ) {

        this.messageListener = new AreYouLonelyListener( this.eventManager );
        IrcManager irc = myPolly.irc();
        irc.addMessageListener( this.messageListener );
    }


    /**
     * @see PollyPlugin#getContainedPermissions()
     */
    @Override
    public Set<String> getContainedPermissions() {

        final TreeSet<String> result = new TreeSet<String>();
        result.add( Permission.TOGGLE_LONELY.getValue() );
        result.addAll( super.getContainedPermissions() );
        return result;
    }


    /**
     * @see PollyPlugin#assignPermissions(RoleManager)
     */
    @Override
    public void assignPermissions( final RoleManager roleManager )
            throws RoleException, DatabaseException {

        roleManager.assignPermission( RoleManager.ADMIN_ROLE, Permission.TOGGLE_LONELY.getValue() );
    }


    /**
     * @see AbstractDisposable#actualDispose()
     */
    @Override
    protected void actualDispose()
            throws DisposingException {

        super.actualDispose();

        this.getMyPolly().irc().removeMessageListener( this.messageListener );
    }
}

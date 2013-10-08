package at.nullpointer.polly.plugin.lonely.commands;

/**
 * Permissions needed for the Lonely Plugin
 * 
 * @author Thomas Pummer
 * 
 */
public enum Permission {

    /**
     * Users with this permission are able to toggle the lonelyplugin on and off
     */
    TOGGLE_LONELY( "polly.permission.TOGGLE_LONELY" ),

    STATUS_LONELY( "polly.permission.STATUS_LONELY" );

    /**
     * String representation of the permission
     */
    private String value;


    /**
     * @return the value
     */
    public String getValue() {

        return value;
    }


    private Permission( final String value ) {

        this.value = value;
    }

}

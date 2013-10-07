package at.nullpointer.polly.plugin.lonely.core;

/**
 * Class containing and retrieving the possible answers the lonely plugin can give
 * 
 * TODO: Read answers from a properties file
 * 
 * @author Thomas Pummer
 * 
 */
public class MessageStore {

    /**
     * List with all possibly answers
     */
    private final static String[] ANSWERS = { ":-)", "^^", ":)", "...", "?" };


    /**
     * Retrieves a random answer
     * 
     * @return String answer
     */
    public String getAnswer() {

        int answerIndex = (int)( Math.random() * ANSWERS.length );
        if ( answerIndex == ANSWERS.length ) {
            answerIndex = 0;
        }
        return ANSWERS[ answerIndex ];
    }

}

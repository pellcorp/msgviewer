/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.MSGViewer;

import net.sourceforge.MSGViewer.CLIHelp.CLIHelp;
import net.sourceforge.MSGViewer.CLIHelp.CLIOption;
import net.sourceforge.MSGViewer.CLIHelp.CLIGroupHeader;
import at.redeye.FrameWork.base.BaseModuleLauncher;

/**
 *
 * @author martin
 */
public class CLIHelpMSGViewer extends CLIHelp
{        
    public static final String CLI_HIDEMENUBAR = "-hidemenubar";
    public static final String CLI_HELP = "-h";
    public static final String CLI_VERSION = "-v";
    public static final String CLI_CONVERT_TEMP = "-t";
    public static final String CLI_CONVERT_OPEN = "-o";
    
    public CLIHelpMSGViewer( BaseModuleLauncher module_launcher )
    {        
        super( module_launcher );
        
        add( new CLIGroupHeader("COMMAND LINE OPTIONS"));
        
        add( new  CLIOption(CLI_VERSION, "Print version and licence informations") );
        add( new  CLIOption(CLI_HELP, "Print this screen") );
        
        add( new CLIOption( Msg2MBox.CLI_PARAMETER, "Converts a msg file to a mbox file.") );
        add( new CLIOption( Msg2Eml.CLI_PARAMETER, "Converts a msg file to an eml file.") );
        add( new CLIOption( MBox2Msg.CLI_PARAMETER, "Converts a mbox file to a msg file.",
                                                    "This feature is in pre alpla state and may never will work." ) );                
        add( new CLIOption( CLI_CONVERT_TEMP, "Converts the message to the users temporary directory.") );
        
        add(new CLIOption(
                CLI_CONVERT_OPEN,
                "Opens the converted message with the associated standard application." ) );               

        add( new CLIGroupHeader("GUI OPTIONS"));
        
        add( new CLIOption( CLI_HIDEMENUBAR, "Hide menubar.",
                                             "In this mode MSGViewer can only show messages. There is no file save as," + 
                                             " or somthing like this avaliable. This mode is use by internet kiosks." ));
    }
    
}

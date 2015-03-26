/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.MSGViewer;

import at.redeye.FrameWork.base.BaseModuleLauncher;
import at.redeye.FrameWork.base.FrameWorkConfigDefinitions;
import at.redeye.FrameWork.base.LocalRoot;
import at.redeye.FrameWork.base.Setup;
import at.redeye.FrameWork.widgets.StartupWindow;
import org.apache.log4j.Level;

/**
 *
 * @author martin
 */
public class ModuleLauncher extends BaseModuleLauncher
{           
    MainDialog mainwin;

    public ModuleLauncher( String[] args )
    {
        super( args );

        BaseConfigureLogging(Level.ERROR);
        
        root = new LocalRoot("MSGViewer", "MSGViewer", false, false);

        root.setBaseLanguage("en");
        root.setDefaultLanguage("en");

        root.setLanguageTranslationResourcePath("/net/sourceforge/MSGViewer/resources/translations");
    }

    public void invoke()
    {                        
        if( getStartupFlag(CLIHelpMSGViewer.CLI_HELP))
        {
            CLIHelpMSGViewer help = new CLIHelpMSGViewer(this);
            help.printHelpScreen();
            return;
        }
        
        if( getStartupFlag(CLIHelpMSGViewer.CLI_VERSION))
        {
            CLIHelpMSGViewer help = new CLIHelpMSGViewer(this);
            help.printVersion();
            return;
        }        
        
       CLIFileConverter converter = null;
       if( getStartupFlag(Msg2MBox.CLI_PARAMETER))
       {
           converter = new Msg2MBox(this);
       } else if( getStartupFlag(MBox2Msg.CLI_PARAMETER)) {
           converter = new MBox2Msg(this);
       } else if( getStartupFlag(Msg2Eml.CLI_PARAMETER)) {
           converter = new Msg2Eml(this);
       }              

       if (converter != null) {
           converter.work();
       } else {
           invokeGui();
       }
    }

    @Override
    public String getVersion() {
        return Version.getVersion();
    }
    
    public void invokeGui()
    {
        if (splashEnabled()) {
            splash = new StartupWindow(
                    "/at/redeye/FrameWork/base/resources/pictures/redeye.png");
        }


        AppConfigDefinitions.registerDefinitions();
	FrameWorkConfigDefinitions.registerDefinitions();

        if( Setup.is_win_system() )
        {
            root.registerPlugin(new at.redeye.Plugins.ShellExec.Plugin());
        }

        root.registerPlugin(new net.sourceforge.MSGViewer.Plugins.msgparser.Plugin());
        root.registerPlugin(new net.sourceforge.MSGViewer.Plugins.tnef.Plugin());
        root.registerPlugin(new net.sourceforge.MSGViewer.Plugins.poi.Plugin());
        root.registerPlugin(new net.sourceforge.MSGViewer.Plugins.javamail.Plugin());
        root.registerPlugin(new at.redeye.Plugins.CommonsLang.Plugin());
        root.registerPlugin(new at.redeye.Plugins.JerichoHtml.Plugin());

        setLookAndFeel(root);

        configureLogging();

        for( String arg : args )
        {
            if( arg.toLowerCase().endsWith(".msg") || arg.toLowerCase().endsWith(".mbox"))
            {
                MainDialog win = null;
                
                if( getStartupFlag("-mainwin") ) {                
                    win = new MainWin(root, arg);
                } else {
                    win = new SingleWin(root, arg);
                }
                

                if( mainwin == null )
                {
                    mainwin = win;
                }
                else
                {
                    if (getStartupFlag("-hidemenubar")) {
                        win.hideMenuBar();
                    }                       
                    win.setVisible(true);          
                }                                
            }
        }
        
        if( mainwin == null ) {
            if (getStartupFlag("-mainwin")) {
                mainwin = new MainWin(root, null);
            } else {
                mainwin = new SingleWin(root, null);
            }             
        } 

        if( getStartupFlag(CLIHelpMSGViewer.CLI_HIDEMENUBAR) ) {
            mainwin.hideMenuBar();  
        }        
        
        
        closeSplash();        
        mainwin.setVisible(true);
        
    }

    private boolean getStartupFlag(String string)
    {
       for( String arg  : args )
       {
           if( arg.equalsIgnoreCase(string) )
               return true;
       }
       
       return false;
    }
    
    public static void main(String[] args) {

        new ModuleLauncher(args).invoke();

    }   
    
}

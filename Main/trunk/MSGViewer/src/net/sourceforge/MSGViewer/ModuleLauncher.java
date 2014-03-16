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
    private static final String CONFIG_LAST_UPDATE = "lastupdate";
    
    MainWin mainwin;

    public ModuleLauncher( String[] args )
    {
        super( args );

        BaseConfigureLogging(Level.ERROR);
        
        root = new LocalRoot("MSGViewer", "MSGViewer", false, false);

        root.setBaseLanguage("de");
        root.setDefaultLanguage("en");

        // root.setWebStartUlr(getWebStartUrl("http://redeye.hoffer.cx/MSGViewer/launch.jnlp"));
        root.setLanguageTranslationResourcePath("/net/sourceforge/MSGViewer/resources/translations");
    }

    public void invoke()
    {
       if( getStartupFlag("-msg2mbox") )
       {
           invokeMSG2MBox();
       } else if( getStartupFlag("-mbox2msg") ) {
           invokeMBox2MSG();
       } else {
           invokeGui();
       }
    }

    @Override
    public String getVersion() {
        return Version.getVersion();
    }
    
    public void invokeMSG2MBox()
    {
        Msg2MBox converter = new Msg2MBox(this);
        
        converter.work();
    }
    
    
    public void invokeMBox2MSG()
    {
        MBox2Msg converter = new MBox2Msg(this);
        
        converter.work();
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
                MainWin win = new MainWin(root, arg);

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
        
        if( mainwin == null )
            mainwin = new MainWin(root,null);        

        if( getStartupFlag("-hidemenubar") ) {
            mainwin.hideMenuBar();  
        }        
        
        
        closeSplash();        
        mainwin.setVisible(true);

        /*
        if( !getStartupFlag("-noautoupdate") ) {
            
            long lastupdate = Long.valueOf(root.getSetup().getLocalConfig(CONFIG_LAST_UPDATE,"0"));
            
            if (lastupdate < System.currentTimeMillis()) {
                
                logger.info("Last update was at: " + lastupdate + " now " + System.currentTimeMillis());
                
                root.getSetup().setLocalConfig(CONFIG_LAST_UPDATE, 
                        String.valueOf(System.currentTimeMillis() + 1000*60*60*24*7*4));
                root.getSetup().saveConfig();

                updateJnlp2();
            }
        }
        */

        /*
        if (Setup.is_linux_system()) {
            new Thread() {

                @Override
                public void run() {
                    extractScripts();
                }
            }.start();
        }
        */
        
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
    
    /*
    void extractScripts() {

        String scripts[] = {"msg2mbox", "msgviewer", "mbox2msg"};

        String exec_path = System.getProperty("user.home");

        if (exec_path == null | exec_path.isEmpty()) {
            return;
        }
        
        File user_home_path = new File(exec_path);
        
        if( !user_home_path.exists() )
            return;              

        exec_path += "/bin";
        

        user_home_path = new File(exec_path);
        if( !user_home_path.exists() )
            return;    

        for (String script : scripts) {
            File script_file = new File(exec_path + "/" + script);

            if (!script_file.exists()) {
                InputStream stream = getClass().getResourceAsStream("/at/redeye/MSGViewer/resources/scripts/" + script);

                if (stream == null) {
                    logger.error("Cannot load RedeyeStarter ");
                    continue;
                }

                try {

                    OutputStream out = new FileOutputStream(script_file);

                    BufferedInputStream bis = new BufferedInputStream(stream);

                    byte[] buf = new byte[1024 * 4];
                    int len;

                    while ((len = bis.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    out.close();
                    bis.close();
                    stream.close();
                    
                    script_file.setExecutable(true);

                } catch (Exception ex) {

                    logger.error(ex);
                }
            }
        }
    }
    */
    
}

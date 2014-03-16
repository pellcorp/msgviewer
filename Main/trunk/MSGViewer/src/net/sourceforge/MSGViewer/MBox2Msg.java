/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.MSGViewer;

import at.redeye.FrameWork.base.Root;
import net.sourceforge.MSGViewer.factory.MessageParserFactory;
import com.auxilii.msgparser.Message;
import java.io.File;
import org.apache.log4j.Level;

/**
 *
 * @author martin
 */
public class MBox2Msg 
{
    ModuleLauncher module_launcher;
    Root root;
    
    public MBox2Msg( ModuleLauncher module_launcher )
    {
        this.module_launcher = module_launcher;
        root = module_launcher.root;
        module_launcher.BaseConfigureLogging(Level.ERROR);                
    }
    
    public void work()
    {
        boolean converted  = false;
        MessageParserFactory factory = new MessageParserFactory();
        
        for( String arg : module_launcher.args )
        {
            if( arg.toLowerCase().endsWith(".mbox") )
            {
                converted = true;
                try {
                    Message msg = factory.parseMessage(new File( arg ));
                    
                    int idx = arg.lastIndexOf(".");
                    
                    File msg_file = new File( arg.substring(0,idx) + ".msg");
                    
                    factory.saveMessage(msg, msg_file);
                } catch( Exception ex ) {
                    System.err.print(ex);
                    ex.printStackTrace();
                }
            }
        }
        
        if( !converted )
            usage();
    }
    
    public void usage()
    {
        System.out.println(root.MlM("usage: mbox2msg FILE FILE ...."));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.MSGViewer.CLIHelp;

import at.redeye.FrameWork.base.BaseModuleLauncher;
import at.redeye.FrameWork.base.Root;
import java.util.ArrayList;

/**
 *
 * @author martin
 */
public class CLIHelp 
{
    private static final int LINE_LEN=80;
    private final ArrayList<CLIOption> options = new ArrayList<>();    
    private BaseModuleLauncher module_launcher;
    
    public CLIHelp(BaseModuleLauncher module_launcher)
    {
        this.module_launcher = module_launcher;
    }
    
    public void add( CLIOption option ) {
        options.add(option);
    }
    
    public void printHelpScreen()
    {
        int max_option_len = getMaxOptionLen();
        
        StringBuilder sb = new StringBuilder();
        
        for( CLIOption o : options )
        {
            o.buildShortHelpText(sb, max_option_len, LINE_LEN);
            sb.append("\n");
            o.buildLongHelpText(sb, max_option_len + 1, LINE_LEN);
        }
        
        System.out.println( module_launcher.root.getAppName() + " - " + module_launcher.root.getAppTitle() );
        System.out.println( "Version: " + module_launcher.getVersion() + "\n");
        
        System.out.println(sb);
    }
    
    public void printVersion()
    {
        System.out.println( module_launcher.root.getAppName() + " - " + module_launcher.root.getAppTitle() );
        System.out.println( "Version: " + module_launcher.getVersion() + "\n");        
    }
    
    private int getMaxOptionLen()
    {
        int val = 0;
        
        for( CLIOption o : options )
        {
            val = Math.max(val, o.getName().length() );
        }
        
        return val;
    }
    
    private int getMaxShortDescrLen()
    {
        int val = 0;
        
        for( CLIOption o : options )
        {
            Math.max(val, o.getShortDescription().length() );
        }
        
        return val;
    }    
}

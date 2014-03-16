/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.MSGViewer.MSGNavigator;

import at.redeye.FrameWork.base.AutoMBox;
import at.redeye.FrameWork.utilities.StringUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author moberza
 */
public class NavActionPopup extends JPopupMenu
{
    
    private MSGNavigator mainwin;
    

    public NavActionPopup(final MSGNavigator mainwin)
    {
        this.mainwin = mainwin;        
        
        {
            JMenu menu_settings = new JMenu(mainwin.MlM("Einstellungen") );
            
            add( menu_settings );
            
            // SETTINGS
            {
                final JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(mainwin.MlM("Größen anzeigen"));
                
                if( StringUtils.isYes(mainwin.getRoot().getSetup().getLocalConfig(MSGNavigator.SETTING_SHOW_SIZE,"true")) )
                {
                    menuItem.setState(true);
                } else {
                    menuItem.setState(false);
                }
                     
                
                
                menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        mainwin.getRoot().getSetup().setLocalConfig(MSGNavigator.SETTING_SHOW_SIZE,String.valueOf(menuItem.getState()));
                        
                        mainwin.reload();
                    }
                });
                
                menu_settings.add(menuItem);
            }
            
            
            
            // AUTOSAVE
            {
                final JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(mainwin.MlM("automatisch Speichern"));
                
                if( StringUtils.isYes(mainwin.getRoot().getSetup().getLocalConfig(MSGNavigator.SETTING_AUTOSAVE,"false")) )
                {
                    menuItem.setState(true);
                } else {
                    menuItem.setState(false);
                }
                     
                
                
                menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        mainwin.getRoot().getSetup().setLocalConfig(MSGNavigator.SETTING_AUTOSAVE,String.valueOf(menuItem.getState()));
                                           
                    }
                });
                
                menu_settings.add(menuItem);
            }            
        }
        
        {
            JMenuItem menuItem = new JMenuItem(mainwin.MlM("Speichern"));

            menuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new AutoMBox(NavActionPopup.class.getName()) {

                        @Override
                        public void do_stuff() throws Exception {

                            mainwin.save();
                        }
                    };
                }
            });

            add(menuItem);
        }

        {
            JMenuItem menuItem = new JMenuItem(mainwin.MlM("Element Löschen"));

            menuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new AutoMBox(NavActionPopup.class.getName()) {

                        @Override
                        public void do_stuff() throws Exception {

                            mainwin.deleteSelectedElement();
                        }
                    };
                }
            });

            add(menuItem);
        }
        
        {
            JMenuItem menuItem = new JMenuItem(mainwin.MlM("Element inspizieren"));

            menuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new AutoMBox(NavActionPopup.class.getName()) {

                        @Override
                        public void do_stuff() throws Exception {

                            mainwin.showSelectedElement();
                        }
                    };
                }
            });

            add(menuItem);
        }        
        
       {
            JMenuItem menuItem = new JMenuItem(mainwin.MlM("Element editieren"));

            menuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new AutoMBox(NavActionPopup.class.getName()) {

                        @Override
                        public void do_stuff() throws Exception {

                            mainwin.editSelectedElement();
                        }
                    };
                }
            });

            add(menuItem);
        }           
    }
}

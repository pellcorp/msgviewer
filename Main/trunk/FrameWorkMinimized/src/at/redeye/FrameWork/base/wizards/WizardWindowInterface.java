/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.redeye.FrameWork.base.wizards;

/**
 *
 * @author Mario
 */
public interface WizardWindowInterface {

    public boolean allowJumpNextWindow();
    public boolean allowJumpPrevWindow();
    public boolean allowJumpToEnd ();
    public boolean allowCloseBeforeEnd ();
    public void onClose ( WizardAction current_step );
    public void onInit ();
    public WizardAction getRecentAction ();
}

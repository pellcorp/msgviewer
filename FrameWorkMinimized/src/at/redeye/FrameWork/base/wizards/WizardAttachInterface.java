package at.redeye.FrameWork.base.wizards;

import at.redeye.FrameWork.base.wizards.impl.WizardListener;
import at.redeye.FrameWork.base.wizards.impl.WizardListener.WizardStatus;

/**
 * @author Mario Mattl
 * 
 * This interface makes the Wizard Framework observable,<br> 
 * so that it's possible to register at it.
 */

public interface WizardAttachInterface {
	
	public void addWizardListener (WizardListener listener);
	public void removeWizardListener (WizardListener listener);
	public void updateWizardListeners(WizardStatus currentWizardStatus);

}

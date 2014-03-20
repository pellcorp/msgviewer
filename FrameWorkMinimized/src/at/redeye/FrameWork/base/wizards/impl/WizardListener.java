package at.redeye.FrameWork.base.wizards.impl;

public interface WizardListener {
	
	public static enum WizardStatus {
		OPENED,
		CLOSED
	}
	
        /**
         * Keeps the listener informed about what's going on
         * @param currentWizardStatus
         * @return return <b>true</b> if you wan't to be keep informed on future events
         * when returning <b>false</b>, the listener will be deregistered
         */
        public boolean onStateChange(WizardStatus currentWizardStatus);

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.redeye.FrameWork.base.wizards;

import java.util.Properties;

/**
 *
 * @author Mario
 */
public class WizardProperties extends Properties  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wizardSmallLogoPath = "";
    private String wizardMainLogoPath = "";

    private String buttonNextText = "Weiter";
    private String buttonPrevText = "Zurück";
    private String buttonJumpEndText = "Fertig";
    private String buttonCancelText = "Abbrechen";
    private String wizardTitle = "Setup Wizard";
    private String legendAreaTitle = "Setup-Verlauf";

    public String getButtonCancelText() {
        return buttonCancelText;
    }

    public void setButtonCancelText(String buttonCloseText) {
        this.buttonCancelText = buttonCloseText;
    }

    public String getButtonJumpEndText() {
        return buttonJumpEndText;
    }

    public void setButtonJumpEndText(String buttonJumpEndText) {
        this.buttonJumpEndText = buttonJumpEndText;
    }

    public String getButtonNextText() {
        return buttonNextText;
    }

    public void setButtonNextText(String buttonNextText) {
        this.buttonNextText = buttonNextText;
    }

    public String getButtonPrevText() {
        return buttonPrevText;
    }

    public void setButtonPrevText(String buttonPrevText) {
        this.buttonPrevText = buttonPrevText;
    }

    public String getWizardMainLogoPath() {
        return wizardMainLogoPath;
    }

    public void setWizardMainLogoPath(String wizardMainLogoPath) {
        this.wizardMainLogoPath = wizardMainLogoPath;
    }

    public String getWizardSmallLogoPath() {
        return wizardSmallLogoPath;
    }

    public void setWizardSmallLogoPath(String wizardSmallLogoPath) {
        this.wizardSmallLogoPath = wizardSmallLogoPath;
    }

    public String getWizardTitle() {
        return wizardTitle;
    }

    public void setWizardTitle(String wizardTitle) {
        this.wizardTitle = wizardTitle;
    }

    public String getLegendAreaTitle() {
        return legendAreaTitle;
    }

    public void setLegendAreaTitle(String legendAreaTitle) {
        this.legendAreaTitle = legendAreaTitle;
    }

    
}

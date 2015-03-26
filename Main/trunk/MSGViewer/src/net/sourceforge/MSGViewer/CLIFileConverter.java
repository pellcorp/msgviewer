package net.sourceforge.MSGViewer;

import java.io.File;
import java.text.MessageFormat;

import net.sourceforge.MSGViewer.factory.MessageParserFactory;

import org.apache.log4j.Level;

import at.redeye.FrameWork.base.BaseModuleLauncher;
import at.redeye.FrameWork.base.Root;

import com.auxilii.msgparser.Message;

public abstract class CLIFileConverter {

	protected ModuleLauncher module_launcher;
	protected Root root;
	private final String sourceType;
	private final String targetType;

	/**
	 * @param module_launcher
	 * @param sourceType
	 *            the source file type (i.e. ending) without a leading dot. E.g.
	 *            "msg"
	 * @param targetType
	 *            the target file type (i.e. ending) without a leading dot. E.g.
	 *            "mbox"
	 */
	public CLIFileConverter(ModuleLauncher module_launcher, String sourceType,
			String targetType) {
		this.module_launcher = module_launcher;
		this.root = module_launcher.root;
		BaseModuleLauncher.BaseConfigureLogging(Level.ERROR);

		this.sourceType = sourceType;
		this.targetType = targetType;
	}

	/**
	 * @return the CLI flag that will trigger this converter. E.g. "-msg2mbox"
	 */
	public abstract String getCLIParameter();

	public void usage() {
		System.out.println(this.root.MlM(MessageFormat.format(
				"usage: {0} FILE FILE ....", this.getCLIParameter())));
	}

	public void work() {
		boolean converted = false;
		MessageParserFactory factory = new MessageParserFactory();

		for (String sourceFile : this.module_launcher.args) {
			if (sourceFile.toLowerCase().endsWith(
					MessageFormat.format(".{0}", this.sourceType))) {
				converted = true;
				try {
					Message msg = factory.parseMessage(new File(sourceFile));

					int idx = sourceFile.lastIndexOf(".");
					String baseFilename = sourceFile.substring(0, idx);

					File targetFile = new File(MessageFormat.format("{0}.{1}",
							baseFilename, this.targetType));

					factory.saveMessage(msg, targetFile);
				} catch (Exception ex) {
					System.err.print(ex);
					ex.printStackTrace();
				}
			}
		}

		if (!converted) {
			this.usage();
		}
	}
}
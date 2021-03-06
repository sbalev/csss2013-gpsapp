/*
 * This file is a part of a project under the terms of the GPL3.
 * You can find these terms in the COPYING file distributed with the project.
 * 
 *  Copyright 2013 Guilhelm Savin
 */
package csss2013.process;

import csss2013.App;
import csss2013.Process;
import csss2013.Trace;
import csss2013.annotation.Default;
import csss2013.annotation.Title;

@Default
@Title("Normalize XYZ")
public class NormalizeXYZ implements Process {
	/*
	 * (non-Javadoc)
	 * 
	 * @see csss2013.Process#getPriority()
	 */
	public int getPriority() {
		return 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see csss2013.Process#process(csss2013.App)
	 */
	public void process(App app) {
		double x = 0, y = 0, n = 0;

		for (int i = 0; i < app.getTraceCount(); i++) {
			Trace t = app.getTrace(i);
			n += t.getNodeCount();

			for (int j = 0; j < t.getNodeCount(); j++) {
				double[] xyz = t.getNode(j).getAttribute("xyz");
				x += xyz[0];
				y += xyz[1];
			}
		}

		x /= n;
		y /= n;

		for (int i = 0; i < app.getTraceCount(); i++) {
			Trace t = app.getTrace(i);

			for (int j = 0; j < t.getNodeCount(); j++) {
				double[] xyz = t.getNode(j).getAttribute("xyz");

				xyz[0] -= x;
				xyz[1] -= y;

				t.getNode(j).setAttribute("xyz", xyz);
			}
		}
	}
}

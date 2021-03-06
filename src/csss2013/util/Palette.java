/*
 * This file is a part of a project under the terms of the GPL3.
 * You can find these terms in the COPYING file distributed with the project.
 * 
 *  Copyright 2013 Guilhelm Savin
 */
package csss2013.util;

public class Palette {
	public static final String[] COLORS = { "#abd44c", "#9a1111",
			"#008aff", "#d6d498", "#ba90ce", "#284faf", "#629377", "#ebc74b", "#294f63",
			"#ff9c00", "#4a6350", "#675382", "#579bca" };

	protected int index;

	public Palette() {
		index = 0;
	}

	public String nextColor() {
		return COLORS[index++ % COLORS.length];
	}
}

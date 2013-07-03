package csss2013;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.graphstream.graph.implementations.AdjacencyListGraph;
import org.graphstream.stream.file.FileSourceGPX;
import org.graphstream.ui.swingViewer.Viewer;

public class Trace extends AdjacencyListGraph {
	public static final boolean HIGH_QUALITY_RENDERING = true;

	public static Trace load(String name, File file) throws IOException {
		FileInputStream in = new FileInputStream(file);

		Trace t = load(name, in);
		in.close();

		return t;
	}

	public static Trace load(String name, InputStream data) throws IOException {
		if (data == null) {
			System.err.printf("Data is null !\n");
			return null;
		}

		Trace t = new Trace(name);
		FileSourceGPX gpx = new FileSourceGPX();

		gpx.addSink(t);
		gpx.readAll(data);
		gpx.removeSink(t);

		return t;
	}

	public static void normalize(Trace... traces) {
		double x = 0, y = 0, n = 0;

		for (int i = 0; i < traces.length; i++) {
			Trace t = traces[i];
			n += t.getNodeCount();

			for (int j = 0; j < t.getNodeCount(); j++) {
				double[] xyz = t.getNode(j).getAttribute("xyz");
				x += xyz[0];
				y += xyz[1];
			}
		}

		x /= n;
		y /= n;

		for (int i = 0; i < traces.length; i++) {
			Trace t = traces[i];

			for (int j = 0; j < t.getNodeCount(); j++) {
				double[] xyz = t.getNode(j).getAttribute("xyz");

				xyz[0] -= x;
				xyz[1] -= y;

				t.getNode(j).setAttribute("xyz", xyz);
			}
		}
	}

	protected String color = "#222222";
	protected String customStyle = "";

	protected Trace(String id) {
		super(id);

		if (HIGH_QUALITY_RENDERING) {
			addAttribute("ui.quality");
			addAttribute("ui.antialias");
		}
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setCustomStyle(String style) {
		this.customStyle = style;
	}

	public String getCustomStyle() {
		return customStyle;
	}

	@Override
	public Viewer display() {
		return display(false);
	}
}
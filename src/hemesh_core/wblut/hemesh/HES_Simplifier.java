/*
 * HE_Mesh  Frederik Vanhoutte - www.wblut.com
 *
 * https://github.com/wblut/HE_Mesh
 * A Processing/Java library for for creating and manipulating polygonal meshes.
 *
 * Public Domain: http://creativecommons.org/publicdomain/zero/1.0/
 */

package wblut.hemesh;

/**
 * Abstract base class for mesh reduction. Implementation should preserve mesh
 * validity.
 *
 * @author Frederik Vanhoutte (W:Blut)
 *
 */
abstract public class HES_Simplifier extends HE_Machine {
	/**
	 * Instantiates a new HES_Simplifier.
	 */
	public HES_Simplifier() {
		super();
	}

	@Override
	public HE_Mesh apply(final HE_Mesh mesh) {
		if (mesh == null || mesh.getNumberOfVertices() == 0) {
			tracker.setStopStatus(this, "Nothing to simplify.");
			return new HE_Mesh();
		}
		HE_Mesh copy = mesh.get();
		try {
			HE_Mesh result = applySelf(mesh);
			tracker.setStopStatus(this, "Mesh simplified.");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			mesh.setNoCopy(copy);
			tracker.setStopStatus(this, "Simplifier failed. Resetting mesh.");
			return mesh;
		} finally {
			copy = null;
		}

	}

	@Override
	public HE_Mesh apply(final HE_Selection selection) {
		if (selection == null) {
			tracker.setStopStatus(this, "Nothing to simplify.");

			return new HE_Mesh();
		}
		HE_Mesh copy = selection.getParent().get();
		try {
			HE_Mesh result = applySelf(selection);
			tracker.setStopStatus(this, "Mesh simplified.");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			selection.getParent().setNoCopy(copy);
			tracker.setStopStatus(this, "Simplifier failed. Resetting mesh.");
			return selection.getParent();
		} finally {
			copy = null;
		}

	}

	protected abstract HE_Mesh applySelf(final HE_Mesh mesh);

	protected abstract HE_Mesh applySelf(final HE_Selection selection);
}
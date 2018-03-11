/**
 * 
 */
package wblut.geom;

import org.eclipse.collections.impl.list.mutable.FastList;

/**
 * The Class WB_BSPNode.
 *
 * @author Frederik Vanhoutte, W:Blut
 */
public class WB_BSPNode {
	
	/** The partition. */
	protected WB_Plane				partition;
	
	/** The polygons. */
	protected FastList<WB_Polygon>	polygons;
	
	/** The pos. */
	protected WB_BSPNode			pos	= null;
	
	/** The neg. */
	protected WB_BSPNode			neg	= null;

	/**
	 * Instantiates a new w b_ bsp node.
	 */
	public WB_BSPNode() {
		polygons = new FastList<WB_Polygon>();
	}

}
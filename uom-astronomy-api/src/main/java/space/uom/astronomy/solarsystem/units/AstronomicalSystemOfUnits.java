package space.uom.astronomy.solarsystem.units;

import java.util.HashMap;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Area;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import javax.measure.quantity.VolumetricDensity;

import tec.uom.se.AbstractSystemOfUnits;
import tec.uom.se.AbstractUnit;
import tec.uom.se.function.MultiplyConverter;
import tec.uom.se.unit.ProductUnit;
import tec.uom.se.unit.TransformedUnit;
import tec.uom.se.unit.SI;
import tec.uom.se.unit.MetricPrefix;

public class AstronomicalSystemOfUnits extends AbstractSystemOfUnits {

	/**
	 * The singleton instance.
	 */
	private static final AstronomicalSystemOfUnits INSTANCE = new AstronomicalSystemOfUnits();

	/**
	 * Holds the mapping quantity to unit.
	 */
	private final HashMap<Class<? extends Quantity>, AbstractUnit> quantityToUnit = new HashMap<Class<? extends Quantity>, AbstractUnit>();

	/**
	 * Default constructor (prevents this class from being instantiated).
	 */
	private AstronomicalSystemOfUnits() {
	}

	/**
	 * Returns the singleton instance of this class.
	 *
	 * @return the metric system instance.
	 */
	public static AstronomicalSystemOfUnits getInstance() {
		return INSTANCE;
	}

	/**
	 * A mass unit accepted for use with SI units (standard name <code>UA</code>
	 * ). The solar mass (M☉) is a standard unit of mass in astronomy that is
	 * used to indicate the masses of other stars, as well as clusters, nebulae
	 * and galaxies. It is equal to the mass of the Sun, about two nonillion
	 * kilograms:
	 */

	public static final TransformedUnit<Mass> SOLAR_MASS = new TransformedUnit<Mass>(
			"M☉", SI.KILOGRAM, new MultiplyConverter(1.9891 * Math.pow(10, 30)));

	/**
	 * A length unit accepted for use with SI units (standard name
	 * <code>UA</code>). The astronomical unit is a unit of length. Its value is
	 * such that, when used to describe the motion of bodies in the solar
	 * system, the heliocentric gravitation constant is (0.017 202 098 95)2
	 * ua3·d-2. The value must be obtained by experiment, and is therefore not
	 * known exactly. public static final Unit<Length> ASTRONOMIC_UNIT =
	 * addUnit(SI.ASTRONOMICAL_UNIT);
	 */

	public static final TransformedUnit<Length> ASTRONOMICAL_UNIT = new TransformedUnit<Length>(
			"AU", SI.METRE, new MultiplyConverter(149597871000.0));

	/**
	 * The SI unit for density quantities (standard name <code>m2</code>).
	 */

	public static final ProductUnit<VolumetricDensity> GRAM_PER_CUBIC_CENTIMETRE = addUnit(
			new ProductUnit<VolumetricDensity>(SI.GRAM.divide((MetricPrefix
					.CENTI(SI.METRE)).pow(3))), VolumetricDensity.class);

	/**
	 * The SI unit for area quantities (standard name <code>m2</code>).
	 */
	public static final ProductUnit<Area> SQUARE_KILOMETRE = addUnit(
			new ProductUnit<Area>(MetricPrefix.KILO(SI.METRE).multiply(
					MetricPrefix.KILO(SI.METRE))), Area.class);

	/**
	 * The SI unit for volume quantities (standard name <code>m3</code>).
	 */
	public static final ProductUnit<Volume> CUBIC_KILOMETRE = addUnit(
			new ProductUnit<Volume>(SQUARE_KILOMETRE.multiply(MetricPrefix
					.KILO(SI.METRE))), Volume.class);

	// ///////////////////
	// Collection View //
	// ///////////////////

	@Override
	public String getName() {
		return "ASTRONOMICALSYSTEMOFUNITS";
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public <Q extends Quantity<Q>> AbstractUnit<Q> getUnit(Class<Q>
	 * quantityType) { return quantityToUnit.get(quantityType); }
	 */

	/**
	 * Adds a new unit not mapped to any specified quantity type.
	 *
	 * @param unit
	 *            the unit being added.
	 * @return <code>unit</code>.
	 */

	private static <U extends Unit<Q>, Q extends Quantity<Q>> U addUnit(U unit) {
		INSTANCE.units.add(unit);
		return unit;
	}

	/**
	 * Adds a new unit and maps it to the specified quantity type.
	 *
	 * @param unit
	 *            the unit being added.
	 * @param type
	 *            the quantity type.
	 * @return <code>unit</code>.
	 */
	private static <U extends AbstractUnit<?>> U addUnit(U unit,
			Class<? extends Quantity<?>> type) {
		INSTANCE.units.add(unit);
		INSTANCE.quantityToUnit.put(type, unit);
		return unit;
	}

}

package com.mjwedeking.navHydraulicCalc

import kotlin.collections.HashMap

class UnitConversion {
    private val factors = HashMap<String, Double>()

    init{
    //	Flow conversion factors - input units converted to SI m3/s
        factors.put("gpm", 0.000063090574947062)
        factors.put("lps", 0.001)
        factors.put("mgd", 0.043812899268793)
        factors.put("cms", 1.0)
        factors.put("cfs", 0.028317016493754)
        factors.put("gpd", 0.000000043812899268793)
        factors.put("AFY", 0.000039087483402945)
        factors.put("AFD", 0.0142764101568)

        //	Depth conversion factors - input units converted to SI meters
        factors.put("mile", 1609.344)
        factors.put("ft", 0.3048006096012)
        factors.put("inch", 0.0254000508001)
        factors.put("mm", 0.001)
        factors.put("cm", 0.01)
        factors.put("m", 1.0)

        //	Velocity factors converted to SI velocity unit (m/s)
        factors.put("fps", 0.30480060960127)
        factors.put("mph", 0.447040894081863)
        factors.put("mps", 1.0)
        factors.put("kph", 0.277777777777777)

        //	Area factors converted to SI area unit (m2)
        factors.put("ac", 4046.856422)
        factors.put("ha", 10000.0)
        factors.put("ft2", 0.09290304)
        factors.put("m2", 1.0)
        factors.put("in2", 0.00064516)

        //	Volume factors converted to SI area unit (m3)
        factors.put("gal", 0.0037854)
        factors.put("mg", 3785.4)
        factors.put("AF", 1233.48)
        factors.put("l", 0.001)
        factors.put("ft3", 0.0283168)
        factors.put("m3", 1.0)

        // Slope factors converted to x/x
        factors.put("ft/ft", 100.0)
        factors.put("m/m", 100.0)
        factors.put("%", 1.0)

        // Time factors converted to x/x
        factors.put("yr", 31557600.0)
        factors.put("day", 86400.0)
        factors.put("hr", 3600.0)
        factors.put("min", 60.0)
        factors.put("sec", 1.0)

        factors.put("NotDefined", 1.0)
    }

    fun GetFactor(inUnits: String, outUnits: String): Double {
        var inFactor = 1.0
        var outFactor = 1.0
        try {
            if (factors.get(inUnits) != null)
                inFactor = factors.get(inUnits) as Double
            if (factors.get(outUnits) != null)
                outFactor = factors.get(outUnits) as Double
        } finally {
        }
        return inFactor / outFactor
    }
}

package com.mjwedeking.navHydraulicCalc.ui.calculator

class Pipe {
    /// flow rate
    private var _flow: Double = 0.toDouble()
    /// depth of flow
    private var _depth: Double = 0.toDouble()
    private var _velocity: Double = 0.toDouble()
    private var _diameter: Double = 0.toDouble()
    private var _slope: Double = 0.toDouble()
    private var _nvalue: Double = 0.toDouble()
    private var _cap: Double = 0.toDouble()
    private var _wp: Double = 0.toDouble()
    private var _wa: Double = 0.toDouble()

    fun Pipe(){
        _depth = 0.0
        _flow = 0.0
        _velocity = 0.0
        _diameter = 0.0
        _slope = 0.0
        _nvalue = 0.013
        _wp = 0.0
        _wa = 0.0
        _cap = 0.0
    }

    fun getFlow(): Double {
        return _flow
    }

    fun setFlow(value: Double) {
        _flow = value
    }

    fun getDepth(): Double {
        return _depth
    }

    fun setDepth(value: Double) {
        _depth = value
    }

    fun getVelocity(): Double {
        return _velocity
    }

    fun setVelocity(value: Double) {
        _velocity = value
    }

    fun getDiameter(): Double {
        return _diameter
    }

    fun setDiameter(value: Double) {
        _diameter = value
    }

    fun getSlope(): Double {
        return _slope
    }

    fun setSlope(value: Double) {
        _slope = value
    }

    fun getNValue(): Double {
        return _nvalue
    }

    fun setNValue(value: Double) {
        _nvalue = value
    }

    fun getdD(): Double {
        return _depth / _diameter
    }

    fun getqCAP(): Double {
        return _flow / _cap
    }

    fun getWetPerimiter(): Double {
        return _wp
    }

    fun getWetArea(): Double {
        return _wa
    }

    fun getVelHead(): Double {
        return _velocity * _velocity / (2 * 32.2)
    }

    fun getCap(): Double {
        return _cap
    }

    fun getHydRadius(): Double {
        return _wa / _wp
    }

    /// Calculate the Flow and Velocity given the Depth
    /// Calculates wetted perimiter dblWPerim
    /// calculates wetted area dblArea
    /// Uses V = 1.486/n * (A/P)^(2/3)* S^(1/2)
    /// and  Q = V * A
    fun QManning(): Boolean {
        var dblTheta = 0.0
        ///Wetted Perimiter
        var dblWPerim = 0.0
        ///Wetted Cross Sectional Area
        var dblWArea = 0.0
        ///Pipe Radius
        val dblRadius = _diameter / 2.0

        _flow = 0.0
        _velocity = 0.0

        if (_depth == _diameter)
            dblTheta = 2.0 * Math.PI   //full pipe theta is 2 pi
        else
            dblTheta = 2.0 * Math.acos((dblRadius - _depth) / dblRadius)
        dblWPerim = dblTheta * dblRadius
        dblWArea = dblRadius * dblRadius * (dblTheta - Math.sin(dblTheta)) / 2.0
        _velocity =
            1.486 / _nvalue * Math.pow(dblWArea / dblWPerim, 2.0 / 3.0) * Math.pow(_slope, 0.5)
        _flow = _velocity * dblWArea
        _wa = dblWArea
        _wp = dblWPerim

        Capacity()

        return true
    }

    /// Calculate the Flow given the Velocity and Depth
    /// calculates wetted area dblArea
    /// Uses Q = V * A
    fun QVA(): Boolean {
        var dblTheta = 0.0
        var dblWArea = 0.0                      //wetted cross sectional area
        val dblRadius = _diameter / 2.0        //pipe radius

        _flow = 0.0

        if (_depth == _diameter)
            dblTheta = 2.0 * Math.PI   //full pipe theta is 2 pi
        else
            dblTheta = 2.0 * Math.acos((dblRadius - _depth) / dblRadius)
        dblWArea = dblRadius * dblRadius * (dblTheta - Math.sin(dblTheta)) / 2.0
        _flow = _velocity * dblWArea
        _wa = dblWArea
        _wp = dblTheta * dblRadius

        return true
    }

    /// Calculate the Velocity given the Flow and Depth
    /// calculates wetted area dblArea
    /// Uses V = Q / A
    fun VQA(): Boolean {
        var dblTheta = 0.0
        var dblWArea = 0.0                             //wetted cross sectional area
        val dblRadius = _diameter / 2.0        //pipe radius

        _velocity = 0.0
        if (_depth == _diameter)
            dblTheta = 2.0 * Math.PI          //full pipe theta is 2 pi
        else
            dblTheta = 2.0 * Math.acos((dblRadius - _depth) / dblRadius)

        dblWArea = dblRadius * dblRadius * (dblTheta - Math.sin(dblTheta)) / 2.0
        _velocity = _flow / dblWArea

        _wa = dblWArea
        _wp = dblTheta * dblRadius

        return true
    }

    /// Calculate the Depth and Velocity given the Flow
    /// Increments the depth by 0.0000001 ft until
    /// the given flow is calculated and retuns the
    /// resultant depth. Also calulates Velocity.
    fun DManning(): Boolean {
        //double dblWPerim = 0.0;                            //wetted perimiter
        //double dblWArea = 0.0;                             //wetted cross sectional area
        //double dblCFlow = 0.0;                             //calculated flow
        //double tmpFlow = 0.0;
        //double dblTheta = 0.0;                             //theta
        //double dblRadius = _diameter / 2.0;        //radius in feet
        //int loopcount = 0;

        //tmpFlow = _flow;
        _depth = 0.0                              //depth of flow
        _velocity = 0.0

        if (_slope <= 0) {
            _depth = 0.0
            _velocity = 0.0
        } else {
            _depth = depthLoop(0.0, .94 * _diameter, _flow)
        }
        return true
    }

    private fun depthLoop(lowDepth: Double, highDepth: Double, destFlow: Double): Double {
        var calcDepth: Double

        calcDepth = (highDepth + lowDepth) / 2
        _depth = calcDepth
        QManning()
        if (destFlow < _flow * 0.999999)
            calcDepth = depthLoop(lowDepth, calcDepth, destFlow)
        if (destFlow > _flow * 1.000001)
            calcDepth = depthLoop(calcDepth, highDepth, destFlow)
        return calcDepth
    }

    /// Calculate the Capacity of the pipe
    /// Calculates wetted perimiter dblWPerim
    /// calculates wetted area dblArea
    /// Uses V = 1.486/n * (A/P)^(2/3)* S^(1/2)
    /// and  Q = V * A
    fun Capacity(): Boolean {
        var dblTheta = 0.0
        ///Wetted Perimiter
        var dblWPerim = 0.0
        ///Wetted Cross Sectional Area
        var dblWArea = 0.0
        ///Pipe Radius
        val dblRadius = _diameter / 2.0
        ///Velocity
        var dblVelocity = 0.0

        _cap = 0.0

        dblTheta = 2.0 * Math.PI   //full pipe theta is 2 pi
        dblWPerim = dblTheta * dblRadius
        dblWArea = dblRadius * dblRadius * Math.PI
        dblVelocity =
            1.486 / _nvalue * Math.pow(dblWArea / dblWPerim, 2.0 / 3.0) * Math.pow(_slope, 0.5)
        _cap = dblVelocity * dblWArea

        return true
    }
}
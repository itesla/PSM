/**
 * 
 */
(function() {

	var root = this;

	var linedUp;
	var linedUpOverlay;
	var lineMapping;
	var xScale;
	var yScale;

	var highlightedLinedUpMarey;

	var linedUpMargin = {
		top : 20,
		right : 40,
		bottom : 20,
		left : 80
	};

	var d3Svg = function(obj) {
		if (obj instanceof d3Svg)
			return obj;
		if (!(this instanceof d3Svg))
			return new d3Svg(obj);
		this._wrapped = obj;
	};

	if (typeof exports !== 'undefined') {
		if (typeof module !== 'undefined' && module.exports) {
			exports = module.exports = d3Svg;
		}
		exports.d3Svg = d3Svg;
	} else {
		root.d3Svg = d3Svg;
	}

	  // add "appendOnce" method to d3 selections which can be called many times but ensures that
	  // the dom element is only added once.  It always returns the dom element, and adds a "firstTime"
	  // attribute to it that is a length-1 selection the first time its added, and an empty selection
	  // all subsequent times
	d3.selection.prototype.appendOnce = function(type, clazz) {
		var result = this.selectAll('.' + clazz.replace(/ /g, '.')).data([ 1 ]);
		result.firstTime = result.enter().append(type).attr('class', clazz);
		return result;
	};

	// add "onOnce" method to d3 selections - adds a single listener to the
	// selection that filters on sub-selections
	// when there are too many events that each have a listener and it becomes a
	// performance problem, switch over
	// to using this listener on a parent dom element to reduce total number of
	// listeners
	d3.selection.prototype.onOnce = function(eventType, subSelector, func) {
		this.each(function() {
			$(this).on(eventType, subSelector, function(evt) {
				var d = d3.select(this).datum();
				try {
					d3.event = evt.originalEvent;
					return func.call(this, d);
				} finally {
					d3.event = null;
				}
			});
		});
		return this;
	};

	// add "off" method to d3 selections - clears events types from a space
	// separated list from the
	// selection that were added using D3 or jQuery.
	// For example: selection.off('mouseover mouseout')
	d3.selection.prototype.off = function(eventTypes) {
		var self = this;
		eventTypes.split(/\s+/g).forEach(function(eventType) {
			self.each(function() {
				$(self).off(eventType);
			}).on(eventType, null);
		});
		return self;
	};

	// add utility to move an SVG selection to the front
	d3.selection.prototype.moveToFront = function() {
		return this.each(function() {
			this.parentNode.appendChild(this);
		});
	};
	
	// highlights the selected line and attenuates other lines 
	d3Svg.highlightLinedUpMarey = function(d) {

		if (d === 0)
			return;
		
		// restore default color for lines
		d3Svg.unhighlightLinedUpMarey();
		highlightedLinedUpMarey = d;
		
		// draw a circle at the points where data exist for the selected line 
		linedUpOverlay.selectAll('g.mareystops').data([ d ]).enter()
				.append('g').attr('class', 'mareystops').call(drawStops);
		
		// hightlights the selected line and 
		linedUp.selectAll('.mareyline').classed({
			highlight : function(other) {
				return other === d;
			},
			dimmed : function(other) {
				return other !== d;
			}
		});
	}

	// restore default color for lines
	d3Svg.unhighlightLinedUpMarey = function() {
		if (!highlightedLinedUpMarey) {
			return;
		}
		highlightedLinedUpMarey = null;
		
		// remove all circles on the lines
		linedUpOverlay.selectAll('*').remove();
		
		// restore default color for lines
		linedUp.selectAll('.mareyline').classed({
			highlight : false,
			dimmed : false
		});
	}

	// draw a circle at the points where data exist for the selected line
	function drawStops(selection) {
		
		// gets position to the circles
		var items = selection.selectAll('.point').data(function(d) {
			var result = getPointsFromStop(d).filter(function(stop) {
				return !!stop;
			});
			var offset = xScale(d.result[0].value);
			result.forEach(function(stop) {
				stop.offset = offset;
			});
			return result;
		}, function(d, i) {
			return i;
		});
		
		// add circles to the chart
		items.enter().append('circle').attr('r', 2).attr('class', 'point');

		// draw circles in theirs position
		items.attr('cx', function(d) {
			return d[0];
		}).attr('cy', function(d) {
			return d[1];
		});
	}

	// draw lines
	function drawLinedUpLines(lines) {
		lines.attr('transform', function(d) {
			var firstX = xScale(d.result[0].x);
			return 'translate(' + firstX + ',0)';
		}).attr('d', draw());
	}

	// scale the data and draw lines
	function draw() {
		return function(data) {
			var points = getPointsFromStop(data);
			return lineMapping(points);
		};
	}

	// scale data
	function getPointsFromStop(data) {
		var points = data.result.map(function(d) {
			var x = xScale(d.x);
			var y = yScale(d.y);
			return [ x, y ];
		});
		return points;
	}

	// create a line-up marey diagram
	d3Svg.lineUpMarey = function() {

		// calculates graph sizes
		var maxLinedUpMareyChartWidth = 970;
		linedUpOuterWidth = Math.min($('.lined-up-marey-container .container')
				.width(), maxLinedUpMareyChartWidth);
		linedUpOuterHeight = linedUpOuterWidth * 300 / 780;

		// draw function
		lineMapping = d3.svg.line().x(function(d) {
			return d[0];
		}).y(function(d) {
			return d[1];
		}).defined(function(d) {
			return d !== null;
		}).interpolate("linear");

		// creates svg tag
		var linedUpSvg = d3.select('.lined-up-marey').appendOnce('svg',
				'lined-up').attr('width', linedUpOuterWidth).attr('height',
				linedUpOuterHeight);
		// creates g tag
		linedUp = linedUpSvg.appendOnce('g', 'g');
		// creates overlay tag
		linedUpOverlay = linedUpSvg.appendOnce('g', 'overlay');
		// resize g tag
		linedUp.firstTime.attr('transform', 'translate(' + linedUpMargin.left
				+ ',' + linedUpMargin.top + ')');
		// resize overlay tag
		linedUpOverlay.firstTime.attr('transform', 'translate('
				+ linedUpMargin.left + ',' + linedUpMargin.top + ')');

		// set listeners on chart
		var t = null;
		linedUp.off('mouseover mouseout').onOnce('mouseover', 'path',
				function(d) {
					clearTimeout(t);
					d3Svg.highlightLinedUpMarey(d);
					d3.select(this).moveToFront();
				}).onOnce('mouseout', 'path', function() {
			clearTimeout(t);
			t = setTimeout(d3Svg.unhighlightLinedUpMarey, 100);
		});
	}

	d3Svg.setData = function(linedUpData) {

		// calculates chart sizes
		var linedUpMareyWidth = linedUpOuterWidth - linedUpMargin.left
				- linedUpMargin.right, linedUpMareyHeight = linedUpOuterHeight
				- linedUpMargin.top - linedUpMargin.bottom;

		// calculates axis ranges 
		var xmin = d3.min(linedUpData, function(d) {
			return d3.min(d.result, function(r) {
				return r.x;
			});
		})
		var xmax = d3.max(linedUpData, function(d) {
			return d3.max(d.result, function(r) {
				return r.x;
			});
		})

		var ymin = d3.min(linedUpData, function(d) {
			return d3.min(d.result, function(r) {
				return r.y;
			});
		})
		var ymax = d3.max(linedUpData, function(d) {
			return d3.max(d.result, function(r) {
				return r.y;
			});
		})

		// set axis ranges and sizes
		xScale = d3.scale.linear().domain([ xmin, xmax ]).range(
				[ 0, linedUpMareyWidth ]);
		yScale = d3.scale.linear().domain([ ymin - 0.05, ymax + 0.05 ]).range(
				[ linedUpMareyHeight, linedUpMargin.bottom ]).clamp(true);

		// creates axis 
		var linedUpXAxis = d3.svg.axis().orient("bottom").scale(xScale);

		var linedUpYAxis = d3.svg.axis().innerTickSize(-linedUpMareyWidth)
				.outerTickSize(0).orient("left").scale(yScale);

		// creates axis tag
		var yAxis = linedUp.appendOnce('g', 'y axis').call(linedUpYAxis);
		var xAxis = linedUp.appendOnce('g', 'x axis').call(linedUpXAxis);
		xAxis.firstTime.attr('transform', 'translate(0,' + linedUpMareyHeight
				+ ')');

		// calculates chart container sizes
		var linedUpWidth = linedUpOuterWidth - linedUpMargin.left
		- linedUpMargin.right;
		var linedUpHeight = linedUpOuterHeight - linedUpMargin.top
				- linedUpMargin.bottom;

		// creates container tag
		var linedUpMareyContainer = linedUp.appendOnce('g', 'mareylinecontainer');
		
		// creates clip tag
		linedUpMareyContainer.firstTime.attr('clip-path', 'url(#mareyClip)');
		linedUp.appendOnce('defs', 'defs').appendOnce('clipPath', 'clip').attr(
				'id', 'mareyClip').appendOnce('rect', 'clipRect').attr('width',
				linedUpWidth).attr('height', linedUpHeight);

		// creates lines tag
		var linedUpMareyLines = linedUpMareyContainer.selectAll('.mareyline')
				.data(linedUpData, function(d) {
					return d.id;
				});

		// add lines to the chart
		linedUpMareyLines.enter().append('path').attr('class', 'mareyline red');

		// draws lines 
		linedUpMareyLines.call(drawLinedUpLines);

	}

}).call(this);

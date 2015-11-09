/***********************************************************
 **	http://www.jq-school.com/Article.aspx?kid=471/
 ** Date:2015-4-27
 ** Email::LeonJsObj@126.com
 **	Author:Leon.z
 ** description:随便写的滚轮修改input值，简单易用。
 ** eg:$(obj).rollValue({minValue:-20,maxValue:50,step:5});
 ***********************************************************/
;(function($) {
	$.fn.rollValue = function(config) {
		/**插件默认值**/
		$.fn.rollValue.defaults = {
			minValue: 0,
			maxValue: 100,
			step: 2,
		};
		var opt = $.extend({}, $.fn.rollValue.defaults, config);
		return this.each(function() {
			var _ele = $(this),
				destination,
				start = opt.minValue < 0 ? _ele.val() : opt.minValue;
			_ele.val(start);
			_ele.on("mousewheel DOMMouseScroll", function(e) {
				var val = $(this).val() - 0;
				if (e.type === "mousewheel") {
					var p = e.originalEvent.wheelDelta / 120;
				} else if (e.type === "DOMMouseScroll") {
					var p = e.originalEvent.detail * (-1) / 3;
				}
				destination = val + opt.step * p;

				if (destination < opt.minValue) {
					return false;

				} else if (destination > opt.maxValue) {
					return false;
				}
				setTimeout(function() {
					_ele.val(val + opt.step * p);
				}, 50)
			})
		});
	};
})(jQuery);
(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory(require("jQuery"));
	else if(typeof define === 'function' && define.amd)
		define(["jQuery"], factory);
	else if(typeof exports === 'object')
		exports["jq-nav-scroll-spy"] = factory(require("jQuery"));
	else
		root["jq-nav-scroll-spy"] = factory(root["jQuery"]);
})(typeof self !== 'undefined' ? self : this, function(__WEBPACK_EXTERNAL_MODULE_1__) {
return /******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 2);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});
var pluginName = exports.pluginName = 'jqNavScrollSpy';
var defaults = exports.defaults = {
  navItems: '.nav-item', // 导航元素
  scrollContainer: 'html,body', // 滚动的容器
  spyItems: '.spy-item', // 监视的元素
  easing: 'swing', // 动效
  speed: 550 // 速度
};

/***/ }),
/* 1 */
/***/ (function(module, exports) {

module.exports = __WEBPACK_EXTERNAL_MODULE_1__;

/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _class = __webpack_require__(3);

var _class2 = _interopRequireDefault(_class);

var _config = __webpack_require__(0);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var $ = __webpack_require__(1);

$.prototype.jqNavScrollSpy = function initJqNavScrollSpy(configs) {
  return this.each(function eachElements() {
    $.data(this, 'plugin_' + _config.pluginName, new _class2.default(this, configs));
  });
};

/***/ }),
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _config = __webpack_require__(0);

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var $ = __webpack_require__(1);

var JqNavScrollSpy = function () {
  function JqNavScrollSpy(element, configs) {
    _classCallCheck(this, JqNavScrollSpy);

    this.$element = element;
    this.$win = $(window);
    this.defaults = $.extend({}, _config.defaults, configs);
    this.init();
  }

  // 初始化


  _createClass(JqNavScrollSpy, [{
    key: 'init',
    value: function init() {
      this.$navItems = $(this.defaults.navItems);
      this.$spyItems = $(this.defaults.spyItems);
      this.$scrollContainer = $(this.defaults.scrollContainer);
      // 修正初始化的时候元素距离顶部的距离不等于滚动的距离的变量，也就是减去第一个元素距离顶部的高度
      this.fixTop = $(this.$spyItems[0]).offset().top;
      this.spyItemsData = this.getSpyItemsData();
      this.spyScroll();
      this.clickSwitch();
    }

    // 监听滚动事件

  }, {
    key: 'spyScroll',
    value: function spyScroll() {
      this.$win.on('scroll', this.throttle(this.scrollCallBack, 100, 200));
    }

    // 滚动监听的回调函数

  }, {
    key: 'scrollCallBack',
    value: function scrollCallBack() {
      var spyIndex = this.getVisibleElIndex();
      this.changeNav(this.$navItems[spyIndex]);
    }

    // 存储监视滚动元素的中心位置数组

  }, {
    key: 'getSpyItemsData',
    value: function getSpyItemsData() {
      var _this = this;

      var spyItemsData = [];
      this.$spyItems.each(function (index) {
        spyItemsData[index] = _this.getOffsetTop(index) + $(_this.$spyItems[index]).height() / 2;
      });
      return spyItemsData;
    }

    // 获得当前滚动到视图区的元素的索引

  }, {
    key: 'getVisibleElIndex',
    value: function getVisibleElIndex() {
      var _this2 = this;

      var spyIndex = void 0;
      var scrollTop = parseInt(this.$win.scrollTop(), 10);
      $.each(this.spyItemsData, function (index) {
        if (_this2.spyItemsData[0] >= scrollTop) {
          spyIndex = 0;
          return false;
        }if (_this2.spyItemsData[index] <= scrollTop && scrollTop <= _this2.spyItemsData[index + 1]) {
          spyIndex = index + 1;
          return false;
        }
        return true;
      });
      return spyIndex;
    }

    // 节流函数

  }, {
    key: 'throttle',
    value: function throttle(func, wait, mustRun) {
      var _this3 = this;

      var timeout = void 0;
      var startTime = new Date();
      return function () {
        for (var _len = arguments.length, rest = Array(_len), _key = 0; _key < _len; _key++) {
          rest[_key] = arguments[_key];
        }

        var curTime = new Date();
        clearTimeout(timeout);
        // 如果达到了规定的触发时间间隔，触发 handler
        if (curTime - startTime >= mustRun) {
          func.apply(_this3, rest);
          startTime = curTime;
          // 没达到触发间隔，重新设定定时器
        } else {
          timeout = setTimeout(func.bind(_this3), wait);
        }
      };
    }

    // 点击切换

  }, {
    key: 'clickSwitch',
    value: function clickSwitch() {
      var self = this;
      this.$navItems.on('click', function onNavItemClick() {
        self.changeNav(this);
        var navIndex = $(this).index();
        self.$win.off('scroll');
        self.scrollIntoView(navIndex);
      });
    }

    // 改变导航active

  }, {
    key: 'changeNav',
    value: function changeNav(currentNav) {
      this.$navItems.removeClass('active');
      $(currentNav).addClass('active');
    }

    // 滚动到可视区

  }, {
    key: 'scrollIntoView',
    value: function scrollIntoView(navIndex) {
      var _this4 = this;

      var offsetTop = parseInt(this.getOffsetTop(navIndex), 10);
      if (!this.$scrollContainer.is(':animated')) {
        this.$scrollContainer.stop().animate({ scrollTop: offsetTop }, this.defaults.speed, this.defaults.easing, function () {
          // 动画结束后重新注册滚动监听事件
          _this4.spyScroll();
        });
      }
    }

    // 获取滚动元素距离顶部的距离

  }, {
    key: 'getOffsetTop',
    value: function getOffsetTop(index) {
      return parseInt($(this.$spyItems[index]).offset().top, 10) - parseInt(this.fixTop, 10);
    }
  }]);

  return JqNavScrollSpy;
}();

exports.default = JqNavScrollSpy;

/***/ })
/******/ ]);
});
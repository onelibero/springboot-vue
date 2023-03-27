/**
 * 公共函数
 */

/**
 * 驼峰转下划线
 * @param {*} name
 * @returns
 */
export function camecaseToLine(name) {
	return name.replace(/([A-Z])/g, "_$1").toLowerCase();
}

/**
 * 调用：new Date().Format("yyyy-MM-dd hh:mm:ss");
 * fmt ---- 时间格式
 * hasweek ---- 用来表示是否显示星期
 */
Date.prototype.Format = function (fmt, hasweek) {
	var weekday = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
	var o = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
		S: this.getMilliseconds(), // 毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));

	return fmt + (hasweek ? "&nbsp;&nbsp;&nbsp;&nbsp;" + weekday[this.getDay()] : "");
};

/**
 * 数字补零，默认两位
 * let a=6;
 * a.az();//06
 * a.az(3);//006
 */
Number.prototype.az = function (n = 2) {
	let s = "";
	for (let i = 1; i < n; i++) {
		s += "0";
	}
	return (s + this).slice(-1 * n);
};

/**
 * 数组分组
 * @param array 数组
 * @param f 函数
 * @returns
 * const groupByBooks = this.$Common.groupBy(this.booksList, function (item) {return item.booksType;});
 */
export function groupBy(array, f) {
	const groups = {};
	array.forEach(function (o) {
		const group = f(o);
		groups[group] = groups[group] || [];
		groups[group].push(o);
	});
	return Object.keys(groups).map(function (group) {
		let resultObject = {};
		resultObject[group] = groups[group];
		return resultObject;
	});
}

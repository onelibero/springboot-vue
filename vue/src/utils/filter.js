/**
 * 10000 => "10,000"
 * @param {number} num
 */
export function toThousandFilter(num) {
	return (+num || 0).toString().replace(/^-?\d+/g, (m) => m.replace(/(?=(?!\b)(\d{3})+$)/g, ","));
}

/**
 * Upper case first char
 * {{ user.userName | upperCaseFirst }}
 * @param {String} string
 */
export function upperCaseFirst(string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
}

// 去掉 html 标签
export function delHtmlTag(value) {
	return value.replace(/<[^>]+>/g, "");
}

/**
 * 字符串超长替换省略号
 * {{ model.name | ellipsis }}
 */
export function ellipsis(value) {
	if (!value) return "";
	if (value.length > 5) {
		return value.slice(0, 5) + "...";
	}
	return value;
}

export function ellipsis10(value) {
	if (!value) return "";
	if (value.length > 10) {
		return value.slice(0, 10) + "...";
	}
	return value;
}

export function ellipsis30(value) {
	if (!value) return "";
	if (value.length > 30) {
		return value.slice(0, 30) + "...";
	}
	return value;
}

export function ellipsis100(value) {
	if (!value) return "";
	if (value.length > 100) {
		return value.slice(0, 100) + "...";
	}
	return value;
}

// 日期字符串省掉时分秒
export function buildDateString(value) {
	if (!value) return "";
	if (value.length > 10) {
		return value.slice(0, 10);
	}
	return value;
}

// 金钱格式化
export function moneyFormats(value) {
	let a = Number(value);
	let b = a.toLocaleString("zh", { style: "currency", currency: "CNY" });
	return b;
}

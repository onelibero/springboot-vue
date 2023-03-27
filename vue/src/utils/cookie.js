import Cookies from "js-cookie";

// 获取
export function get(key) {
	return Cookies.get(key);
}

// 返回 js 对象
export function getObject(key) {
	return JSON.parse(Cookies.get(key));
}

// 设置 value ---- js 对象
export function setToken(key, value) {
	return Cookies.set(key, value);
}

// 移除
export function remove(key) {
	return Cookies.remove(key);
}

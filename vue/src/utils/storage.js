/**
 * Storage
 * sessionStorage ---- 临时存储，在浏览器打开期间存在，包括页面重新加载
 * localStorage ---- 长期存储，浏览器关闭后，数据依然会一直存在
 */
let ls = window.localStorage;
let ss = window.sessionStorage;
export default {
	// localStorage 操作
	getItemForLs(key) {
		try {
			return JSON.parse(ls.getItem(key));
		} catch (err) {
			return null;
		}
	},
	setItemForLs(key, val) {
		ls.setItem(key, JSON.stringify(val));
	},
	removeItemForLs(key) {
		ls.removeItem(key);
	},
	clearLs() {
		ls.clear();
	},
	keysForLs() {
		return ls.keys();
	},
	// sessionStorage 操作
	getItemForSs(key) {
		try {
			return JSON.parse(ss.getItem(key));
		} catch (err) {
			return null;
		}
	},
	setItemForSs(key, val) {
		ss.setItem(key, JSON.stringify(val));
	},
	removeItemForSs(key) {
		ss.removeItem(key);
	},
	clearForSs() {
		ss.clear();
	},
	keysForSs() {
		return ss.keys();
	},
};

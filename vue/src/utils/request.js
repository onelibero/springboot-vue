/**
 * 封装 axios 工具类
 * 统一封装请求头、参数、错误提示、权限共功能
 */

// 导入模块
import axios from "axios";
import Storage from "@/utils/storage.js";
import * as Cookie from "@/utils/cookie.js";
import { Message } from "element-ui";

// 定义常量
export const OK_CODE = 200;
export const UNAUTHORIZED_CODE = 401;
export const FORBIDDEN_CODE = 403;
export const NOT_FOUND_CODE = 404;
export const INTERNAL_SERVER_ERROR_CODE = 500;
export const TOKEN_KEY = "Token";
export const TOKEN_OBJECT_KEY = "TokenObject";

// 定义全局变量，本机只能本地测试，生产环境不可用
export const domain = "http://127.0.0.1:8081";

// 请求拦截器
axios.interceptors.request.use(
	(config) => {
		// TODO 处理权限相关业务
		return config;
	},
	(error) => {
		// TODO
		console.log(error);
		return Promise.reject(error);
	}
);

// 响应拦截器
axios.interceptors.response.use(
	(response) => {
		return response.data;
		// const res = response.data;
		// if (response.status == FORBIDDEN_CODE || response.status == UNAUTHORIZED_CODE) {
		// 	// 状态码为 401、403，说明 jwt 过期
		// 	// 清除 Storage、Cookie 中的 JWT 令牌，并跳转到登录页面
		// 	Storage.removeItemForLs(TOKEN_KEY);
		// 	Storage.removeItemForLs(TOKEN_OBJECT_KEY);
		// 	window.location.href = "/login";
		// } else if (response.status == NOT_FOUND_CODE || response.status == INTERNAL_SERVER_ERROR_CODE) {
		// 	Message({
		// 		message: res.message || "Error",
		// 		type: "error",
		// 		duration: 5 * 1000,
		// 	});
		// 	return Promise.reject(new Error(res.message || "Error"));
		// } else {
		// 	return res;
		// }
	},
	(error) => {
		console.log("err" + error);
		// if (error.response && (error.response.status == FORBIDDEN_CODE || error.response.status == UNAUTHORIZED_CODE)) {
		// 	// 状态码为 401、403，说明 jwt 过期
		// 	// 清除 Storage、Cookie 中的 JWT 令牌，并跳转到登录页面
		// 	Storage.removeItemForLs(TOKEN_KEY);
		// 	Storage.removeItemForLs(TOKEN_OBJECT_KEY);
		// 	window.location.href = "/login";
		// }
		// Message({
		// 	message: error.message,
		// 	type: "error",
		// 	duration: 5 * 1000,
		// });
		return Promise.reject(error);
	}
);

/**
 * 封装 get 请求
 * @param {*} url
 * @param {*} params 查询参数
 * @returns
 */
export function fetch(url, params = {}) {
	// const token = Cookies.get(TOKEN_KEY) === undefined ? "11111" : Cookies.get(TOKEN_KEY);
	const token = Storage.getItemForLs(TOKEN_KEY);
	return axios({
		headers: {
			"Content-Type": "application/json;charset=UTF-8",
			Token: token,
		},
		url: domain + url,
		method: "get",
		params: params,
	});
}

/**
 * 封装非 get 请求
 * @param {*} url
 * @param {*} type 请求类型
 * @param {*} data js 对象
 * @returns
 */
export function fetch_(url, type = "post", data = {}) {
	// const token = Cookies.get(TOKEN_KEY) === undefined ? "11111" : Cookies.get(TOKEN_KEY);
	const token = Storage.getItemForLs(TOKEN_KEY);
	return axios({
		headers: {
			"Content-Type": "application/json;charset=UTF-8",
			Token: token,
		},
		url: domain + url,
		method: type,
		data: JSON.stringify(data),
	});
}

// 获取 Cookies
export function getCookies(key) {
	return Cookies.get(key) || "";
}

// 设置 Cookies
export function setCookies(key, value) {
	Cookies.set(key, value);
}

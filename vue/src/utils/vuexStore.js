// 引入 Vue、Vuex
import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);
import Storage from "@/utils/storage.js";
//import * as Cookie from "@/utils/cookie.js";

//const TOKEN_KEY = "Token";
const TOKEN_OBJECT_KEY = "TokenObject";

// 导出 store 实例
export default new Vuex.Store({
	/**
	 * 设置一个全局访问的 state 对象
	 * collapse：sidebar 是否压缩显示
	 * token：登录凭证信息，当前用户
	 * 取值：this.$VuexStore.state.collapse
	 */
	state: {
		collapse: false,
		/**
		 * JS 代码是运行在内存中的，页面刷新时会重新加载 JS 脚本，变量重新赋值
		 * 登录信息存在 localStorage 中永久保存，重新加载脚本时从 localStorage 获取 token 信息
		 */
		token: Storage.getItemForLs(TOKEN_OBJECT_KEY) || "",
	},
	/**
	 * 实时监控 state 值的变化，并包装数据以函数形式返回
	 * 取值：this.$VuexStore.getters.isLogin
	 */
	getters: {
		// 判断用户是否登录
		isLogin: function (state) {
			return state.token != "";
		},
	},
	/**
	 * 操作 state 数据的方法的集合
	 * 默认形参 ---- (state, payload)
	 * state ---- 对应上面定义的 state 对象
	 * payload ---- 挂载的参数，如果是多个参数封装为一个 js 对象，可选
	 * 调用 ---- this.$VuexStore.commit("changeCollapse", this.collapse);
	 */
	mutations: {
		// 变更 collapse 值
		changeCollapse: function (state, payload) {
			state.collapse = payload;
		},
		// 变更 token 值
		setToken: function (state, payload) {
			if (payload == "") {
				Storage.removeItemForLs(TOKEN_OBJECT_KEY);
				state.token = "";
			} else {
				Storage.setItemForLs(TOKEN_OBJECT_KEY, payload);
				state.token = payload;
			}
		},
	},
});

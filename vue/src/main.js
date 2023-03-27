import Vue from 'vue'
import App from './App.vue'
// 引入 vue-router 及路由配置
import Router from "vue-router";
import router from "./router";
Vue.use(Router);
// 引入 ElementUI 和主题样式
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.use(ElementUI);
// EChart 组件
import * as echarts from "echarts";
Vue.prototype.$echarts = echarts;
Vue.config.productionTip = false;

// Bus，用于组件间传递消息
import Bus from "@/utils/bus.js";
Vue.prototype.$Bus = Bus;
// Cookie 组件
import * as Cookie from "@/utils/cookie.js";
Vue.prototype.$Cookie = Cookie;
// Storage 组件
import Storage from "@/utils/storage.js";
Vue.prototype.$Storage = Storage;
// Vuex Store 组件
import VuexStore from "@/utils/vuexStore.js";
Vue.prototype.$VuexStore = VuexStore;
// Axios Request 组件
import * as Request from "@/utils/request";
Vue.prototype.$Request = Request;
// 注册过滤器组件
import * as Filters from "@/utils/filter.js";
Object.keys(Filters).forEach((key) => {
	Vue.filter(key, Filters[key]);
});
// Common 组件
import * as Common from "@/utils/common.js";
Vue.prototype.$Common = Common;
// TestData 组件
import * as TestData from "@/utils/testData.js";
Vue.prototype.$TestData = TestData;



// 使用 router.beforeEach 注册一个全局前置守卫，进行登录验证
router.beforeEach((to, from, next) => {
	if (to.meta.requireAuth) {
		if (VuexStore.getters.isLogin) {
			next();
		} else {
			next("/login");
		}
	} else {
		next();
	}
});



new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

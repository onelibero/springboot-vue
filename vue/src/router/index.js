import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
	mode: "history", // history 模式去除地址栏 # 号
	routes: [
		{
			// http://127.0.0.1:8081/login
			path: "/login",
			name: "登录",
			component: () => import("@/components/Login.vue"),
			meta: { requireAuth: false },
		},
		{
			// http://127.0.0.1:8080/
			path: "/",
			name: "主页",
			component: () => import("@/components/Comic/Index.vue"),
            meta: { requireAuth: false },
		},
		{
			// http://127.0.0.1:8080/account
			path: "/detail",
			name: "动漫详情",
			component: () => import("@/components/Comic/ComicDetail.vue"),
            meta: { requireAuth: true },
		},
		{
			// http://127.0.0.1:8080/resource
			path: "/user",
			name: "个人中心",
			component: () => import("@/components/User.vue"),
			redirect: "/user/setting",
			// 二级路由，渲染到该组件中的 <router-view />
			children: [
				{
					// http://127.0.0.1:8080/resource/films
					path: "/user/setting",
					name: "个人详情",
					component: () => import("@/components/User/Detail.vue"),
					meta: { requireAuth: true },
				},
				{
					path: "/user/update",
					name: "个人设置",
					component: () => import("@/components/User/Update.vue"),
					meta: { requireAuth: true },
				}
			],
		},
		{
			// http://127.0.0.1:8080/test
			path: "/history",
			name: "动漫管理",
			component: () => import("@/components/User.vue"),
			redirect: "/comic/like",
			// 二级路由，渲染到该组件中的 <router-vi@/components/User/Update.vue
			children: [
				{
					// http://127.0.0.1:8080/test/helloworld1
					path: "/comic/like",
					name: "点赞",
					component: () => import("@/components/User/Like.vue"),
					meta: { requireAuth: true}
				},
				{
					// http://127.0.0.1:8080/test/helloworld2
					path: "/comic/record",
					name: "浏览记录",
					component: () => import("@/components/User/Record.vue"),
					meta: { requireAuth: true },
				},
			],
		},
		{
			// http://127.0.0.1:8080/test
			path: "/permission",
			name: "管理员",
			component: () => import("@/components/Permission.vue"),
			redirect: "/permission/user",
			// 二级路由，渲染到该组件中的 <router-vi@/components/User/Update.vue
			children: [
				{
					// http://127.0.0.1:8080/test/helloworld1
					path: "/permission/user",
					name: "用户管理",
					component: () => import("@/components/Comic/User.vue"),
					meta: { requireAuth: true}
				},
				{
					// http://127.0.0.1:8080/test/helloworld2
					path: "/permission/comic",
					name: "动漫管理",
					component: () => import("@/components/Comic/Comic.vue"),
					meta: { requireAuth: true },
				},
				
			],
		},
	],
});

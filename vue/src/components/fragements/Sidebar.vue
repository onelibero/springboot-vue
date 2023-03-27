<template>
	<el-row class="tac">
		<el-col>
			<!-- 
				:default-active="this.$route.path" ---- 当前路由为默认选中
				也可采用 selectMenu 函数渲染选中
			-->
			<el-menu
				:default-active="this.$route.path"
				:collapse="this.$VuexStore.state.collapse"
				@open="handleOpen"
				@close="handleClose"
				@select="selectMenu"
				background-color="#d3dce6"
				text-color="#112e49"
				active-text-color="#575258"
				unique-opened
				router
				ref="menu"
			>
				<template v-for="item in menus">
					<template v-if="item.subItems && item.subItems.length > 0">
						<el-submenu :index="item.path" :key="item.index">
							<template slot="title">
								<i :class="item.icon"></i>
								<span slot="title">{{ item.title }}</span>
							</template>
							<el-menu-item
								class="vue-sideMenu"
								v-for="(subItem, i) in item.subItems"
								:index="subItem.path"
								:key="i"
							>
								{{ subItem.title }}
							</el-menu-item>
						</el-submenu>
					</template>
					<template v-else>
						<el-menu-item class="vue-sideMenu" :index="item.path" :key="item.index">
							<i :class="item.icon"></i>
							<span slot="title">{{ item.title }}</span>
						</el-menu-item>
					</template>
				</template>
			</el-menu>
		</el-col>
	</el-row>
</template>
<script>
export default {
	name: "Sidebar",
	props: {
		menus: {
			type: Array,
		},
	},
	data() {
		return {
			 //menus: this.$TestData.navigationData,
		};
	},
	methods: {
		// 展开菜单
		handleOpen: function (key, keyPath) {
			console.log(key, keyPath);
		},
		// 关闭菜单
		handleClose: function (key, keyPath) {
			console.log(key, keyPath);
		},
		// 选择菜单
		selectMenu: function (index, indexPath) {
			// 设置选中
			// this.$refs.menu.activeIndex = index;
		},
	},
};
</script>
<style>
.el-menu {
	height: 100%;
	border: none;
}
/** 只设置左边菜单选中样式，不影响头部菜单 */
.vue-sideMenu.is-active {
	border-right: 3px solid #459f75;
	background-color: #9eaaad !important;
}
/** 覆盖掉 app 样式，设置靠左对齐 */
.el-menu-item,
.el-submenu {
	text-align: left;
}
</style>

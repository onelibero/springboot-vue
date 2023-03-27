<template>
	<!-- 
		default-active="1" ---- 设定默认选中菜单
		:default-active="this.$route.path" ---- 当前路由为默认默认选中
	-->
	<h1>sss</h1>
</template>
<script>
export default {
	name: "Header",
	props: {
		user: {
			type: Object,
		},
	},
	data() {
		return {
			collapse: false,
		};
	},
	methods: {
		handleSelect(key, keyPath) {
			console.log(key, keyPath);
		},
		collapseCharge() {
			this.collapse = !this.collapse;
			// 通过 VuexStore 改变 collapse 状态，在 layout 和 sideBar 中取值
			this.$VuexStore.commit("changeCollapse", this.collapse);
			console.log(this.$VuexStore.state.collapse);
		},
		logout() {
			// 没有后台方法，由前端清除 token
			this.$VuexStore.commit("setToken", "");
			this.$router.push("/login");
		},
	},
};
</script>
<style>

</style>

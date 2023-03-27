<template>
	<div v-if="pageInfo.total > 0" class="vue-pagination">
		<el-pagination
			:page-sizes="[5, 20, 50, 100]"
			:page-size="pageInfo.pageSize"
			:total="pageInfo.total"
			:current-page.sync="pageInfo.pageNum"
			layout="total, sizes, prev, pager, next, jumper"
			@size-change="changePageSize"
			@current-change="changeCurrentPage"
		>
		</el-pagination>
	</div>
</template>

<script>
export default {
	name: "Pagination",
	// 使用 props 属性接收父组件传递的参数
	props: {
		pageInfo: {
			type: Object,
			default: {
				total: 5, // 总数据量
				pageNum: 1, // 当前页
				pageSize: 5, // 页长
				pages: 1, // 总页数
			},
		},
	},
	methods: {
		changePageSize: function (obj) {
			// 改变页长，通过 $emit 函数，将页长传递给父组件
			this.$emit("emitPageData", {
				pageSize: obj,
			});
		},
		changeCurrentPage: function (obj) {
			// 改变当前页，通过 $emit 函数，将当前页传递给父组件
			this.$emit("emitPageData", {
				pageSize: this.pageInfo.pageSize,
				pageNum: obj,
			});
		},
	},
};
</script>

<style>
.vue-pagination {
	width: 100%;
	margin-top: 5px;
	float: left
}
</style>

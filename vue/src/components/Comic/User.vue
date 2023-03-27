<template>
	<div class="table-div">
		<!-- 表格头部面板 -->
		<div class="table-panel">
			<!-- 
                @keyup.native ---- 键盘松开事件
				@keyup.enter.native ---- 回车事件
             -->
			<el-input
				v-model="search.keyword"
				placeholder="关键字查询"
				class="filter-item"
				@keyup.enter.native="initTableData"
				@keyup.native="initTableData"
			/>
			
		</div>

		<!-- 
            表格
            @row-click ---- 行点击事件
			@select-all ---- 全选事件
			@select ---- 选择事件
            :default-sort="{ prop: 'id', order: 'descending' }"
        -->
		<el-table
			ref="list"
			:data="pageInfo.list"
			style="width: 100%; margin-top: 0px"
			border
			stripe
			highlight-current-row
			@sort-change="changeSort"
		>
			<!-- 
                sortable ---- 未开启后台分页时使用
                sortable="custom" ---- 开启后台分页时使用，table 中用 sort-change 监听排序点击事件
            -->
			<el-table-column property="id" label="ID" width="80" align="center" sortable="custom"></el-table-column>
			<el-table-column property="userName" label="用户名" align="center" sortable="custom"></el-table-column>
			<el-table-column property="userImage" label="头像" align="center" sortable="custom">
				<template slot-scope="scope">
					<img class="userIcon" v-if="scope.row.avatar" :src="scope.row.avatar" />
					<img class="userIcon" v-else src="url" />
				</template>
			</el-table-column>
			<el-table-column property="password" label="密码" align="center" sortable="custom">
				<template slot-scope="scope">
					{{ scope.row.password | ellipsis }}
				</template>
			</el-table-column>
			<el-table-column property="createDate" label="创建时间" width="220" align="center" sortable="custom">
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 5px">{{ scope.row.createTime | buildDateString }}</span>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="130" align="center">
				<template slot-scope="scope">
					<el-button
						circle
						icon="el-icon-edit-outline"
						type="primary"
						title="编辑"
						size="small"
						@click="editModel(scope.row)"
					></el-button>
					<el-button
						circle
						icon="el-icon-delete"
						type="danger"
						title="删除"
						size="small"
						@click="deleteModel(scope.row)"
					></el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--
			父子数据传递：:pageInfo="pageInfo"
			子到父数据传递：子发射数据，并制定父接收的key，父指定接收key，以及其接收函数
		-->
		<Pagination :pageInfo="pageInfo" @emitPageData="receivePageData" />

		<UserEdit :dialogData="dialogData" @emitDialogData="receiveDialogData" />
	</div>
</template>

<script>
import Pagination from "@/components/fragements/Pagination.vue";
import UserEdit from "@/components/Comic/UserEdit.vue";

export default {
	name: "Users",
	components: {
		Pagination,
		UserEdit,
	},
	data() {
		return {
			url:"https://tse1-mm.cn.bing.net/th/id/OIP-C.l9T-NQJt5y8YF4iMTcbuSgAAAA?w=148&h=150&c=7&r=0&o=5&dpr=1.3&pid=1.7",
			// 查询参数
			search: {
				currentPage: 1,
				pageSize: 5,
				keyword: "",
				sort: "id",
				direction: "desc",
			},
			// 表格数据结构，参考 PageInfo
			pageInfo: {
				total: 4, // 总数据量
				pageNum: 1, // 当前页
				pageSize: 5, // 页长
				pages: 1, // 总页数
				list: [], // 当前页数据集合
			},
			dialogData: {
				id: 0,
				showEditDialog: false,
			},
		};
	},
	methods: {
		// 初始化表格数据
		initTableData: function () {
			var self = this;
			this.$Request
				.fetch_("/user/userList", "post", self.search)
				.then((result) => {
					console.log(result)
					self.pageInfo = result;
					self.pageInfo.list.map((item) => {
						if (item.avatar) {
							item.avatar = this.$Request.domain + item.avatar;
						}
						return item;
					});
				})
				.catch((error) => {
					this.$message.error(error);
				});
		},
		// 列排序事件
		changeSort: function (obj) {
			// 驼峰转下划线
			this.search.sort = this.$Common.camecaseToLine(obj.prop);
			this.search.direction = obj.order == "descending" ? "desc" : "asc";
			this.initTableData();
		},
		// 接收分页组件 $emit 函数传递的值
		receivePageData: function (pageInfo) {
			this.search.currentPage = pageInfo.pageNum;
			this.search.pageSize = pageInfo.pageSize;
			this.initTableData();
		},
		// 接收编辑组件 $emit 函数传递的值
		receiveDialogData: function (showEditDialog) {
			this.dialogData.showEditDialog = showEditDialog;
			if (this.dialogData.id == 0) {
				this.search.currentPage = 1;
				this.pageInfo.pageNum = 1;
			} else {
				this.search.currentPage = this.pageInfo.pageNum;
			}
			this.initTableData();
		},
		// 新增模型
		addModel: function () {
			this.dialogData = {
				id: 0,
				showEditDialog: true,
			};
		},
		// 修改模型
		editModel: function (row) {
			this.dialogData = {
				id: row.id,
				showEditDialog: true,
			};
		},
		// 删除模型
		deleteModel: function (row) {
			var self = this;
			this.$confirm("确定要删除当前行吗？", "删除", {
				comfirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			})
				.then(() => {
					var url = "/user/deleteUser/" + row.id;
					this.$Request
						.fetch_(url, "delete", {})
						.then((result) => {
							if (result.code == this.$Request.OK_CODE) {
								this.$message({
									message: "删除成功",
									type: "success",
									duration: 2 * 1000,
								});
								self.search.currentPage = 1;
								this.initTableData();
							} else {
								this.$message.error(result.message);
							}
						})
						.catch((error) => {
							this.$message.error(error);
						});
				})
				.catch(() => {});
		},
	},
	mounted() {
		
		this.initTableData();
	},
};
</script>
<style>
.userIcon {
	width: 40px;
	height: 40px;
}
.table-div {
	margin-top: 10px;
}
.filter-item {
	float: left;
	margin-left: 80%;
	width: 150px;
	display: inline-block;
	vertical-align: middle;

	/* margin-right: 5px; */
}
.button-item {
	float: left;
	width: 100px;
	display: inline-block;
	vertical-align: middle;

	margin-right: 5px;
}
</style>

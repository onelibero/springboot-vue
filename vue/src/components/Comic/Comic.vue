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
			<el-button class="button-item" type="primary" icon="el-icon-edit" @click="addModel()">新增</el-button>
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
			style="width: 100%; margin-top: 10px"
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
			<el-table-column property="comicName" label="动漫名" align="center" sortable="custom"></el-table-column>
            <el-table-column property="nickName" label="昵称" align="center" sortable="custom"></el-table-column>
			<el-table-column property="cover" label="封面" align="center" sortable="custom">
				<template slot-scope="scope">
					<img class="userIcon" v-if="scope.row.cover" :src="scope.row.cover" />
					<img class="userIcon" v-else src="/images/default.jpg" />
				</template>
			</el-table-column>
			<el-table-column property="region" label="地区" align="center" sortable="custom"></el-table-column>
            <el-table-column property="label" label="标签" align="center" sortable="custom"></el-table-column>
            <el-table-column property="description" label="描述" align="center" sortable="custom">
                <!-- 设置表述文本框的大小 -->
            </el-table-column>
            <el-table-column property="remark" label="完结？" align="center" sortable="custom"></el-table-column>
            <el-table-column property="year" label="上映年" align="center" sortable="custom"></el-table-column>
            <el-table-column property="updateTime" label="最新更新" align="center" sortable="custom"></el-table-column>
            <el-table-column property="number" label="章节" align="center" sortable="custom"></el-table-column>
            <el-table-column property="popularity" label="热度" align="center" sortable="custom"></el-table-column>
            <el-table-column property="url" label="链接" align="center" sortable="custom"></el-table-column>
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

		<ComicEdit :dialogData="dialogData" @emitDialogData="receiveDialogData" />
	</div>
</template>

<script>
import Pagination from "@/components/fragements/Pagination.vue";
import ComicEdit from "@/components/Comic/ComicEdit.vue";

export default {
	name: "Comic",
	components: {
		Pagination, //分页
		ComicEdit, //修改
	},
	data() {
		return {
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
				.fetch_("/comic/getComicBySearch", "post", self.search) //后端url 分页查询
				.then((result) => {
                    console.log(result)
					self.pageInfo = result;
					self.pageInfo.list.map((item) => {
						if (item.cover) {
							item.cover = this.$Request.domain + item.cover;
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
					var url = "/comic/delete/" + row.id;  //删除
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
        console.log("ssss")
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
	float: right;
	width: 200px;
	display: inline-block;
	vertical-align: middle;
	margin-bottom: 10px;
	/* margin-right: 5px; */
}
.button-item {
	float: left;
	width: 100px;
	display: inline-block;
	vertical-align: middle;
	margin-bottom: 10px;
	margin-right: 5px;
}
</style>

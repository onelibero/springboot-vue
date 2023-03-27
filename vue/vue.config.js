const { defineConfig } = require("@vue/cli-service");
const webpack = require("webpack");
module.exports = defineConfig({
	transpileDependencies: true,
	// 关闭语法检查
	lintOnSave: false,
	devServer: {
		port: 8080,
	},
	configureWebpack: {
		plugins: [
			new webpack.ProvidePlugin({
				$: "jquery",
				jQuery: "jquery",
				"windows.jQuery": "jquery",
			}),
		],
	},
});

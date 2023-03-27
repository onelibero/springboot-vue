<template>
	<div class="pull-right">
		{{ webSiteInfo.copyright }}
		<a href="https://beian.miit.gov.cn" style="color: #0056b3" target="_blank">{{ webSiteInfo.icp }}</a>
	</div>
</template>

<script>
export default {
	name: "Footer",
	props: {
		webSiteInfo: {
			type: Object,
		},
	},
	data() {
		return {
			// webSiteInfo: this.$TestData.webSiteInfo,
		};
	},
};
</script>

<style></style>

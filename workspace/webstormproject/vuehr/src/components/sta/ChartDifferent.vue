<template>
    <div>
        <el-row style="margin-top: 5px;">
            <el-col :span="7" v-for="(item, index) in dataList" :key="index" :offset="1">
                <el-card :body-style="{ padding: '7px' }">
                    <div style="margin-bottom: 5px;display: flex;justify-content: space-between">
                        <span>{{item.goodName}}</span><span>{{item.diff}}</span>
                    </div>
                    <div style="display: flex;justify-content: space-between">
                        <!--max-->
                        <div style="margin-left: 20px">
                            <el-image
                                    style="width: 100px; height: 100px"
                                    :src="item.max.imgUrl"
                                    fit="fill">
                                <div slot="error" class="image-slot"
                                     style="display: flex;justify-content: center ;align-items: center;">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                            <div>
                                <div style="width: 160px">
                                    <a :href="item.max.detailUrl" target="_blank" style="font-size: 10px">{{item.max.title}}</a>
                                </div>

                                <div>
                                    <span style="font-size: 10px">店铺:{{item.max.shop}}</span>
                                </div>
                                <div>
                                    <span style="font-family: 黑体">价格: {{item.max.price}}元</span>
                                </div>
                            </div>
                        </div>
                        <!--min-->
                        <div style="margin-right: 20px">
                            <el-image
                                    style="width: 100px; height: 100px"
                                    :src="item.min.imgUrl"
                                    fit="fill">
                                <div slot="error" class="image-slot"
                                     style="display: flex;justify-content: center ;align-items: center;">
                                    <i class="el-icon-picture-outline"></i>
                                </div>
                            </el-image>
                            <div>
                                <div style="width: 160px">
                                    <a :href="item.min.detailUrl" target="_blank" style="font-size: 10px">{{item.min.title}}</a>
                                </div>

                                <div>
                                    <span style="font-size: 10px">店铺:{{item.min.shop}}</span>
                                </div>
                                <div>
                                    <span style="font-family: 黑体">价格: {{item.min.price}}元</span>
                                </div>
                            </div>
                        </div>
                        <!--other max min-->
                        <div>
                            <div style="width: 100px;">
                                <div v-if="item.otherMax != null">
                                    <!--bug!!! v-for不加冒号 否则检测x 为data中的数值未定义-->
                                    <div v-for="(x, index) in item.otherMax">
                                        <div>
                                            <span style="font-size: 10px">店铺:{{x.shop}}</span>
                                        </div>
                                        <div>
                                            <span style="font-family: 黑体"><a :href="x.detailUrl">价格: {{x.price}}元</a></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="width: 100px;">
                                <div v-if="item.otherMin != null">
                                    <!--bug!!! v-for不加冒号 否则检测x 为data中的数值未定义-->
                                    <div v-for="(x, index) in item.otherMin">
                                        <div>
                                            <span style="font-size: 10px">店铺:{{x.shop}}</span>
                                        </div>
                                        <div>
                                            <span style="font-family: 黑体"><a :href="x.detailUrl">价格: {{x.price}}元</a></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "ChartDifferent",
        data() {
            return {
                dataList: [],
                otherMaxList: [],
                otherMinList: []
            }
        },
        mounted() {
            this.initData()
        },
        watch: {},
        methods: {
            initData() {
                this.getRequest('/statistics/chart/maxAndMin').then(resp => {
                    if (resp) {
                        this.dataList = resp.data
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
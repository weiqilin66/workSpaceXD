<template>
    <div>
        <el-row style="margin-top: 5px;">
            <el-col :span="7" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 1 : 0">
                <el-card :body-style="{ padding: '7px' }">
                    <div style="display: flex;justify-content: space-between">
                        <div style="margin-left: 20px">
                            <el-image
                                    style="width: 200px; height: 200px"
                                    src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                                    fit="fill"/>

                            <div style="padding: 14px;">
                                <span>好吃的汉堡</span>
                                <div class="bottom clearfix">
                                    <time class="time">{{ currentDate }}</time>
                                    <el-button type="text" class="button">操作按钮</el-button>
                                </div>
                            </div>
                        </div>
                        <div style="margin-right: 20px">
                            <el-image
                                    style="width: 200px; height: 200px"
                                    src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                                    fit="fill"/>
                            <div style="padding: 14px;">
                                <span>好吃的汉堡</span>
                                <div class="bottom clearfix">
                                    <time class="time">{{ currentDate }}</time>
                                    <el-button type="text" class="button">操作按钮</el-button>
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
        name: "ChartDifferent"
    }
</script>

<style scoped>

</style>
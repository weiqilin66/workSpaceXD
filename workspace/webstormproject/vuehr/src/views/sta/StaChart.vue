<template>
    <div>
        <!--标签页样式-->
        <el-tabs v-model="activeName" type="card" >
            <el-tab-pane label="差价" name="ChartDif"><ChartDifferent></ChartDifferent></el-tab-pane>
            <el-tab-pane label="设计中" name="table2"><ChartDifferent></ChartDifferent></el-tab-pane>

        </el-tabs>
    </div>
</template>

<script>
    import ChartDifferent from "../../components/sta/ChartDifferent";
    export default {
        name: "StaScore",
        data(){
            return{
                activeName: 'ChartDif'
            }
        },
        components:{
            ChartDifferent
        }
    }
</script>

<style scoped>

</style>
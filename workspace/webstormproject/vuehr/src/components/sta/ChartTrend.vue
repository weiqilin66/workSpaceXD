<template>
    <div>
        <div style="display: flex;justify-content: center;margin-bottom: 5px;margin-right: 170px">

            <div style="margin-right: 5px">
                <el-radio-group v-model="days">
                    <el-radio :label="7">近7天</el-radio>
                    <el-radio :label="15">半月</el-radio>
                    <el-radio :label="30">整月</el-radio>
                </el-radio-group>
            </div>
            <div>
                <el-input placeholder="关键字...机型 游戏主名 游戏附名" v-model="kw" style="width: 400px;margin-right: 5px"
                          @keydown.enter.native="doSearch(kw)"/>
                <el-button type="primary" icon="el-icon-search" @click="changeChart2(kw)">搜索</el-button>
            </div>
        </div>
        <div style="margin-bottom: 100px;display: flex;justify-content: center;flex-wrap: wrap;width: 90%">

            <el-button v-for="(item,index) in btnList" :type="item.type" :key="index"
                       style="margin-top: 3px" @click="changeChart2(item.name)" plain>{{item.name}}
            </el-button>
        </div>

        <div id="echarts" ref="echarts"></div>
    </div>

</template>
<style scoped>
    #echarts {
        height: 900px;
    }
</style>
<script>
    export default {
        name: "ChartTrend",
        data() {
            return {
                kw: '',
                days: 7,
                etlDate: [],
                btnList: [],//快捷按钮列表
                chartData: {
                    // 配置标题
                    title: {
                        text: '欢迎帅奇'
                    },
                    // 提示
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        }
                    },
                    // 图例
                    legend: {
                        data: ['宁波老猎人电玩']
                    },
                    // 工具(下载...)
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    // 位置
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [//横坐标
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: ['周一','周二','周三']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                        }
                    ],
                    series: [
                        {
                            name: '宁波老猎人电玩',//toolTip提示使用
                            type: 'line',// 类型 折线图
                            areaStyle: {}, //填充颜色
                            label: {    // 显示数据
                                normal: {
                                    show: false,
                                    position: 'top'
                                }
                            },
                            data: [0,220,0]
                        }
                    ]
                }
            }
        },
        mounted() {
            this.getKw()
            this.drawLine()
        },
        methods: {
            drawLine() {
                // 基于准备好的dom，初始化echarts实例
                // let myChart = this.echarts.init(document.getElementById('echarts'));
                let myChart = this.echarts.init(this.$refs['echarts']);
                let option = this.chartData
                myChart.setOption(option);
            },
            doSearch(kw) {
                this.changeChart2(kw)
            },
            changeChart2(kw) {
                this.getXDate()
                // 横坐标日期
                this.chartData.xAxis[0].data = this.etlDate
                this.chartData.title['text'] = kw
                this.getRequest('/statistics/chart/byTitle?title=' + kw + '&date=' + this.days).then(resp => {
                    if (resp) {
                        if (resp.error != null) {//错误检索规则报错
                            this.$message.error(resp.error);
                            this.kw = ''
                            return
                        }
                        // 图例
                        const shops = resp.shops
                        this.chartData.legend.data = shops
                        this.chartData.series=[]
                        shops.forEach(shop=>{
                            let sery = {
                                name: shop,
                                type: 'line',
                                areaStyle: {},
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'top'
                                    }
                                },

                                data: []
                            }
                            // 变量作为属性名使用 由 . 改成 []
                            sery.data=resp[shop]
                            this.chartData.series.push(sery)
                            console.log(sery);
                        })

                        this.drawLine()//重新绘制图表加载数据
                    }
                })
            },
            // 获取 x轴的日期数组
            getYyyyMMdd(d) {
                var curr_date = d.getDate();
                var curr_month = d.getMonth() + 1;
                var curr_year = d.getFullYear();
                String(curr_month).length < 2 ? (curr_month = "0" + curr_month) : curr_month;
                String(curr_date).length < 2 ? (curr_date = "0" + curr_date) : curr_date;
                var yyyyMMdd = curr_year + "" + curr_month + "" + curr_date;
                return yyyyMMdd;
            },
            // 获取 x轴的日期数组
            getXDate(num) {
                this.etlDate = []
                if (this.days === 0) {
                    num = 15
                } else {
                    num = this.days
                }
                for (let i = 0; i < num; i++) {
                    const start = new Date();
                    start.setDate(start.getDate() - i);
                    this.etlDate.push(this.getYyyyMMdd(start))
                }
            },
            getKw() {
                this.getRequest("/statistics/chart/goodList").then(resp => {
                    if (resp) {
                        this.btnList = resp.data
                    }
                })
            }
            /*
                        async changeChart(kw) {
                            this.etlDate = []
                            if (this.selDate.length === 0) {//默认近15天数据

                                for (let i=0;i<15;i++){
                                    const start = new Date();
                                    start.setDate(start.getDate() -i);
                                    this.etlDate.push(this.getyyyyMMdd(start))
                                }
                                console.log('etl日期区间:'+this.etlDate);
                            }else{//选择日期
                                const start = this.selDate[0]
                                const end =this.selDate[1]
                                const res = end -start
                                console.log('日期区间: '+res);
                                const nextDay = start.setDate(start.getDate() -1);

                                if(start === end){
                                    this.etlDate=this.selDate[0]
                                }else {
                                    this.etlDate.push(start)
                                    if (nextDay!==end){//下一天不是结束日
                                        this.etlDate.push(nextDay)
                                    }
                                    this.etlDate.push(end)
                                }
                            }


                            // 横坐标日期
                            this.chartData.xAxis[0].data = this.etlDate
                            let x = 0
                            // 初始化
                            const resp = await Axios.get('/statistics/chart/init?title=' + kw + '&date=' +  this.etlDate[0])
                            this.initChartAsync(kw, resp)
                            // 遍历数据
                            for (const date of this.etlDate) {
                                if(x===0){
                                    x++
                                    continue
                                }
                                console.log('数据日期:'+date);
                                const resp2 = await Axios.get('/statistics/chart/init?title=' + kw + '&date=' + date)
                                console.log('数据:'+resp2);
                                this.getNextDataAsync(resp2)
                            }

                            this.drawLine()
                            // 异步请求 等待3秒再绘图
                            // setTimeout(this.drawLine(),3000)

                        },
            */

            /*
                    initChartAsync(kw, resp) {
                        if (resp) {
                            console.log('初始化数据:'+resp)
                            this.dataList = resp
                            // console.log(this.dataList);
                            // 初始化数据
                            this.chartData.series = []
                            // 店铺图例
                            let legendShop = []
                            // 图标样式
                            this.chartData.yAxis = [{
                                type: 'value',
                                // max: 400,
                                // min: 200,
                            }]

                            // 标题
                            this.chartData.title['text'] = kw

                            this.dataList.forEach(good => {
                                legendShop.push(good['shop'])
                                let sery = {
                                    name: good['shop'],
                                    type: 'line',
                                    areaStyle: {},
                                    label: {
                                        normal: {
                                            show: true,
                                            position: 'top'
                                        }
                                    },
                                    data: [good['price']]
                                }
                                this.chartData.series.push(sery)
                            })
                            this.chartData.legend.data = legendShop

                        }
                    },
        */
            /*getNextDataAsync(resp) {
                if (resp) {
                    let x = 0
                    resp.forEach(good => {
                        this.chartData.series[x].data.push(good['price'])
                        x++
                    })

                }

            },*/
        },

    }
</script>


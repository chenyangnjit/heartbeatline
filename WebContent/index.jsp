<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
 
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
     <style type="text/css">
           #container5
           {
               height: 300px;
               width: 660px;
                  margin-top:20px;
           }
      </style>

      <title>智慧健康系统实时心率监测曲线</title>   
		<!-- 引用相应的javascipt类库，相应的类库存放在本地js文件夹下-->
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/highcharts.js"></script>  
        <script type="text/javascript">
            $(function () {
                $(document).ready(function () {
                    Highcharts.setOptions(    //设置全局参数，如颜色方案
                    {
                    global:
                        {
                            useUTC: false
                        }
                });

                var chart; //container为盛放chart的容器
                chart = new Highcharts.Chart({
                    chart:   //chart 属性设置
                        {
						type:'spline',
                        renderTo: 'container', //盛放图表的区域
                        animation: Highcharts.svg, // don't animate in old IE
                        marginRight: 10, //标题与图标之间的距离
                        borderWidth: 3,
                        plotBorderWidth: 2,
                        zoomType: 'xy',
                        events:
                            {
                                load: function () {

                                    // set up the updating of the chart each second
                                    var series0 = this.series[0];
                                    //var series1 = this.series[1];
                                    setInterval(function () {
                                        //var randomCode = parseInt(Math.random() * 10000);
                                        //var oldrandomCode = parseInt(Math.random() * 10000);
                                        $.ajax({        //异步调用获取展厅数据
                                            url: "bar.do",
                                            type: "post",
                                            dataType: "json",
                                            success: function (data) {
                                                var x = (new Date()).getTime(), // current time
                                        		y = data[0].data; //设置y轴的数据
                                       			// alert(data[0].data);  //测试是否有数据输出

                                                series0.addPoint([x, y], true, true); //表示实时更新，并移除最早的点
                                                // series1.addPoint([x, 35], true, true);
                                            },
                                            error: function () { }
                                        });

                                    }, 1000); //1s更新一次数据
                                }
                            }
                    },

                    title:
                        {
                    		text: '智慧健康系统实时心率监测曲线',
                        	x: -20 //center
                        },



                    xAxis:  //设置x轴属性                   
                        {
                        title:
                            {
                                text: '心率测量时间（年(yy)-月(mm)-日(dd) 小时(h)：分钟(min)：秒(sec)）'
                            },
                        type: 'datetime', //x轴数据类型
                        tickPixelInterval: 150,
                        gridLineWidth: 1
                    },


                    yAxis:  //设置y数轴属性
                        {
                        title:
                            {
                                text: '心跳(次/分)'
                            },
                        gridLineWidth: 1,
                        plotLines: [{ value: 0, width: 1, color: '#7D26CD'}]
                    },


                    tooltip: //Tooltip用于设置当鼠标滑向数据点时显示的提示框信息。时间加温度数据
                        {
                        //backgroundColor: 'Lime',
                        valueSuffix: '次/分',
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2);
                        }
                    },


                    legend: //设置图例
                        {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        borderColor: '#CD2990',
                        borderWidth: 5
                    },
					

                    exporting: //是否保存图片
                        {
                        enabled: true
                    },

                    credits:
                    {
                        //enabled: true     //去掉highcharts网站url 
 						text:'南邮教育部工程中心智慧健康系统',               // 显示的文字
 						href:'http://10.10.22.89/smarthealth',   // 链接地址
 						style: {                            // 样式设置
 						cursor: 'pointer',
 						color: 'blue',
 						fontSize: '13px'
 					}					   
                    },


                    series:
                    [                   //第一条曲线的数据集合 
                        {
                        type: 'spline',
                        lineWidth: 5,
                        name: '心率曲线-次/min',
                        data: (function () {
                            // generate an array of random data
                            var data = [],

                        time = (new Date()).getTime(),
                        i;

                            for (i = -19; i <= 0; i++) {
                                data.push(      //push方法向数组的尾部添加数据                          
                                    {
                                    x: time + i * 1000,     //从-19开始是为了让x轴的时间精确到秒
                                    y: Math.random()
                                });
                            }
                            return data;
                        })()
                    },           

                      ]


                });
            });

        });
           	</script>

</head>
	<body>

<div id="container" style="width:800px;height:400px;"></div>
</html>

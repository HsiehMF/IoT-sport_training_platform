
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Highchart 測試</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <script src="http://code.highcharts.com/highcharts.js"></script>
</head>
<body>
  <div id="container" style="width: 600px; height: 400px; margin: 0 auto">
  </div>
  <script>
    $(function () {
      $('#container').highcharts({
        chart: {type: 'bar'},
        title: {text: '零食消耗統計'},
        xAxis: {categories: ['冰棒', '小熊餅乾', '泡麵']},
        yAxis: {title: {text: '被吃掉的零食'}},
        series: [
          {name: '小葵', data: [1, 2, 4]},
          {name: '小新', data: [5, 7, 3]}
          ]
        });
      });
  </script>
</body>
</html>
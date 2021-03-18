
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Highchart ����</title>
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
        title: {text: '�s�����Ӳέp'},
        xAxis: {categories: ['�B��', '�p���氮', '�w��']},
        yAxis: {title: {text: '�Q�Y�����s��'}},
        series: [
          {name: '�p��', data: [1, 2, 4]},
          {name: '�p�s', data: [5, 7, 3]}
          ]
        });
      });
  </script>
</body>
</html>
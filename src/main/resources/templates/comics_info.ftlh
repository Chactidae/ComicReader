<!DOCTYPE html>
<html>
<head>
  <title>ComicReader</title>
</head>
<body>
  <h1>ComicReader</h1><hr>
    <h4>Подробная информация о комиксе</h4>
    <#list images as img>
      <img src="/images/${img.id}" height="512px"/><br><br>
    </#list>
  <hr>
</body>
</html>
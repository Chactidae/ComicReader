<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
  <title>COMIC READER</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">COMIC READER</a>
  </div>
</nav>
<h4 class="text-center">Приятного чтения!</h4> <#-- Центрируем заголовок -->
    <div class="row justify-content-center"> <#-- Центрируем колонку -->
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card mb-4">
                <div class="text-center"> <#-- Центрируем изображения -->
                    <#list images as img>
                    <div class="slide" id="slide-@image.Id">
                        <div class="slide-content">
                            <img src="/images/${img.id}" class="card-img-top" alt="..."/><br><br>
                        </div>
                    </div>
                </#list>
            </div>
            <div class="text-center"> <#-- Центрируем кнопки -->
                <a href="/book/${id}" class="btn btn-danger">Удалить мангу</a>
                <form action="/book/${id}/addPage" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="book" value="${id}">
                    <input type="file" name="page" accept="image/*"> <br>
                    <button type="submit" class="btn btn-primary">Добавить страницу</button> <#-- Кнопка для добавления страницы -->
                </form>
            </div>
        </div>
    </div>
</div>
  <hr>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>
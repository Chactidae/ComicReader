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
        <form class="d-flex" action="/" method="get">
            <input class="form-control me-2" type="search" placeholder="Поиск по названию произведения" aria-label="Search" name="b_name">
            <button class="btn btn-outline-success" type="submit">Поиск</button>
        </form>
    </div>
</nav>

<div class="container mt-4">
    <div class="row">
        <#list book as book>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card mb-4">
                <img src="/images/${book.previewImageId}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${book.title}</h5>
                    <p class="card-text">Описание книги</p>
                    <a href="/book/${book.id}" class="btn btn-success">Подробнее</a>
                    <button class="btn btn-success">В избранное</button>
                </div>
            </div>
        </div>
    </#list>
</div>

<div class="row mt-4">
    <div class="col">
        <h3>Загрузить новую мангу</h3>
        <form action="/book/create" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="bookTitle" class="form-label">Название книги:</label>
                <input type="text" class="form-control" id="bookTitle" name="title">
            </div>
            <div class="mb-3">
                <label for="bookCover" class="form-label">Обложка:</label>
                <input type="text" class="form-control" id="bookCover" name="cover">
            </div>
            <div class="mb-3">
                <label for="bookAuthor" class="form-label">Автор:</label>
                <input type="number" class="form-control" id="bookAuthor" name="author">
            </div>
            <div class="mb-3">
                <label for="bookInfo" class="form-label">Информация о книге:</label>
                <input type="number" class="form-control" id="bookInfo" name="book_info_id">
            </div>
            <div class="mb-3">
                <label for="file1" class="form-label">Первая фотография:</label>
                <input type="file" class="form-control" id="file1" name="file1">
                <label for="file2" class="form-label">Вторая фотография:</label>
                <input type="file" class="form-control" id="file2" name="file2">
                <label for="file3" class="form-label">Третья фотография:</label>
                <input type="file" class="form-control" id="file3" name="file3">
            </div>
            <!-- Остальные поля для информации о книге и фотографий -->
            <input type="submit" class="btn btn-primary" value="Добавить книгу">
        </form>
    </div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>

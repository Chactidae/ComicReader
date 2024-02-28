<!DOCTYPE html>
<head>
    <title>КИНОСТУДИЯ</title>
</head>
<body>
<h1>КИНОСТУДИЯ</h1><hr>
<form action="/" method="get">
    Поиск по названию произведения: <input type="text" name="b_name"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list book as book>
    <div>
        <img src="/images/${book.previewImageId}"  height="120px">
        <p><b>${book.title} | <a href="/book/${book.id}">Подробнее...</a></b></p>
    </div>
    <#else>
    <h3>Книг нет</h3>
</#list>
<hr>
<h3>Загрузить новую мангу</h3>
<form action="/book/create" method="post" enctype="multipart/form-data">
    Название книги: <input type="text" name="title"/><br><br>
    Обложка: <input type="text" name="cover"/><br><br>
    Автор: <input type="number" name="author"/><br><br>
    Информация о книге: <input type="number" name="book_info_id"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="submit" value="Добавить книгу"/>
</form>
</body>
</html>
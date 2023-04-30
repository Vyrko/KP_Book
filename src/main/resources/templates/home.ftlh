<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BookSell</title>
</head>
<body>
    <h1>BookSell</h1>
    <h4>Книги</h4>
<form action="/home" method="get">
    Поиск по названию: <input type="text" name="nameBook"><br>
    <input type="submit" value="Поиск"/>
</form>
    <button class="btn btn-primary rounded-circle p-2 lh-1" type="button">
        <svg class="bi" width="16" height="16"><use xlink:href="#x-lg"></use></svg>
        <span class="visually-hidden">Dismiss</span>
    </button>
<#list books as book>
    <div>
        <p>
            <b>${book.name}</b>
            ${book.cost} руб.
            | <a href="home/book/${book.id}">Подробнее...</a>
        </p>
    </div>
    <#else>
    <h3>Книг нет</h3>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/home/book/create" method="post" enctype="multipart/form-data">
    Название: <input type="text" name="name"/>
    Автор: <input type="text" name="author"/>
    Стоимость: <input type="number" name="cost"/>
    Первое фото: <input type="file" name="file1"/>
    <input type="submit" value="Добавить"/>
</form>
</body>
</html>
# Тестовое задание
# Задача

<div align="center">
    <img src="https://github.com/avan-es/vkTask/assets/83888190/c85e00b1-4dce-4c1d-b130-2b76c3d7a5df"/>
</div>

## Структура
- ApiError - пакет с ошибками и обработчиками ошибок.
- admin - пакет с доступом ROLE_ADMIN. POST/GET/DELETE пользователей, хранящихся в БД (не на https://jsonplaceholder.typicode.com/ ); доступ всех логов из БД. 
- albums - пакет с доступом ROLE_ADMIN, ROLE_ALBUMS. POST/PATCH/GET/DELETE.
- config/security - настройки.
- constants - константы и enum's.
- log - пакет с классами логирования действий пользователей в БД.
- posts - пакет с доступом ROLE_ADMIN, ROLE_POSTS. POST/PATCH/GET/DELETE.
- users -  пакет с доступом ROLE_ADMIN, ROLE_USERS. POST/PATCH/GET/DELETE на https://jsonplaceholder.typicode.com
- utils - пакет с утилитарным классом (обобщенные методы для всех типов данных). DRY.

# Описание эндпоинтов

<p align="center">
  <table align="center" width="100%">
    <tr>
        <th colspan="2">
          UserController (локальные пользователе в БД). Эндпоинты доступны только пользователям с ролью ROLE_ADMIN
        </th>
      </tr>
    <tr>
      <td>GET avan/admin/{id}</td><td>Получить пользователя по ID</td>
    </tr>
    <tr>
      <td>GET avan/admin</td><td>Получить всех пользователей</td>
    </tr>
    <tr>
      <td>POST avan/admin</td><td>Добавить нового пользователя</td>
    </tr>
    <tr>
      <td>DELETE avan/admin/{id}</td><td>Удалить пользователя по ID</td>
    </tr>
    <tr>
      <td>GET avan/admin/logs</td><td>Получить все логи из БД</td>
    </tr>
    <tr>
        <th colspan="2">
         AlbumController (взаимодействие с порталом jsonplaceholder.typicode.com). Эндпоинты доступны пользователям с ролью ROLE_ALBUMS или ROLE_ADMIN
        </th>
      </tr>
    <tr>
      <td>GET avan/albums/{id}</td><td>Получить альбом по ID </td>
    </tr>
    <tr>
      <td>GET avan/albums</td><td>Получить все альбомы</td>
    </tr>
    <tr>
      <td>POST avan/albums</td><td>Добавить новый альбом</td>
    </tr>
    <tr>
      <td>PATCH avan/albums/{id}</td><td>Обновить альбом с ID</td>
    </tr>
    <tr>
      <td>DELETE avan/albums/{id}</td><td>Удалить альбом с ID</td>
    </tr>
    <tr>
        <th colspan="2">
          PostController (взаимодействие с порталом jsonplaceholder.typicode.com). Эндпоинты доступны пользователям с ролью ROLE_POSTS или ROLE_ADMIN
        </th>
      </tr>
    <tr>
      <td>GET avan/posts/{id}</td><td>Получение поста с ID</td>
    </tr>
    <tr>
      <td>GET avan/posts</td><td>Получение всех постов</td>
    </tr>
    <tr>
      <td>POST avan/posts</td><td>Добавить новый пост</td>
    </tr>
    <tr>
      <td>PATCH avan/posts/{id}</td><td>Обновить пост с ID</td>
    </tr>
    <tr>
      <td>DELETE avan/posts/{id}</td><td>Удалить пост с ID</td>
    </tr>
     <tr>
        <th colspan="2">
          UserSiteController (взаимодействие с порталом jsonplaceholder.typicode.com). Эндпоинты доступны пользователям с ролью ROLE_USERS или ROLE_ADMIN
        </th>
      </tr>
    <tr>
      <td>GET avan/users/{id}</td><td>Получение пользователя с ID</td>
    </tr>
    <tr>
      <td>GET avan/users</td><td>Получение всех пользователей</td>
    </tr>
    <tr>
      <td>POST avan/users</td><td>Добавить нового пользователя</td>
    </tr>
    <tr>
      <td>PATCH avan/users/{id}</td><td>Обновить пользователя с ID</td>
    </tr>
    <tr>
      <td>DELETE avan/users/{id}</td><td>Удалить пользователя с ID</td>
    </tr>
  </table>
  </p>


# Стек технологий
- Java
- Spring Boot
- Spring Security
- Hibernate
- REST
- Apache Maven
- Lombok
- Postman
- IntelliJ IDEA

## Запуск программы

Для запуска приложения потребуется ***Java 17, IntelliJ IDEA.***

Алгоритм:
- Склонировать приложение в свой репозиторий или скачать на компьютер;
- Открыть в ***IntelliJ IDEA***;
- Пересобрать maven;
- Запустить проект VkTaskApplication.class;

### Для тестирования подготовлен файл коллекции Postman
[VK_TEST.postman_collection.json](https://github.com/avan-es/vkTask/blob/master/postman-tests/VK_TEST.postman_collection.json)

Импортировать тесты в Postman. Запустить коллекцию из 306 тестов. 

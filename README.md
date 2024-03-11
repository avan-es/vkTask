# Тестовое задание
# Задача

<div align="center">
    <img src="https://github.com/avan-es/vkTask/assets/83888190/c85e00b1-4dce-4c1d-b130-2b76c3d7a5df"/>
</div>

## Структура
- ApiError - пакет с ошибками и обработчиками ошибок.
- admin - пакет с доступом ROLE_ADMIN. POST/GET/DELETE пользователей, хранищихся в БД (не на https://jsonplaceholder.typicode.com/ ); доступ всех логов из БД. 
- albums - пакет с доступом ROLE_ADMIN, ROLE_ALBUMS. POST/PATCH/GET/DELETE.
- config/security - настройки.
- constants - константы и enum's.
- log - пакет с классами логирования действий пользователей в БД.
- posts - пакет с доступом ROLE_ADMIN, ROLE_POSTS. POST/PATCH/GET/DELETE.
- users -  пакет с доступом ROLE_ADMIN, ROLE_USERS. POST/PATCH/GET/DELETE на https://jsonplaceholder.typicode.com
- utils - пакет с утильтарным классом (обобщенные методы для всех типов данных). DRY.

# Описание эндпоинтов

В процессе 😊

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

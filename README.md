# API Kinopoisk Project

https://github.com/zharlykassym/Movies/assets/80376028/8f5e0fca-efd1-43be-95b9-a7fd919ec3db

Kinopoisk Movie это функциональное user-friendly мобильное приложение, взаимодействующее с интернетом и представляющее собой
каталог фильмов с актуальной информацией о каждом фильме.
Приложение представляет наличие следующих экранов:
- Главный каталог с сеткой подгружаемых фильмов: постер, отображение рейтинга и наименование
- Страница фильма с последующей информацией: наименование, рейтинг, год выпуска, описание, кнопка сохранения фильма в избранное, трейлеры и отзывы.

В ходе разработки реализованы:
- Бесперебойная работа подгрузки данных с API Kinopoisk
В работе задействованы следующие зависимости:
- Retrofit для обработки JSON объектов.
- Glide: для работы с постерами фильмов
- RxJava: для работы с данными в фоновом потоке
- Room DB: для хранения определенных данных на устройстве
Реализованы следующие функции:
- Соблюдена реализация MVVM архитектуры. Соблюдены принципы SOLID и принцип чистого кода.
- Использованы паттерны проектирования: Singleton и FactoryMethod
На реализации:
- Запуск трейлеров через неявный Intent и подгрузка соотвествующих постеров
- Производится работа над возможностью добавления фильма в папку “избранные” на память устройства в системную базу данных (Room)
для постоянного доступа к любимым фильмам в режиме оффлайн.
- Добавление списка актуальных отзывов к каждому фильму
Последующие улучшения:
- Фильтрация главного каталога по параметрам: год выпуска, жанр, рейтинг.

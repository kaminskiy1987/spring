* docker build -t taskdb .
* docker run --name taskdb -p 13306:3306 -e MYSQL_ROOT_PASSWORD=123456 taskdb


Решение

Взаимодействие постороено через API и swagger. Создаем задачу указав только наименование задачи и тело задачи.
Назначаем исполнителя.

/create - создать задачу
/delete - удалить задачу
/update - обновить задачу
/status - проверить статус задачи
/select - выбор задачи
/assignTasks - назаначить исполнителя
/addTask - добавить задачу в список
/removeTask - удалить задачу в список
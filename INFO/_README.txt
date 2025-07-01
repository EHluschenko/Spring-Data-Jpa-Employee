
(1) Отримання всіх даних

GET
http://localhost:8080/app2/api/v1/employees

(2) Створення даних

POST
http://localhost:8080/app2/api/v1/employees

Виконуємо окремі запити
{
    "firstName": "Bi",
    "lastName": "Kli",
    "post": "waiter",
	"phone": "23254647"
}

{
    "firstName": "Vira",
    "lastName": "Nishova",
    "post": "doctor",
	"phone": "096-232-45-43"
}

(3) Отримання даних за id

GET
http://localhost:8080/app2/api/v1/employees/2

Також, за неіснуючим id

GET
http://localhost:8080/app2/api/v1/employees/9


(4) Оновлення даних за id

PUT
http://localhost:8080/app2/api/v1/employees/2

{
    "firstName": "Vira",
    "lastName": "Nishova",
    "post": "doctor",
	"phone": "096-111-11-41"
}

Також, спробуємо оновити за неіснуючим id.

(5) Видалення даних за id

DELETE
http://localhost:8080/app2/api/v1/employees/2